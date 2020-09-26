package com.junhao.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.junhao.R;
//import android.widget.Toolbar;

public class AddGroupActivity extends AppCompatActivity {

    //activity 是栈   新的activity出现时它会在最上层

    public static final int REQUESTCODE_CHOOSE_ICON = 1000;
    ImageView ivChoosenIcon;
    int chosenIconId = 0;
    EditText etGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("添加分组");
        setSupportActionBar(toolbar);

        ivChoosenIcon = findViewById(R.id.iv_chosen_icon);

        RelativeLayout relativeLayout = findViewById(R.id.rl_icon_choose);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGroupActivity.this,IconChooseActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,REQUESTCODE_CHOOSE_ICON);
            }
        });

        etGroupName = (EditText) findViewById(R.id.et_group_name);
    }



    //当另一个activity被关闭  回到这个activity时
    //startActivityForResult 接收这个  requestCode
    //resultCode 结果码  你在另一个activity做了什么事情  成功失败
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESTCODE_CHOOSE_ICON){
            if (resultCode == RESULT_OK){
                chosenIconId = data.getIntExtra("chosenIconId", -1);
                ivChoosenIcon.setImageResource(chosenIconId);
                Toast.makeText(AddGroupActivity.this,"chosenIconId: " + chosenIconId,Toast.LENGTH_SHORT);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId){
            case R.id.item_confirm:

                String groupName = etGroupName.getText().toString().trim();// 获取EditText控件文本 并去掉首尾空格
                if (groupName == null || "".equals(groupName)){
                        Toast.makeText(AddGroupActivity.this,"请输入分组名" ,Toast.LENGTH_SHORT).show();
                        return true;
                }
                if (chosenIconId == -1){
                    Toast.makeText(AddGroupActivity.this,"请输入选择图标:",Toast.LENGTH_SHORT).show();
                    return true;
                }
                saveGroup(groupName);
                finish();//把当前的activity
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //存东西
    private void saveGroup(String groupName) {
        SharedPreferences sharedPreferences = getSharedPreferences("groups",MODE_PRIVATE);//MODE_PRIVATE
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(groupName,chosenIconId);
        editor.apply();//提交修改    editor.cpmmit();
    }


}