package edu.ucsb.cs.cs185.group.a185;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrewluo on 3/16/17.
 */

public class PostDialogFragment extends DialogFragment {
    static User user = User.getInstance();
    Post post;
    EditText titleEditor, contentEditor, tagEditor;
    //TextView addedTags;
    Button editButton, cancelButton;
    ImageView addTagButton;
    AutoCompleteTextView tagField;
    ArrayList<String> addedTags;
    static PostManager postManager = PostManager.getInstance();

    public PostDialogFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void setPost(Post post){
        this.post = post;
    }

    public static PostDialogFragment newInstance(){
        PostDialogFragment postDialogFragment = new PostDialogFragment();
        postDialogFragment.setPost(new Post(user.getUsername()));
        return postDialogFragment;
    }

    public static PostDialogFragment newInstance(int position){
        PostDialogFragment postDialogFragment = new PostDialogFragment();
        postDialogFragment.setPost(postManager.getPost(position));
        return postDialogFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_editor, container);

        //tagEditor = (EditText)view.findViewById(R.id.edit_tags);
        //addedTags = (TextView) view.findViewById(R.id.edit_added_tags);

        // SET UP LIST VIEW
        ListView tagsListView = (ListView) view.findViewById(R.id.edit_tags_list);

        // SET UP HEADER LAYOUT
        View header = getActivity().getLayoutInflater().inflate(R.layout.header_posteditor, tagsListView, false);
        tagsListView.addHeaderView(header, null, false);
        titleEditor = (EditText)view.findViewById(R.id.edit_title);
        contentEditor = (EditText)view.findViewById(R.id.edit_content);

        // SET UP ADAPTER
        addedTags = new ArrayList<String>();
        final TagAdapter tagsListAdapter = new TagAdapter(getActivity(), R.layout.tag_item_two, addedTags);
        tagsListView.setAdapter(tagsListAdapter);

        // SET UP FOOTER
        View footer = getActivity().getLayoutInflater().inflate(R.layout.footer_posteditor, tagsListView, false);
        tagsListView.addFooterView(footer);
        tagField = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTags);
        addTagButton = (ImageView) view.findViewById(R.id.addTagButton);
        editButton = (Button)view.findViewById(R.id.edit_button);
        cancelButton = (Button)view.findViewById(R.id.edit_cancel_button);

        // SET UP AUTOCOMPLETE TAG VIEW
        String[] userTags = getResources().getStringArray(R.array.userTags);
        ArrayAdapter<String> adapterTags = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, userTags);
        tagField.setAdapter(adapterTags);

        // SET UP LISTENER ON AUTOCOMPLETE ITEM
        tagField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                addedTags.add(tagField.getText().toString());
                tagsListAdapter.notifyDataSetChanged();
                tagField.setText("");
            }
        });

        // HANDLE ADD TAG BUTTON
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                System.out.println("TAG FIELD TEXT: " + tagField.getText().toString());
                /*String enteredTag = "'" + tagField.getText().toString() + "'";
                String currTagList = addedTags.getText().toString();
                addedTags.setText(currTagList + " " + enteredTag);*/
                addedTags.add(tagField.getText().toString());
                tagsListAdapter.notifyDataSetChanged();
                tagField.setText("");
            }
        });

        // HANDLE CANCEL BUTTON
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog))
                        .setTitle("Cancel")
                        .setMessage("Are you sure you want to exit? Any information you have entered will be lost.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setTitle(titleEditor.getText().toString());
                post.setText(contentEditor.getText().toString());
                for (int i = 0; i < addedTags.size(); i++) {
                    post.addTag(addedTags.get(i));
                }
                postManager.addPost(post);
                dismiss();
            }
        });

        return view;
    }
}
