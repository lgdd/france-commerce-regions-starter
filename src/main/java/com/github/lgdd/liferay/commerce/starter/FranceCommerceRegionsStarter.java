package com.github.lgdd.liferay.commerce.starter;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "commerce.region.starter.key=" + FranceCommerceRegionsStarter.FRANCE_NUMERIC_ISO_CODE,
	service = CommerceRegionsStarter.class
)
public class FranceCommerceRegionsStarter implements CommerceRegionsStarter {

	@Override public void start(ServiceContext serviceContext)
					throws Exception {

		CommerceCountry commerceCountry = getCommerceCountry(
						serviceContext.getScopeGroupId());

		if (commerceCountry == null) {
			return;
		}

		ClassLoader classLoader = FranceCommerceRegionsStarter.class.getClassLoader();
		String json = StringUtil.read(classLoader, STARTER_RESOURCES_PATH + "regions.json");
		JSONArray regions = _jsonFactory.createJSONArray(json);

		for (int i = 0; i < regions.length(); i++) {
			JSONObject region = regions.getJSONObject(i);
			double priority = region.getDouble("priority");
			String code = region.getString("code");
			String name = region.getString("name");
			_commerceRegionLocalService.addCommerceRegion(
					commerceCountry.getCommerceCountryId(), name, code, priority, true,serviceContext);
		}

	}

	private CommerceCountry getCommerceCountry(long groupId)
					throws PortalException {
		return _commerceCountryLocalService.fetchCommerceCountry(
						groupId, FRANCE_NUMERIC_ISO_CODE);
	}

	public static final int FRANCE_NUMERIC_ISO_CODE = 250;

	private static final String STARTER_RESOURCES_PATH = "com/github/lgdd/liferay/commerce/starter/internal/";

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;
}
