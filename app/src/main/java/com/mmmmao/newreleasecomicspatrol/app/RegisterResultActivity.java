package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolComics;

import java.util.List;

public class RegisterResultActivity  extends Activity {

    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_result);

        Intent intent = getIntent();
        PatrolComics comics = (PatrolComics)intent.getSerializableExtra("comics");

        DbPatrolComicsRepository dbPatrolComicsRepository = new DbPatrolComicsRepository(this);
        dbPatrolComicsRepository.register(comics);

        List<PatrolComics> patrolComicsList = dbPatrolComicsRepository.findByAll();

        //ListView初期化
        ListView list = (ListView)findViewById(R.id.patrolComicsList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(PatrolComics patrolComics : patrolComicsList){
            adapter.add(patrolComics.getView());
        }

        list.setAdapter(adapter);



    }

}

