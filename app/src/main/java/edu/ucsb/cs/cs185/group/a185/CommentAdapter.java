package edu.ucsb.cs.cs185.group.a185;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by wells on 3/18/17.
 */

public class CommentAdapter extends BaseAdapter implements PostManager.OnUpdateListener {
    private Context context;
    User user  = User.getInstance();
    private PostManager postManager = PostManager.getInstance();
    private Post post;

    public CommentAdapter(Context context, int position){
        this.context = context;
        post = postManager.getPost(position);
        postManager.setCommentListener(this);
    }

    public void onUpdate(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return post.getCommentCount();
    }

    @Override
    public String getItem(int position) {
        return post.getComment(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        TextView username = new TextView(context);
        //username.setText(user.getUsername() + ": ");
        username.setText(post.getUsername(position) + ": ");
        TextView comment = new TextView(context);
        comment.setText(post.getComment(position));
        layout.addView(username);
        layout.addView(comment);
        return layout;

    }
}
