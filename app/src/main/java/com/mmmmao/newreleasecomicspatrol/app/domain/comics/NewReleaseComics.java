package com.mmmmao.newreleasecomicspatrol.app.domain.comics;

import java.io.Serializable;

public class NewReleaseComics implements Serializable {

    private final ComicsId comicsId;

    private final Title title;

    private final Author author;

    private final Amount amount;

    private final PublicationDate publicationDate;

    private final Isbn isbn;

    private final Publisher publisher;

    private final Url url;

    public NewReleaseComics(ComicsId comicsId, Title title, Author author, Amount amount, PublicationDate publicationDate, Isbn isbn, Publisher publisher, Url url){
        this.comicsId = comicsId;
        this.title = title;
        this.author = author;
        this.amount = amount;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.publisher = publisher;
        this.url = url;

    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Amount getAmount() {
        return amount;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Url getUrl() {
        return url;
    }



}

