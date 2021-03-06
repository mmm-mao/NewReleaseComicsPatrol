package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Isbn;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComicsId;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseTitle;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.PublicationDate;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.Url;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComicsList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class DbNewReleaseComicsRepository extends SQLiteOpenHelper {

    public final static String DB = "comics.db";
    public final static String TABLE = "new_release";
    public final static String ID = "_id";
    public final static String TITLE = "title";
    public final static String PUBLICATION_DATE = "publication_date";
    public final static String ISBN = "isbn";
    public final static String URL = "url";

    private final static int DB_VERSION = 1;

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE + " ("
            + "_id integer primary key autoincrement, "
            + TITLE + " text not null, "
            + PUBLICATION_DATE + " text not null, "
            + ISBN + " text unique not null, "
            + URL  + " text not null);";

    private final static String DROP_TABLE = "DROP TABLE" + TABLE;

    public DbNewReleaseComicsRepository(Context c){
        super(c, DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean register(NewReleaseComics newReleaseComics){

        ContentValues values = new ContentValues();
        values.put(TITLE, newReleaseComics.getNewReleaseTitle().getValue());
        values.put(PUBLICATION_DATE, newReleaseComics.getPublicationDate().getValue());
        values.put(ISBN, newReleaseComics.getIsbn().getValue());
        values.put(URL, newReleaseComics.getUrl().getValue());
        getWritableDatabase().insert(TABLE, null, values);

        close();

        return true;

    }

    public boolean delete(int id){
        getWritableDatabase().delete(TABLE, "_id" + "=" + id, null);

        close();

        return true;
    }

    public NewReleaseComics findByIsbn(Isbn isbn){
        final String[] columns = new String[]{ID, TITLE, PUBLICATION_DATE, ISBN, URL};

        Cursor cursor = getReadableDatabase().query(TABLE, columns, ISBN + "=?", new String[]{isbn.getValue() }, null, null, null);

        if(cursor.moveToFirst() == false){
            return null;
        }

        return NewReleaseComicsMapperForDb.create(cursor);
    }

    public NewReleaseComicsList findAllByRegisteredComics(){

        final String[] columns = new String[]{ID, TITLE, PUBLICATION_DATE, ISBN, URL};
        String date = new DateTime().toString("yyyyMMdd");

        Cursor cursor = getReadableDatabase().query(TABLE, columns, PUBLICATION_DATE + ">?", new String[]{date }, null, null, PUBLICATION_DATE + " ASC");


        final int idIndex = cursor.getColumnIndex(ID);
        final int titleIndex = cursor.getColumnIndex(TITLE);
        final int publicationDateIndex = cursor.getColumnIndex(PUBLICATION_DATE);
        final int isbnIndex = cursor.getColumnIndex(ISBN);
        final int urlIndex = cursor.getColumnIndex(URL);

        List<NewReleaseComics> list = new ArrayList<NewReleaseComics>();
        while(cursor.moveToNext()){

            NewReleaseComicsId newReleaseComicsId = new NewReleaseComicsId(cursor.getInt(idIndex));
            NewReleaseTitle newReleaseTitle = new NewReleaseTitle(cursor.getString(titleIndex));
            PublicationDate publicationDate = new PublicationDate(cursor.getString(publicationDateIndex));
            Isbn isbn = new Isbn(cursor.getString(isbnIndex));
            Url url = new Url(cursor.getString(urlIndex));

            NewReleaseComics newReleaseComics = new NewReleaseComics(newReleaseComicsId, newReleaseTitle, publicationDate, isbn, url);
            list.add(newReleaseComics);
        }

        NewReleaseComicsList newReleaseComicsList = new NewReleaseComicsList(list);

        cursor.close();
        close();

        return newReleaseComicsList;

    }


}

