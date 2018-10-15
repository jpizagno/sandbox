package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * This class with import graphql.servlet.SimpleGraphQLServlet; seems to work with graphql-java-servlet and lower.
 *
 * http://localhost:8080/graphql
 *
 * http://localhost:8080/graphql?query={allLinks{url}}
 *
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    /**
     * register the resolver properly when generating the schema
     *
     * @return
     */
    private static GraphQLSchema buildSchema() {
        LinkRepository linkRepository = new LinkRepository();
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}
