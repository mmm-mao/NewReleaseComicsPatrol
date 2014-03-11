package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Author;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComicsId;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.Publisher;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolTitle;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.ComicsList;

import java.util.ArrayList;
import java.util.List;

public class DbPatrolComicsRepository extends SQLiteOpenHelper {

    private final static String DB = "manga.db";
    private final static String TABLE = "patrol";
    private final static String ID = "_id";
    private final static String TITLE = "title";
    private final static String AUTHOR = "author";
    private final static String PUBLISHER = "publisher";

    private final static int DB_VERSION = 1;

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE + " ("
            + "_id integer primary key autoincrement, "
            + TITLE + " text not null, "
            + AUTHOR + " text not null, "
            + PUBLISHER  + " text not null);";

    private final static String DROP_TABLE = "DROP TABLE" + TABLE;

    public DbPatrolComicsRepository(Context c){
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

    public boolean register(PatrolComics manga){

        ContentValues values = new ContentValues();
        values.put(TITLE, manga.getPatrolTitle().getValue());
        values.put(AUTHOR, manga.getAuthor().getValue());
        values.put(PUBLISHER, manga.getPublisher().getValue());
        getWritableDatabase().insert(TABLE, null, values);

        close();

        return true;

    }

    public boolean delete(int id){
        getWritableDatabase().delete(TABLE, "_id" + "=" + id, null);

        close();

        return true;
    }

    public ComicsList findAllByRegisteredComics(){

        final String[] columns = new String[]{ID, TITLE, AUTHOR, PUBLISHER};

        Cursor cursor = getReadableDatabase().query(TABLE, columns, null, null, null, null, "_id ASC");


        final int idIndex = cursor.getColumnIndex(ID);
        final int titleIndex = cursor.getColumnIndex(TITLE);
        final int authorIndex = cursor.getColumnIndex(AUTHOR);
        final int publisherIndex = cursor.getColumnIndex(PUBLISHER);

        List<PatrolComics> tempComicsList = new ArrayList<PatrolComics>();
        while(cursor.moveToNext()){

            PatrolComicsId patrolComicsId = new PatrolComicsId(cursor.getInt(idIndex));
            PatrolTitle patrolTitle = new PatrolTitle(cursor.getString(titleIndex));
            Author author = new Author(cursor.getString(authorIndex));
            Publisher publisher = new Publisher(cursor.getString(publisherIndex));

            PatrolComics patrolComics = new PatrolComics(patrolComicsId, patrolTitle, author, publisher);
            tempComicsList.add(patrolComics);
        }
        ComicsList comicsList = new ComicsList(tempComicsList);


        cursor.close();
        close();

        return comicsList;

    }


}

