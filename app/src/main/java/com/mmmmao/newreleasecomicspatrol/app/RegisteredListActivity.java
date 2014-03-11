package com.mmmmao.newreleasecomicspatrol.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmmmao.newreleasecomicspatrol.app.datasource.DbPatrolComicsRepository;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.PatrolComics;
import com.mmmmao.newreleasecomicspatrol.app.domain.comics.ComicsList;

public class RegisteredListActivity extends Activity implements AdapterView.OnItemLongClickListener {

    static ArrayAdapter<String> adapter;

    private ComicsList comicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_list);

        DbPatrolComicsRepository dbPatrolComicsRepository = new DbPatrolComicsRepository(this);
        comicsList = dbPatrolComicsRepository.findAllByRegisteredComics();

        //ListView初期化
        ListView list = (ListView)findViewById(R.id.patrolComicsList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(PatrolComics patrolComics : comicsList.getList()){
            adapter.add(patrolComics.getView());
        }

        list.setAdapter(adapter);

        list.setOnItemLongClickListener(this);

        // コンテキストメニュー登録
        registerForContextMenu(list);



    }

    @Override
    public boolean onItemLongClick(AdapterView<?>parent, View view, int position, long id) {
//ListViewをクリックしたときの動作
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
// コンテキストメニューの設定
        menu.setHeaderTitle("Message");
        menu.add(0, 0, 0, "削除");
    }

    /*
 * メニューがクリックされた際に呼び出されるメソッド.
 */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // menu名を取得
        String menuName = item.getTitle().toString();
        // ContextMenuInfoを取得
        AdapterView.AdapterContextMenuInfo detailInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if ("DELETE".equals(menuName)) {
            DbPatrolComicsRepository dbPatrolComicsRepository = new DbPatrolComicsRepository(this);
            dbPatrolComicsRepository.delete(comicsList.getComicsIdByDesignationComics(detailInfo.position));
            Log.d("sample", detailInfo.position + "の" + menuName + "処理を実行");
        }
        return true;
    }

    public void toHome(View v){

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}

