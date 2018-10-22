
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
    token  # token here can be hard-coded in index.html for testing
    user {
      id
      name
    }
  }
}
# answer
#{
#  "data": {
#    "signinUser": {
#      "token": "5bca1578a71565174cf311e8",
#      "user": {
#        "id": "5bca1578a71565174cf311e8",
#        "name": "james"
#      }
#    }
#  }
#}
```

#### Vote
To vote, get your UserId from siningUser above, and from list allinks:
```
{
  allLinks {
    url
    id
  }
}
# Answer
#{
#  "data": {
#    "allLinks": [
#      {
#        "url": "http://www.otto.de",
#        "id": "5bc7871d2aafb32247cce393"
#      },
#      {
#        "url": "http://www.chefslittlehelper.com",
#        "id": "5bc787322aafb32247cce394"
#      },
#      {
#        "url": "https://en.wikipedia.org/wiki/BoJack_Horseman",
#        "id": "5bca2bd2a715651e5b341eef"
#      }
#    ]
#  }
#}
```
Then vote with above linkID and User ID:
```
mutation vote {
  createVote(
    linkId: "5bc7871d2aafb32247cce393"
    userId:"5bca1578a71565174cf311e8") {
    
    id
    createdAt 
    link {
    	url
  	}
  	user {
    	name
  	}
  }
}
#Answer:
#{
#  "data": {
#    "createVote": {
#      "id": "5bcada6450a7c2364b4aa69e",
#      "createdAt": "2018-10-20T07:33:56.273Z",
#      "link": {
#        "url": "5bc7871d2aafb32247cce393"
#      },
#      "user": {
#        "name": "james"
#      }
#    }
#  }
#}
```

to query votes:
```
{
  allVotes {
    id
    createdAt
   user {
     id
   }
  }
}
```

to filter links in query:
```
query links {
  allLinks(filter: {description_contains:"chef" }) {
    url
    description
  }
}
#{
#  "data": {
#    "allLinks": [
#      {
#        "url": "5bc787322aafb32247cce394",
#        "description": "http://www.chefslittlehelper.com"
#      }
#    ]
#  }
#}
```

To Page through the data:
```
query links {
  allLinks(skip:1 , first:1) {
    url
    description
  }
}
```

Connectors connect to other systems, be it databases, third-party APIs or alike.
