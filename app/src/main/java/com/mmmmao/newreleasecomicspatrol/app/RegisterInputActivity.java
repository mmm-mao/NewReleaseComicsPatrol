package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mmmmao.newreleasecomicspatrol.app.datasource.HttpComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolComics;

public class RegisterInputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void searchComics(View v) {
        //-----[xmlの取得先]

        EditText searchTitle = (EditText)findViewById(R.id.title);
        EditText searchKeyword = (EditText)findViewById(R.id.keyword);

        GetXmlAsyncTask getXmlAsyncTask = new GetXmlAsyncTask();
        getXmlAsyncTask.execute(searchTitle.getText().toString(), searchKeyword.getText().toString());

    }


    private class GetXmlAsyncTask extends AsyncTask<String, Void, PatrolComics> {

        @Override
        protected PatrolComics doInBackground(String... params) {

            HttpComicsRepository httpComicsRepository = new HttpComicsRepository();

            return httpComicsRepository.searchComicsByTitleAndKeyword(params);

        }

        @Override
        protected void onPostExecute(PatrolComics comics) {

            if(comics == null){
                Toast.makeText(getApplicationContext(), "指定された内容で漫画が見つかりませんでした。再度検索をお願いします。", Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(getApplicationContext(), RegisterConfirmActivity.class);
            intent.putExtra("comics", comics);

            startActivity(intent);
        }

    }
}

