package com.mmmmao.newreleasecomicspatrol.app.service;


import android.app.IntentService;
import android.content.Intent;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbNewReleaseComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.datasource.HttpComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.ComicsList;

public class NewReleaseComicsCheckIntentService extends IntentService {

    public NewReleaseComicsCheckIntentService() {
        super("NewReleaseComicsCheckIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        DbPatrolComicsRepository dbPatrolComicsRepository = new DbPatrolComicsRepository(this);
        ComicsList comicsList = dbPatrolComicsRepository.findAllByRegisteredComics();

        HttpComicsRepository httpComicsRepository = new HttpComicsRepository();
        DbNewReleaseComicsRepository dbNewReleaseComicsRepository = new DbNewReleaseComicsRepository(this);
        for(PatrolComics patrolComics : comicsList.getList()){
            NewReleaseComics newReleaseComics = httpComicsRepository.searchNewReleaseComics(patrolComics);

            if(newReleaseComics != null && newReleaseComics.registerVerify()){
                dbNewReleaseComicsRepository.register(newReleaseComics);
            }

        }
    }
}
