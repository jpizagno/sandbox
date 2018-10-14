package de.example.graphql;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

public class GraphQLEx {

    public static void main(String[] args) {
        ExecutionResult executionResult = runHelloWorld("{hello}");
        System.out.println(executionResult.getData().toString());
    }

    /**
     * query = "{hello}"
     *
     * @param query
     * @return
     */
    public static ExecutionResult runHelloWorld(String query) {
        String schema =  "type Query{name: String, age: Int, weight: Float, resident: Boolean}";

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder ->
                        builder.dataFetcher("name", new StaticDataFetcher("jim") )
                                .dataFetcher("age", new StaticDataFetcher(30) )
                                .dataFetcher("weight", new StaticDataFetcher(87.7))
                                .dataFetcher("resident", new StaticDataFetcher(true)))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        ExecutionResult executionResult = build.execute(query);

        return executionResult;
    }

}
