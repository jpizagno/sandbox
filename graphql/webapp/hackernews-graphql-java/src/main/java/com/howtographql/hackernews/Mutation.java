package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    /**
     * Create a new link.
     * mutatino in schema.graphqls is needed for this to work.
     *
     * @param url
     * @param description
     * @return
     */
    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
