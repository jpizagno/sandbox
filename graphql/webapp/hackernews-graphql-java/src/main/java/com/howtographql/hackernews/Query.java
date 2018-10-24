package com.howtographql.hackernews;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;

public class Query  {

    private final LinkRepository linkRepository;
    private final VoteRepository voteRepository;

    public Query(LinkRepository linkRepository, VoteRepository voteRepository) {

        this.linkRepository = linkRepository;
        this.voteRepository = voteRepository;
    }

    @GraphQLQuery
    public List<Link> allLinks(LinkFilter filter,
                               @GraphQLArgument(name = "skip", defaultValue = "0") Number skip,
                               @GraphQLArgument(name = "first", defaultValue = "0") Number first) {
        return linkRepository.getAllLinks(filter, skip.intValue(), first.intValue());
    }

    public List<Vote> allVotes() { return voteRepository.getAllVotes(); }

}

