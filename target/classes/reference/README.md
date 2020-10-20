# Skills assessment

This is a scenario that is used as a basis of a capability fit with the team. It is meant to be completed over several hours with access to the internet. The intent is that you prioritise work in the skill areas where you can demonstrate the value you can add to a development team. 

The output needs to be able to run locally by the reviewer after you finish, please commit frequent changes/explain them, and regularly push to a github repository. Showing your working/understanding can completely out-weigh a last minute compilation issue.

Gold plating everything will likely be detrimental to the assessment, that being said you should limit your work up to about 3 hours. Complete as much of the basic functionality of the full stack as you can, commenting rather than implementing where you think things could be expanded on later. If you have time then do a pass adding in that value where you have opinions.

Toward the end of your timeboxed 3 hours, you should spend 20-30 minutes making sure everything compiles and that any build instructions are documented.

When you are ready to start, clone this private github repository and commence your work pushing change to a PR for review by Practiv.

## Problem domain for assessment

CoalFired is a power utility that supplies cheap energy to the consumer market in blocks of 256kWh. The company has decided to spin off a startup to compete in the clean energy space where margins are significantly higher. LowCarbPower is the name of the new brand where there is a mix of energy sources with a minimum 81% clean and 19% other sources.

You are part of a new squad that has two pieces of work in front of you, the first is to deliver a production ready proof of concept. This is will be a full stack solution that allows potential customers to work out their estimated monthly power bill (use 30 days as a month). To do this customers must supply a valid identity/email address and number of kWh that will be used in a month. The number of kWh should be used alongside the energy costs and source ratios to produce a final number. 

In the background we want to keep track of the clean energy supplier rates and how they want to change over time, so we'll poll them once an hour and persist that for later.

The second is to do a similar thing for business customers with bulk spot rate purchasing, you’ll be joined by a couple more developers and that work will start in a couple of weeks after the initial poc is completed. While this second chunk of work doesn’t require any immediate effort, it may influence to how you structure your projects.

## Potential outcomes we are looking for from a Full Stack Developer

### Frontend
Produce a Single Page Application (SPA) with:
- either a social login or valid email address 
- input field for valid kWh per month
- submit or calculate button
- ux transition to display estimated monthly power bill

### Backend
Produce components with 
- service calls to your mocked release candidate spec
- calls to a mocked CoalFired API (Open API spec supplied)
- calculation of estimate /dmonthly bill
- audit trail of estimate for each customer (observability)
- structure to support a cron job that records the price of clean energy supplier rate every hour

### Persistence
- Store the clean energy supplier rate (from the cron job)

## Assessment background

The architects of the day have decided to mandate Java for backend applications and prefer react for frontend development. They haven’t decided on a persistence layer for now. Kubernetes is meant to be the delivery platform for the stack. 

For now you have to get requirements and define/generate what that API should look like in a form that can be published with these suppliers.

```
From: LowCarbPower Product Team <support+lowcarbpower@practiv.com>
To: Software Engineering Team <engineers@practiv.com>
 
Hi Engineering Team,
 
The product owners and business have negotiated to have suppliers implement 
an API that will return their next month’s kWh price of clean energy 
i.e. they will return $0.25/kWh or $0.2445/kWh plus some other metadata.
 
Next step is that we get a release candidate spec to them, when you're ready 
if you can email that spec to the product team on support+lowcarbpower@practiv.com
```

There is an existing API from CoalFired to find out what the current price is for a block of electricity. A request to this endpoint results in the required price. An example OpenAPI specification is [available](reference/product-catalog.v1.yaml). When running this specification in a mock service a [request](http://localhost:3000/platform/productCatalogManagement/v4/productOfferingPrice/block-256-offer-price) should result in a payload like this.

```
{
  "id": "block-256-offer-price",
  "href": "/platform/productCatalogManagement/v4/productOfferingPrice/block-256-offer-price",
  "description": "One time change for a 256kWh block of usage",
  "lifecycleStatus": "string",
  "name": "Prepaid 256kWh block",
  "priceType": "otc",
  "version": "1",
  "price": {
    "unit": "NZD",
    "value": 85.43
  },
  "unitOfMeasure": {
    "amount": 256,
    "units": "kWh"
  },
  "validFor": {
    "endDateTime": null,
    "startDateTime": "2019-08-24T14:15:22Z"
  }
}
```
