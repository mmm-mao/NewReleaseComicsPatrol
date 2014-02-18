package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mmmmao.newreleasecomicspatrol.app.datasource.HttpMangaRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolManga;

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

    public void searchManga(View v) {
        //-----[xmlの取得先]

        EditText serchTitle = (EditText)findViewById(R.id.title);
        EditText serchKeyword = (EditText)findViewById(R.id.keyword);

        GetXmlAsyncTask getXmlAsyncTask = new GetXmlAsyncTask();
        getXmlAsyncTask.execute(serchTitle.getText().toString(), serchKeyword.getText().toString());

    }


    private class GetXmlAsyncTask extends AsyncTask<String, Void, PatrolManga> {

        @Override
        protected PatrolManga doInBackground(String... params) {

            HttpMangaRepository httpMangaRepository = new HttpMangaRepository();

            return httpMangaRepository.searchMangaByTitleAndKeyword(params);

        }

        @Override
        protected void onPostExecute(PatrolManga manga) {

            if(manga == null){
                Toast.makeText(getApplicationContext(), "指定された内容で漫画が見つかりませんでした。再度検索をお願いします。", Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(getApplicationContext(), RegisterConfirmActivity.class);
            intent.putExtra("manga", manga);

            startActivity(intent);
        }

    }
}

