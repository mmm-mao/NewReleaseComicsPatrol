package com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics;

import android.text.Html;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComicsId;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolTitle;

import java.io.Serializable;

public class NewReleaseComics implements Serializable {

    private final NewReleaseComicsId newReleaseComicsId;

    private final NewReleaseTitle newReleaseTitle;

    private final PublicationDate publicationDate;

    private final Isbn isbn;

    private final Url url;

    public NewReleaseComics(NewReleaseComicsId newReleaseComicsId, NewReleaseTitle newReleaseTitle, PublicationDate publicationDate, Isbn isbn, Url url){
        this.newReleaseComicsId = newReleaseComicsId;
        this.newReleaseTitle = newReleaseTitle;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.url = url;

    }

    public boolean registerVerify(){
        return publicationDate.verify();
    }

    public CharSequence getTitleUrlLink(){

        String html = url.createHtmlLink(newReleaseTitle);
        return Html.fromHtml(html);
    }

    public NewReleaseTitle getNewReleaseTitle() {
        return newReleaseTitle;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Url getUrl() {
        return url;
    }



}

