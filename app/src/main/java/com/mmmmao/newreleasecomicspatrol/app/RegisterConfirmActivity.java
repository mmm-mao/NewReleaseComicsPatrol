package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mmmmao.newreleasecomicspatrol.app.domain.PatrolComics;

public class RegisterConfirmActivity  extends Activity {

    PatrolComics comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_confirm);

        Intent intent = getIntent();
        comics = (PatrolComics)intent.getSerializableExtra("comics");

        final TextView title = (TextView)findViewById(R.id.title);
        title.setText(comics.getTitle().getValue());

        final TextView author = (TextView)findViewById(R.id.author);
        author.setText(comics.getAuthor().getValue());

        final TextView publisher = (TextView)findViewById(R.id.publisher);
        publisher.setText(comics.getPublisher().getValue());

    }

    public void toRegister(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterResultActivity.class);
        intent.putExtra("comics", comics);
        startActivity(intent);
    }

}
