package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbNewReleaseComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComicsList;
import com.mmmmao.newreleasecomicspatrol.app.library.DailyScheduler;
import com.mmmmao.newreleasecomicspatrol.app.library.NewReleaseComicsListItemAdapter;
import com.mmmmao.newreleasecomicspatrol.app.service.NewReleaseComicsCheckIntentService;

public class MainActivity extends Activity {

    static NewReleaseComicsListItemAdapter adapter;

    public static final int REGISTER = 0;
    public static final int SHOW_LIST = 1;
    public static final int CHECK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DailyScheduler scheduler = new DailyScheduler(getApplicationContext());
        scheduler.setByTime(NewReleaseComicsCheckIntentService.class, 21, 10, -1);

        DbNewReleaseComicsRepository dbNewReleaseComicsRepository = new DbNewReleaseComicsRepository(this);
        NewReleaseComicsList newReleaseComicsList = dbNewReleaseComicsRepository.findAllByRegisteredComics();

        //ListView初期化
        ListView list = (ListView)findViewById(R.id.patrolComicsList);
        adapter = new NewReleaseComicsListItemAdapter(this, newReleaseComicsList.getList());

        list.setAdapter(adapter);

    }

    // オプションメニューが最初に呼び出される時に1度だけ呼び出されます
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューアイテムを追加します
        menu.add(Menu.NONE, REGISTER, Menu.NONE, "登録");
        menu.add(Menu.NONE, SHOW_LIST, Menu.NONE, "一覧表示");
        menu.add(Menu.NONE, CHECK, Menu.NONE, "チェック");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case REGISTER:
                registerAction();
                return true;

            case SHOW_LIST:
                registeredList();
                return true;
            case CHECK:
                newReleaseCheck();
                return true;

        }
        return false;
    }

    private void registerAction() {

        Intent intent = new Intent(getApplicationContext(), RegisterInputActivity.class);
        startActivity(intent);

    }

    private void registeredList() {

        Intent intent = new Intent(getApplicationContext(), RegisteredListActivity.class);

        startActivity(intent);

    }

    private void newReleaseCheck(){

        Intent intent = new Intent(this, NewReleaseComicsCheckIntentService.class);
        startService(intent);

    }

}
