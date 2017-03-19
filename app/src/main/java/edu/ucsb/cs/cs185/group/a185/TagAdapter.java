package edu.ucsb.cs.cs185.group.a185;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TagAdapter extends ArrayAdapter<String> {
    private int layout;
    private ArrayList<String> tags;

    public TagAdapter(Context context, int resource, ArrayList<String> tags_arg) {
        super(context, resource, tags_arg);
        tags = tags_arg;
        layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tagText = (TextView) convertView.findViewById(R.id.tagText);
            viewHolder.tagButton = (ImageView) convertView.findViewById(R.id.tagButton);
            convertView.setTag(viewHolder);
        }
        mainViewHolder = (ViewHolder) convertView.getTag();
        mainViewHolder.tagText.setText(getItem(position));
        mainViewHolder.tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
                tags.remove(position);
                notifyDataSetChanged();
                System.out.println(tags.size());
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView tagText;
        ImageView tagButton;
    }
}
