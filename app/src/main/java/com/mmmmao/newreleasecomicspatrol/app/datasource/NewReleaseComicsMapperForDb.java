package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.database.Cursor;

import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Isbn;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComicsId;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseTitle;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.PublicationDate;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Url;

public class NewReleaseComicsMapperForDb {

    public static NewReleaseComics create(Cursor cursor) {


        final int idIndex = cursor.getColumnIndex(DbNewReleaseComicsRepository.ID);
        final int titleIndex = cursor.getColumnIndex(DbNewReleaseComicsRepository.TITLE);
        final int publicationDateIndex = cursor.getColumnIndex(DbNewReleaseComicsRepository.PUBLICATION_DATE);
        final int isbnIndex = cursor.getColumnIndex(DbNewReleaseComicsRepository.ISBN);
        final int urlIndex = cursor.getColumnIndex(DbNewReleaseComicsRepository.URL);

        NewReleaseComicsId newReleaseComicsId = new NewReleaseComicsId(cursor.getInt(idIndex));
        NewReleaseTitle newReleaseTitle = new NewReleaseTitle(cursor.getString(titleIndex));
        PublicationDate publicationDate = new PublicationDate(cursor.getString(publicationDateIndex));
        Isbn isbn = new Isbn(cursor.getString(isbnIndex));
        Url url = new Url(cursor.getString(urlIndex));

        return new NewReleaseComics(newReleaseComicsId, newReleaseTitle, publicationDate, isbn, url);

    }
}
