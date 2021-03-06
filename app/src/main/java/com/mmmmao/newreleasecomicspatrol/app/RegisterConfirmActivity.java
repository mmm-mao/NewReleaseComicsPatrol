package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbNewReleaseComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.datasource.HttpComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;

public class RegisterConfirmActivity  extends Activity {

    PatrolComics comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_confirm);

        Intent intent = getIntent();
        comics = (PatrolComics)intent.getSerializableExtra("comics");

        final TextView title = (TextView)findViewById(R.id.title);
        title.setText(comics.getPatrolTitle().getValue());

        final TextView author = (TextView)findViewById(R.id.author);
        author.setText(comics.getAuthor().getValue());

        final TextView publisher = (TextView)findViewById(R.id.publisher);
        publisher.setText(comics.getPublisher().getValue());

    }

    public void toRegister(View v){


        GetXmlAsyncTask getXmlAsyncTask = new GetXmlAsyncTask();
        getXmlAsyncTask.execute(comics);
    }

    private class GetXmlAsyncTask extends AsyncTask<PatrolComics, Void, NewReleaseComics> {

        @Override
        protected NewReleaseComics doInBackground(PatrolComics... params) {

            HttpComicsRepository httpComicsRepository = new HttpComicsRepository();
            return httpComicsRepository.searchNewReleaseComics(comics);

        }

        @Override
        protected void onPostExecute(NewReleaseComics newReleaseComics) {

            if(newReleaseComics == null){
                return;
            }

            DbNewReleaseComicsRepository dbNewReleaseComicsRepository = new DbNewReleaseComicsRepository(getApplicationContext());
            dbNewReleaseComicsRepository.register(newReleaseComics);

            Intent intent = new Intent(getApplicationContext(), RegisterResultActivity.class);
            intent.putExtra("comics", comics);
            startActivity(intent);
        }

    }

}
