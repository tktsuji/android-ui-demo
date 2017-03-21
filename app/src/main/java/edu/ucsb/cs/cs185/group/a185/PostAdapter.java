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
 * Created by calvinwan on 3/16/17.
 */

public class PostAdapter extends BaseAdapter implements PostManager.OnUpdateListener {
    private Context context;
    private PostManager postManager = PostManager.getInstance();
    private User user = User.getInstance();

    public PostAdapter(Context context){
        this.context = context;
        postManager.setPostListener(this);
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
        title.setTextSize(18);


        TextView username = new TextView(context);
        username.setText("Posted by: " + user.getUsername());


        TextView content = new TextView(context);
        content.setText(p.getText());
        content.setMaxLines(2);

        linearLayout.addView(title);
        linearLayout.addView(username);
        for(int i=0;i <p.getTagCount();i++) {
            TextView t = new TextView(context);
            t.setText(p.getTag(i));
            if(i < p.getTagCount()-1){
                t.append(", ");
            }
            linearLayout.addView(t);
        }
        linearLayout.addView(content);

        cardView.addView(linearLayout);
        cardView.setClickable(false);
        cardView.setFocusable(false);


        return cardView;
    }
}
