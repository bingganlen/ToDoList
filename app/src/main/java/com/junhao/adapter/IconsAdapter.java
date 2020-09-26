package com.junhao.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhao.R;

public class IconsAdapter extends BaseAdapter {

    Context context;
    Pair<Integer,String>[] icons;
//    String[] icNames;
//    int[] iconIds;


    public IconsAdapter(Context context, Pair<Integer, String>[] icons) {
        this.context = context;
        this.icons = icons;
    }

    @Override
    public int getCount() {
//        if (groups == null){
//            return 0;
//        }
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //                TextView textView = new TextView(IconChooseActivity.this);
//                textView.setText("第" + position + "行");

        //想明白parent作用   :  加入页面的时候不会重新计算高度，直接使用父控件的宽高
        //false: 并不直接加到父控件里面   ListView有自己的机制显示



        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_icon_choose,parent,false);
            viewHolder.ivIcon = convertView.findViewById(R.id.iv_icon);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.ivIcon.setImageResource(icons[position].first);
        viewHolder.tvName.setText(icons[position].second);
        return convertView;
    }



    class ViewHolder{
        ImageView ivIcon;
        TextView tvName;
    }

}
