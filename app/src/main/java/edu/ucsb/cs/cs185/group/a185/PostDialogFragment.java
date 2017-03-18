package edu.ucsb.cs.cs185.group.a185;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by andrewluo on 3/16/17.
 */

public class PostDialogFragment extends DialogFragment {
    Post post;
    EditText titleEditor, contentEditor, tagEditor;
    Button editButton;
    static PostManager postManager = PostManager.getInstance();

    public PostDialogFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void setPost(Post post){
        this.post = post;
    }

    public static PostDialogFragment newInstance(){
        PostDialogFragment postDialogFragment = new PostDialogFragment();
        postDialogFragment.setPost(new Post());
        return postDialogFragment;
    }

    public static PostDialogFragment newInstance(int position){
        PostDialogFragment postDialogFragment = new PostDialogFragment();
        postDialogFragment.setPost(postManager.getPost(position));
        return postDialogFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_editor, container);
        titleEditor = (EditText)view.findViewById(R.id.edit_title);
        contentEditor = (EditText)view.findViewById(R.id.edit_content);
        tagEditor = (EditText)view.findViewById(R.id.edit_tags);
        editButton = (Button)view.findViewById(R.id.edit_button);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setTitle(titleEditor.getText().toString());
                post.setText(contentEditor.getText().toString());
                post.addTag(tagEditor.getText().toString());
                postManager.addPost(post);
            }
        });

        return view;
    }
}
