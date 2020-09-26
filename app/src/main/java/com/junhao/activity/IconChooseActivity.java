package com.junhao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.junhao.R;
import com.junhao.adapter.IconsAdapter;

public class IconChooseActivity extends AppCompatActivity {

//    @BindView(R.id.lv_icons)
    ListView lvicons;
    Pair<Integer,String> [] icons = new Pair[]{
            new Pair<>(R.drawable.ar1, "Ar1"),
            new Pair<>(R.drawable.ar2, "Ar2"),
            new Pair<>(R.drawable.ar3, "Ar3"),
            new Pair<>(R.drawable.ar4, "Ar4"),
            new Pair<>(R.drawable.ar5, "Ar5"),
            new Pair<>(R.drawable.ar6, "Ar6"),
            new Pair<>(R.drawable.ar7, "Ar7"),
            new Pair<>(R.drawable.ar8, "Ar8"),
            new Pair<>(R.drawable.ar9, "Ar9"),
            new Pair<>(R.drawable.ar10, "Ar10")
    };


//    int[] iconIds = {
//          R.drawable.ar1,
//            R.drawable.ar2,
//            R.drawable.ar3,
//            R.drawable.ar4,
//            R.drawable.ar5,
//            R.drawable.ar6,
//            R.drawable.ar7,
//            R.drawable.ar8,
//            R.drawable.ar9,
//            R.drawable.ar10,
//    };
//
//    String[] icNames = {"Ar1","Ar2","Ar3","Ar4","Ar5","Ar6","Ar7","Ar8","Ar9","Ar10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_choose);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("选择Icon");
        //setSupportActionBar(toolbar);  //不需要 actionBar

        lvicons = findViewById(R.id.lv_icons);
        //适配器
        lvicons.setAdapter(new IconsAdapter(IconChooseActivity.this,icons));

        lvicons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("chosenIconId",icons[position].first);  //原本是 iconIds[position]
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}