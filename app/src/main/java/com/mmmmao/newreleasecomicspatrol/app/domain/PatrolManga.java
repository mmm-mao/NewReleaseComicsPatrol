package com.mmmmao.newreleasecomicspatrol.app.domain;

import java.io.Serializable;

public class PatrolManga implements Serializable {

    private final Title title;

    private final Author author;

    private final Publisher publisher;

    public PatrolManga(Title title, Author author, Publisher publisher){
        this.title = title;
        this.author = author;
        this.publisher = publisher;

    }

    public String getView(){
        return title.getValue() + ":" + author.getValue() + ":" + publisher.getValue();
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

}

