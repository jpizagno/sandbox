package de.example.graphql;

import graphql.ExecutionResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphQLExTest {

    @Test
    public void runHelloWorld() {
        // given
        String query = "{hello}";

        // when
        ExecutionResult executionResult = GraphQLEx.runHelloWorld(query);

        // then
        assertThat(executionResult.getData().toString(), is("{hello=world}"));
    }
}
