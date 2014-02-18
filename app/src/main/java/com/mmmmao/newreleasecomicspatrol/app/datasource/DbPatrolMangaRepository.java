package com.mmmmao.newreleasecomicspatrol.app.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mmmmao.newreleasecomicspatrol.app.domain.Author;
import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolManga;
import com.mmmmao.newreleasecomicspatrol.app.domain.Publisher;
import com.mmmmao.newreleasecomicspatrol.app.domain.Title;

import java.util.ArrayList;
import java.util.List;

public class DbPatrolMangaRepository extends SQLiteOpenHelper {

    private final static String DB = "manga.db";

    private final static String TABLE = "patrol";

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

    public DbPatrolMangaRepository(Context c){
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

    public boolean register(PatrolManga manga){

        ContentValues values = new ContentValues();
        values.put(TITLE, manga.getTitle().getValue());
        values.put(AUTHOR, manga.getAuthor().getValue());
        values.put(PUBLISHER, manga.getPublisher().getValue());
        getWritableDatabase().insert(TABLE, null, values);

        close();

        return true;

    }

    public List<PatrolManga> findByAll(){

        final String[] colums = new String[]{TITLE, AUTHOR, PUBLISHER};

        Cursor cursor = getReadableDatabase().query(TABLE, colums, null, null, null, null, "_id DESC");


        final int titleIndex = cursor.getColumnIndex(TITLE);
        final int authorIndex = cursor.getColumnIndex(AUTHOR);
        final int publisherIndex = cursor.getColumnIndex(PUBLISHER);

        List<PatrolManga> patrolMangaList = new ArrayList<PatrolManga>();
        while(cursor.moveToNext()){

            Title title = new Title(cursor.getString(titleIndex));
            Author author = new Author(cursor.getString(authorIndex));
            Publisher publisher = new Publisher(cursor.getString(publisherIndex));

            PatrolManga patrolManga = new PatrolManga(title, author, publisher);
            patrolMangaList.add(patrolManga);
        }


        cursor.close();
        close();

        return patrolMangaList;

    }


}

