package com.junhao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhao.R;

import java.util.List;
import java.util.Map;

/**\
 *  适配器  分组列表
 */
public class GroupAdapter extends BaseAdapter {

    List<Map.Entry<String,Integer>> groups;
    Context context;

    public GroupAdapter(List<Map.Entry<String, Integer>> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (groups == null){
            return 0;
        }
        return groups.size();
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


        viewHolder.ivIcon.setImageResource(groups.get(position).getValue());
        viewHolder.tvName.setText(groups.get(position).getKey());
        return convertView;
    }


    class ViewHolder{
        ImageView ivIcon;
        TextView tvName;
    }
}
