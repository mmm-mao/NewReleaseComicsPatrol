package com.mmmmao.newreleasecomicspatrol.app.library;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mmmmao.newreleasecomicspatrol.app.R;
import com.mmmmao.newreleasecomicspatrol.app.domain.newreleasecomics.NewReleaseComics;

import java.util.List;

public class NewReleaseComicsListItemAdapter extends ArrayAdapter<NewReleaseComics> {

    private LayoutInflater mLayoutInflater;

    public NewReleaseComicsListItemAdapter(Context context, List<NewReleaseComics> objects) {
        // 第2引数はtextViewResourceIdとされていますが、カスタムリストアイテムを使用する場合は特に意識する必要のない引数です
        super(context, 0, objects);
        // レイアウト生成に使用するインフレーター
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
        if (convertView == null) {
            // レイアウトファイルからViewを生成する
            view = mLayoutInflater.inflate(R.layout.new_release_comics_list_item, parent, false);
        } else {
            // レイアウトが存在する場合は再利用する
            view = convertView;
        }

        // リストアイテムに対応するデータを取得する
        NewReleaseComics item = getItem(position);

        // 各Viewに表示する情報を設定
        TextView titleText = (TextView) view.findViewById(R.id.TitleText);
        titleText.setText(item.getTitleUrlLink());
        MovementMethod mMethod = LinkMovementMethod.getInstance();
        titleText.setMovementMethod(mMethod);

        TextView publicationDateText = (TextView) view.findViewById(R.id.PublicationDateText);
        publicationDateText.setText(item.getPublicationDate().getValueForView());

        return view;
    }

}

