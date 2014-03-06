package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class PatrolComics implements Serializable {

    private final ComicsId comicsId;

    private final Title title;

    private final Author author;

    private final Publisher publisher;

    public PatrolComics(ComicsId comicsId, Title title, Author author, Publisher publisher){
        this.comicsId = comicsId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;

    }

    public String getView(){
        return title.getValue() + ":" + author.getValue() + ":" + publisher.getValue();
    }

    public int getComicsId() { return comicsId.getValue(); }

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

