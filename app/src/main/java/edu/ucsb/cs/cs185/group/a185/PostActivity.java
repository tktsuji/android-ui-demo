package edu.ucsb.cs.cs185.group.a185;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {
    PostManager postManager = PostManager.getInstance();
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

        TextView title = (TextView) findViewById(R.id.big_post_title);
        TextView username = (TextView)findViewById(R.id.big_post_username);
        TextView content = (TextView) findViewById(R.id.big_post_content);
        TextView tags = (TextView) findViewById(R.id.big_post_tags);

        title.setText(postItem.getTitle());
        username.setText(postItem.getUser());
        content.setText(postItem.getText());
        String tags_list = "";
        for (int i = 0; i < postItem.getTagCount(); i ++) {
            tags_list = tags_list + postItem.getTag(i) + ", ";
        }
        tags.setText(tags_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView commentList = (ListView)findViewById(R.id.comment_list);
        CommentAdapter adapter = new CommentAdapter(this, position);
        commentList.setAdapter(adapter);

        final EditText editComment = (EditText)findViewById(R.id.edit_comment_field);
        Button editCommentButton = (Button)findViewById(R.id.edit_comment_button);
        editCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postItem.addComment(editComment.getText().toString());
                editComment.clearFocus();
                editComment.setText("");
                postManager.updateComments();
            }
        });

    }


}
