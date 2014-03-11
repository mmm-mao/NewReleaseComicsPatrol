package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbNewReleaseComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComicsList;
import com.mmmmao.newreleasecomicspatrol.app.library.DailyScheduler;
import com.mmmmao.newreleasecomicspatrol.app.service.NewReleaseComicsCheckIntentService;

public class MainActivity extends Activity {

    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DailyScheduler scheduler = new DailyScheduler(getApplicationContext());
        scheduler.setByTime(NewReleaseComicsCheckIntentService.class, 4, 0, -1);

        DbNewReleaseComicsRepository dbNewReleaseComicsRepository = new DbNewReleaseComicsRepository(this);
        NewReleaseComicsList newReleaseComicsList = dbNewReleaseComicsRepository.findAllByRegisteredComics();

        //ListView初期化
        ListView list = (ListView)findViewById(R.id.patrolComicsList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(NewReleaseComics newReleaseComics : newReleaseComicsList.getList()){
            adapter.add(newReleaseComics.getView());
        }

        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void next(View v) {

        Intent intent = new Intent(getApplicationContext(), RegisterInputActivity.class);
        startActivity(intent);

    }

    public void registeredList(View v) {

        Intent intent = new Intent(getApplicationContext(), RegisteredListActivity.class);

        startActivity(intent);

    }

    public void newReleaseCheck(View v){

        Intent intent = new Intent(this, NewReleaseComicsCheckIntentService.class);
        startService(intent);

    }

}
