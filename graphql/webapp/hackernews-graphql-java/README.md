
#### reference
tutorial from
https://www.howtographql.com/graphql-java/1-getting-started/

#### run
You can run the app just by executing the run script:
```
shell% ./run.sh
```

#### issues
This project only seems to run with old version of graphql* in the pom.xml.

#### testing
in-browser IDE for exploring GraphQL:
http://localhost:9999/

enter in left-panel of browser:
```
{
  allLinks {
    url
  }
}
```

#### MongoDB
to start MongoDB in a Container
```
shell% mkdir mongo_data
shell% vi mongo.conf # edit bindIp, comment out to allow all connections
shell% ./run_mongo.sh
```


#### terminology
A "mutation" in graphql is when data is written, and the mutation describes how that is done.
Create Link via API call (GraphiQL in-browser IDE)
```
mutation createLink {
  createLink(url:"http://www.otto.de", description:"work website") {
    url
    description
  }
}
```

to query data call:
```
http://localhost:9999/graphql?query={allLinks{url}}
```


Connectors connect to other systems, be it databases, third-party APIs or alike.
