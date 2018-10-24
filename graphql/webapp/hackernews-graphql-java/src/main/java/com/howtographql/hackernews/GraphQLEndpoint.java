package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class with import graphql.servlet.SimpleGraphQLServlet; seems to work with graphql-java-servlet and lower.
 *
 * http://localhost:9999/graphql
 *
 * http://localhost:9999/graphql?query={allLinks{url}}
 *
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;
    private static final VoteRepository voteRepository;

    static {
        //Change to `new MongoClient("mongodb://<host>:<port>/hackernews")`
        //if you don't have Mongo running locally on port 27017
        MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
        voteRepository = new VoteRepository(mongo.getCollection("votes"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        Query query = new Query(linkRepository, voteRepository); //create or inject the service beans
        LinkResolver linkResolver = new LinkResolver(userRepository);
        Mutation mutation = new Mutation(linkRepository, userRepository, voteRepository);

        return new GraphQLSchemaGenerator()
                .withOperationsFromSingletons(query, linkResolver, mutation) //register the beans
                .generate(); 
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        User user = request
                .map(req -> req.getHeader("Authorization"))
                .filter(id -> !id.isEmpty())
                .map(id -> id.replace("Bearer ", ""))
                .map(userRepository::findById)
                .orElse(null);
        return new AuthContext(user, request, response);
    }


    /**
     * Wrap all data-fetching exceptions by overriding filterGraphQLErrors
     * This way, in addition to the syntactical and validation errors, data-fetching errors will have precise messages
     * sent to the client, but without the gritty details. All other error types will still be hidden behind a
     * generic message.
     *
     * Ex:  When somebody enters wrong password in Mutation signIn() then this message is seen:
     *      "message": "Exception while fetching data: Invalid credentials"
     *
     * @param errors
     * @return  List of Errors
     */
    @Override
    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        return errors.stream()
                .filter(e -> e instanceof ExceptionWhileDataFetching || super.isClientError(e))
                .map(e -> e instanceof ExceptionWhileDataFetching ? new SanitizedError((ExceptionWhileDataFetching) e) : e)
                .collect(Collectors.toList());
    }
}
