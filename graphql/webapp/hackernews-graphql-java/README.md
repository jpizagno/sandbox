
#### Reference
tutorial from
https://www.howtographql.com/graphql-java/1-getting-started/

#### Run
You can run the app just by executing the run script:
```
shell% ./run.sh
```

#### Issues
This project only seems to run with old version of graphql* in the pom.xml.

#### Testing
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


#### Terminology
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

To mutate data from the command line:
```
curl -X POST   cgraphql -H "Content-Type: application/json" -d '{"query": "mutation{createLink(url: \"www.google.com\", description:\"search site\"){url description}}"}'
```

to query data call:
```
http://localhost:9999/graphql?query={allLinks{url}}
http://localhost:9999/graphql?query={allLinks{url,description}}
```

To create a User:
```
mutation createUser {
  createUser (name:"james"
    authProvider:{email:"jpizagno@gmail.com"
    password:"secret"})
    {id name}
}

#later
mutation signIn {
  signinUser(auth: {email:"jpizagno@gmail.com", password:"secret"}) {
    token
    user {
      id
      name
    }
  }
}
```

Connectors connect to other systems, be it databases, third-party APIs or alike.
