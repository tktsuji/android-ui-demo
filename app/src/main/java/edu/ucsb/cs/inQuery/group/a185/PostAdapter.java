package edu.ucsb.cs.inQuery.group.a185;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        LinearLayout.LayoutParams cardViewMargins = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cardViewMargins.setMargins(5,5,5,5);
        cardView.setContentPadding(40,20,20,20);

        cardView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shadow));
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
        title.setTextSize(20);
        title.setTextColor(context.getResources().getColor(R.color.colorLink));
        title.setPadding(5,5,5,5);
        linearLayout.addView(title);

        TextView content = new TextView(context);
        content.setText(p.getText());
        content.setLines(2);
        linearLayout.addView(content);

        TextView tags = new TextView(context);
        String tagsLine = "";
        for(int i = 0; i < p.getTagCount(); i++) {
            tagsLine = tagsLine + p.getTag(i) + "   ";
        }
        tags.setText(tagsLine);
        tags.setTypeface(null,Typeface.ITALIC);
        tags.setTextColor(context.getResources().getColor(R.color.colorLink));
        linearLayout.addView(tags);

        cardView.addView(linearLayout);
        cardView.setClickable(false);
        cardView.setFocusable(false);

        return cardView;
    }
}
