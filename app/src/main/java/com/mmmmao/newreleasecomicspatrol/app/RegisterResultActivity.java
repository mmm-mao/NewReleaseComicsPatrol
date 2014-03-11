package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbNewReleaseComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.datasource.HttpComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;

public class RegisterResultActivity  extends Activity {

    private final static HttpComicsRepository httpComicsRepository = new HttpComicsRepository();

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

    public void toRegister(View v){

        Intent intent = new Intent(getApplicationContext(), RegisterInputActivity.class);
        startActivity(intent);

    }
}

