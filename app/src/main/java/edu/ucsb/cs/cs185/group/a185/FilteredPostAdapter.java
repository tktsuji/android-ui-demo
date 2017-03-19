package edu.ucsb.cs.cs185.group.a185;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wells on 3/18/17.
 */

public class FilteredPostAdapter extends BaseAdapter{
    final int FILTER_BY_TAG = 1;
    final int FILTER_BY_USERNAME = 2;
    ArrayList<Post> filtered_list;
    Context context;
    PostManager postManager = PostManager.getInstance();

    public FilteredPostAdapter(Context context, String filter, int filterType){
        this.context = context;
        filtered_list = postManager.getPostArray();
        if(filterType == FILTER_BY_TAG){
            filterByTag(filter);
        }
        else if(filterType==FILTER_BY_USERNAME) {
            filterByUsername(filter);
        }

    }

    public void filterByTag(String tag){
        for(int i =0; i<postManager.getCount();i++){
            if( !filtered_list.get(i).hasTag(tag)){
                filtered_list.remove(i);
                i--;
            }
        }
    }

    public void filterByUsername(String username){
        for(int i=0; i<postManager.getCount();i++){
            if(!filtered_list.get(i).getUser().equals(username)){
                filtered_list.remove(i);
                i--;
            }
        }
    }

    @Override
    public int getCount() {
        return filtered_list.size();
    }

    @Override
    public Post getItem(int position) {
        return filtered_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post p = filtered_list.get(position);
        CardView cardView = new CardView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardView.setLayoutParams(params);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(context);
        title.setText(p.getTitle());
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(16);

        TextView username = new TextView(context);
        username.setText("Posted by: " + filtered_list.get(position).getUser());


        for(int i=0;i <p.getTagCount();i++) {
            TextView t = new TextView(context);
            t.setText(p.getTag(i));
            linearLayout.addView(t);
        }

        TextView content = new TextView(context);
        content.setText(p.getText());
        content.setMaxLines(2);

        linearLayout.addView(title);
        linearLayout.addView(content);

        cardView.addView(linearLayout);
        cardView.setClickable(false);
        cardView.setFocusable(false);


        return cardView;
    }

}
