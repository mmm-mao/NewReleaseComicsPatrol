package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;

public class RegisterResultActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_result);

        Intent intent = getIntent();
        PatrolComics comics = (PatrolComics)intent.getSerializableExtra("comics");

        DbPatrolComicsRepository dbPatrolComicsRepository = new DbPatrolComicsRepository(this);
        dbPatrolComicsRepository.register(comics);

    }

    public void toHome(View v){

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}

