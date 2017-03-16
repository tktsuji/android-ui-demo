package edu.ucsb.cs.cs185.group.a185;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by calvinwan on 3/16/17.
 */

public class PostAdapter extends BaseAdapter implements PostManager.OnUpdateListener {
    private Context context;
    private PostManager postManager = PostManager.getInstance();

    public PostAdapter(Context context){
        this.context = context;
        postManager.setUpdateListener(this);
    }

    public void onUpdate(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return postManager.getCount();
    }

    @Override
    public Post getItem(int position) {
        return postManager.getPost(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post p = postManager.getPost(position);
        CardView cardView = new CardView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        cardView.setLayoutParams(params);

        TextView title = new TextView(context);
        title.setText(p.getTitle());
        title.setLayoutParams(params);

        TextView content = new TextView(context);
        content.setLayoutParams(params);
        content.setText(p.getText());

        cardView.addView(title);
        cardView.addView(content);

        for(int i=0;i <p.getTagCount();i++){
            TextView t = new TextView(context);
            t.setText(p.getTag(i));
            cardView.addView(t);
        }

        for(int i=0;i <p.getTagCount();i++){
            TextView c = new TextView(context);
            c.setText(p.getComment(i));
            cardView.addView(c);
        }

        return cardView;
    }
}
