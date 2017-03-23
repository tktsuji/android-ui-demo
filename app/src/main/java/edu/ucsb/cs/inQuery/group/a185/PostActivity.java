package edu.ucsb.cs.inQuery.group.a185;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {
    PostManager postManager = PostManager.getInstance();
    User user = User.getInstance();
    Post postItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);

        postItem = postManager.getPost(position);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SET UP LISTVIEW
        ListView commentList = (ListView)findViewById(R.id.comment_list);

        // SET UP HEADER
        View header = getLayoutInflater().inflate(R.layout.header_postactivity, commentList, false);
        commentList.addHeaderView(header, null, false);
        TextView title = (TextView) findViewById(R.id.big_post_title);
        TextView username = (TextView)findViewById(R.id.big_post_username);
        TextView content = (TextView) findViewById(R.id.big_post_content);
        TextView tags = (TextView) findViewById(R.id.big_post_tags);

        title.setTextColor(getResources().getColor(R.color.colorLink));
        tags.setTextColor(getResources().getColor(R.color.colorLink));
        tags.setTypeface(null, Typeface.ITALIC);

        title.setText(postItem.getTitle());
        username.setText("Posted by: " + postItem.getUser());
        username.setTypeface(null,Typeface.BOLD);
        content.setText(postItem.getText());
        String tags_list = "";
        for (int i = 0; i < postItem.getTagCount(); i ++) {
            tags_list = tags_list + postItem.getTag(i) + "   ";
        }
        tags.setText(tags_list);

        // SET UP ADAPTER
        CommentAdapter adapter = new CommentAdapter(this, position);
        commentList.setAdapter(adapter);

        // SET UP FOOTER
        View footer = getLayoutInflater().inflate(R.layout.footer_postactivity, commentList, false);
        commentList.addFooterView(footer);

        final EditText editComment = (EditText)findViewById(R.id.edit_comment_field);
        Button editCommentButton = (Button)findViewById(R.id.edit_comment_button);
        editCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postItem.addComment(editComment.getText().toString());
                postItem.addUsername(user.getUsername().toString());
                editComment.clearFocus();
                editComment.setText("");
                postManager.updateComments();
            }
        });

    }


}
