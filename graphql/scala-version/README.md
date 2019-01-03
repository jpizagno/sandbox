## HowToGraph - GraphQL with Sangria Tutorial codebase

A code for GraphQL-Sangria Tutorial. Visit [HowtoGraphql.com](http://howtographql.com) to learn more.  

### Running the example

```bash
sbt run
```

SBT will automatically compile and restart the server whenever the source code changes.

After the server is started you can run queries interactively using [GraphiQL](https://github.com/graphql/graphiql) by opening [http://localhost:9080](http://localhost:9080) in a browser.

Use different PORT if you've changed it int he configuration.

### Database Configuration

This example connects to MongoDB.  Just run the default Docker Mongo image.

```bash
run_mongodb.sh
```
