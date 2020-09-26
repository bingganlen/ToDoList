package com.junhao.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import com.junhao.R;
import com.junhao.adapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.listview_contact)   //代替：findviewbyid方法。
//    ListView mListView_contact;

//    @BindView(R.id.topbar)
//    QMUITopBar mTopBar;

    ListView lvGroups;
    List<Map.Entry<String,Integer>> groups;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这是toolbar菜单
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("分组列表");
        toolbar.setSubtitle("subTitle");
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);  // src/main/res/values/styles.xml   把继承的类改一下 parent="Theme.AppCompat.Light.DarkActionBar" 改成 parent="Theme.AppCompat.Light.NoActionBar"

        //This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.


        // 沉浸式状态栏
//        QMUIStatusBarHelper.translucent(this);

//        View root = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
//        ButterKnife.bind(this, root);
//        //初始化状态栏
//        //initTopBar();
//        setContentView(root);

        lvGroups = findViewById(R.id.lv_groups);
        lvGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String groupName = groups.get(position).getKey();
                Intent intent = new Intent(MainActivity.this,ToDoListActivity.class);
                intent.putExtra("groupName",groupName);
                startActivity(intent);
            }
        });

    }

    // create的执行一次   这里可以返回当前activity时重新执行  渲染
    // 如果activity在后台很久没有使用，也会被调用onStop() 杀掉
    @Override
    protected void onResume() {
        super.onResume();
        //分组列表
        SharedPreferences sharedPreferences = getSharedPreferences("groups",MODE_PRIVATE);
        Map<String, Integer> map = (Map<String, Integer>) sharedPreferences.getAll();
        Set<Map.Entry<String,Integer>> set = map.entrySet();
        groups = new ArrayList<>(set);
        lvGroups.setAdapter(new GroupAdapter(groups,MainActivity.this));
    }

    //    private void initTopBar() {
////        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.app_color_theme_4));
//        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
//            }
//        });
//
//        mTopBar.setTitle("沉浸式状态栏示例");
//    }


    //actionBar  添加菜单   menu菜单容器
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId){
            case R.id.item_add:
                Intent intent = new Intent(MainActivity.this,AddGroupActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}







//        case R.id.item_a:
//        Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
//        break;
//        case R.id.item_b:
//        Toast.makeText(MainActivity.this, "b", Toast.LENGTH_SHORT).show();
//        break;
//        case R.id.item_c:
//        Toast.makeText(MainActivity.this, "c", Toast.LENGTH_SHORT).show();
//        break;