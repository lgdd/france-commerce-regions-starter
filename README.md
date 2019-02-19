# France Commerce Regions Starter

![Freelancer](doc/preview.png)

## Usage

This module allows you to add France regions automatically when initializing a Liferay Commerce site with an [Accelerator](https://dev.liferay.com/web/commerce/documentation/-/knowledge_base/1-0/accelerators) or when using the _Health Check_ feature (_Site > Commerce > Settings > Health Check > Commerce Countries > Fix issue_).

## Data structure
Regions are stored in a JSON file `regions.json` following this structure:
```
[
  ...
  {
    "priority": 10,
    "code":"IDF",
    "name":"\u00cele-de-France"
  },
  ...
]
```

Each JSON object has 3 attributes:

- __priority__: order in which the region is listed
- __code__: region's code based on ISO 3166 code without `FR-` prefix
- __name__: region's name escaped to Unicode

## Requirements

- Liferay Commerce 1.1.1

## Installation

- Download the `.jar` file in [releases](https://github.com/lgdd/france-commerce-regions-starter/releases) and deploy it into Liferay.

or

- Clone this repository, build the module with `./gradlew build` and deploy it into Liferay.

## Resources

Liferay Commerce Documentation: [Adding Regions To Liferay Commerce’s Country List](https://dev.liferay.com/web/commerce/developer-guide/-/knowledge_base/1-0/adding-regions-to-commerces-country-list)

ISO 3166-2:FR - [wikipedia.org](https://www.iso.org/obp/ui/fr/#iso:code:3166:FR), [iso.org](https://www.iso.org/obp/ui/fr/#iso:code:3166:FR)

## License

[MIT](LICENSE)
