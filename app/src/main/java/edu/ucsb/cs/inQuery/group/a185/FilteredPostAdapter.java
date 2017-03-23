package edu.ucsb.cs.inQuery.group.a185;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wells on 3/18/17.
 */

public class FilteredPostAdapter extends BaseAdapter implements PostManager.OnUpdateListener{
    final int FILTER_BY_TAG = 1;
    final int FILTER_BY_USERNAME = 2;
    final int FILTER_BY_PROFILE =3;
    ArrayList<Post> filtered_list;
    Context context;
    PostManager postManager = PostManager.getInstance();
    User user = User.getInstance();


    public FilteredPostAdapter(Context context, String filter, int filterType){
        this.context = context;
        postManager.setFilteredPostListener(this);
        filtered_list = new ArrayList<Post>();
        for(int i = 0;i<postManager.getCount(); i++){
            filtered_list.add(postManager.getPost(i));
        }
        if(filterType == FILTER_BY_TAG){
            filterByTag(filter);
        }
        else if(filterType==FILTER_BY_USERNAME) {
            filterByUsername(filter);
        }
        else if(filterType == FILTER_BY_PROFILE){
            filterByMajorAndInterests();
        }

    }

    public void onUpdate(){
        notifyDataSetChanged();
    }

    public void filterByTag(String tag){
        for (int i = 0; i < filtered_list.size(); i++) {
            if(filtered_list.size()!=0) {
                if (!filtered_list.get(i).hasTag(tag)) {
                    filtered_list.remove(i);
                    i--;
                }
            }
        }
    }

    public void filterByMajorAndInterests(){
        boolean temp[] = new boolean[filtered_list.size()];
        for(int i=0; i<filtered_list.size(); i++) {
            temp[i] = true;
            if(user.getMajor() != null && filtered_list.get(i).hasTag(user.getMajor())) {
                Log.d("if loop","if");
                temp[i] = false;
            }
            for(int j=0; j<user.getTags().size(); j++) {
                if(filtered_list.get(i).hasTag(user.getTags().get(j))) {
                    Log.d("For loop","for");
                    temp[i] = false;
                }
            }
        }

        for(int i=filtered_list.size()-1; i>=0; i--) {
            if (temp[i])
                filtered_list.remove(i);
        }
    }

    public void filterByUsername(String username){
        for(int i=0; i<filtered_list.size();i++){
            if(username!=null) {
                if(filtered_list.size()!=0) {
                    if (!filtered_list.get(i).getUser().equals(username)) {
                        filtered_list.remove(i);
                        i--;
                    }
                }
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
