package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final VoteRepository voteRepository;

    public Query(LinkRepository linkRepository, VoteRepository voteRepository) {

        this.linkRepository = linkRepository;
        this.voteRepository = voteRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }

    public List<Vote> allVotes() { return voteRepository.getAllVotes(); }

}

