package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class PatrolComics implements Serializable {

    private final PatrolComicsId patrolComicsId;

    private final PatrolTitle patrolTitle;

    private final Author author;

    private final Publisher publisher;

    public PatrolComics(PatrolComicsId patrolComicsId, PatrolTitle patrolTitle, Author author, Publisher publisher){
        this.patrolComicsId = patrolComicsId;
        this.patrolTitle = patrolTitle;
        this.author = author;
        this.publisher = publisher;

    }

    public String getView(){
        return patrolTitle.getValue() + ":" + author.getValue() + ":" + publisher.getValue();
    }

    public int getPatrolComicsId() { return patrolComicsId.getValue(); }

    public PatrolTitle getPatrolTitle() {
        return patrolTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }



}

