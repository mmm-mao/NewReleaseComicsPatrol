package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolManga;

public class RegisterConfirmActivity  extends Activity {

    PatrolManga manga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_confirm);

        Intent intent = getIntent();
        manga = (PatrolManga)intent.getSerializableExtra("manga");

        final TextView title = (TextView)findViewById(R.id.title);
        title.setText(manga.getTitle().getValue());

        final TextView author = (TextView)findViewById(R.id.author);
        author.setText(manga.getAuthor().getValue());

        final TextView publisher = (TextView)findViewById(R.id.publisher);
        publisher.setText(manga.getPublisher().getValue());

    }

    public void toRegister(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterResultActivity.class);
        intent.putExtra("manga", manga);
        startActivity(intent);
    }

}
