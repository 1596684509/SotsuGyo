package com.example.sotsugyou.Activity.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sotsugyou.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<ListViewItem> items;
    private Context context;

    public ListViewAdapter(List<ListViewItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false);

            viewHolder.title = convertView.findViewById(R.id.item_listView_text);
            viewHolder.image = convertView.findViewById(R.id.item_listView_image);

            convertView.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.image.setImageResource(items.get(position).getImageId());
        viewHolder.title.setText(items.get(position).getTitle());

        return convertView;
    }

    class ViewHolder {

        TextView title;
        ImageView image;

    }
}
