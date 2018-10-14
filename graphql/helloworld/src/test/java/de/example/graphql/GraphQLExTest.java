package de.example.graphql;

import graphql.ExecutionResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphQLExTest {

    @Test
    public void runHelloWorld() {
        // given
        String query = "{name, age, weight, resident}";

        // when
        ExecutionResult executionResult = GraphQLEx.runHelloWorld(query);

        // then
        assertThat(executionResult.getData().toString(), is("{name=jim, age=30, weight=87.7, resident=true}"));
    }
}
