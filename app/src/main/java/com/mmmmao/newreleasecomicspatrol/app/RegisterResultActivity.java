package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolMangaRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolManga;

import java.util.List;

public class RegisterResultActivity  extends Activity {

    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_result);

        Intent intent = getIntent();
        PatrolManga manga = (PatrolManga)intent.getSerializableExtra("manga");

        DbPatrolMangaRepository dbPatrolMangaRepository = new DbPatrolMangaRepository(this);
        dbPatrolMangaRepository.register(manga);

        List<PatrolManga> patrolMangaList= dbPatrolMangaRepository.findByAll();

        //ListView初期化
        ListView list = (ListView)findViewById(R.id.patrolMangaList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(PatrolManga patrolManga : patrolMangaList){
            adapter.add(patrolManga.getView());
        }

        list.setAdapter(adapter);



    }

}

