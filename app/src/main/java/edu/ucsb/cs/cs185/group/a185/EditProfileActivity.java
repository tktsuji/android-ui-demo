package edu.ucsb.cs.cs185.group.a185;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static edu.ucsb.cs.cs185.group.a185.Utility.getSmallBitmap;

public class EditProfileActivity extends AppCompatActivity {
    User user = User.getInstance();
    Spinner spinner, spinnerLevels;
    EditText nameTextField;
    AutoCompleteTextView tagField;
    Button confirmButton, changePicButton, deletePicButton;
    ImageView avatar, addTagButton;
    Uri currAvatar;
    ArrayList<String> newUserTags;

    TextView tv;
    ImageView deleteTagButton;

    private static int TAG_ID = 1;
    private static final int PICK_IMAGE_REQUEST = 9876;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // SET UP TAG LIST VIEW
        ListView tagsListView = (ListView) findViewById(R.id.tags_list);

        // SET UP HEADER LAYOUT
        View header = getLayoutInflater().inflate(R.layout.header_editprofile, tagsListView, false);
        tagsListView.addHeaderView(header, null, false);
        currAvatar = user.getAvatar();
        avatar = (ImageView) findViewById(R.id.avatar);
        changePicButton = (Button) findViewById(R.id.changePicButton);
        deletePicButton = (Button) findViewById(R.id.deletePicButton);
        nameTextField = (EditText) findViewById(R.id.UsernameText);

        spinner = (Spinner) findViewById(R.id.majorSpinner);
        ArrayList<String> majorArray = new ArrayList<>();
        majorArray.addAll(Arrays.asList(getResources().getStringArray(R.array.majors_array)));
        majorArray.remove(majorArray.size()-1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, majorArray) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView)v.findViewById(android.R.id.text1)).setTextSize(20);
                return v;
            }
        };
        spinner.setAdapter(adapter);

        spinnerLevels = (Spinner) findViewById(R.id.levelSpinner);
        ArrayList<String> levelsArray = new ArrayList<>();
        levelsArray.addAll(Arrays.asList(getResources().getStringArray(R.array.levels_array)));
        levelsArray.remove(levelsArray.size()-1);
        ArrayAdapter<String> levelsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levelsArray){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView)v.findViewById(android.R.id.text1)).setTextSize(20);
                return v;
            }
        };
        spinnerLevels.setAdapter(levelsAdapter);

        // SET UP ADAPTER
        newUserTags = new ArrayList<String>(user.getTags());
        final TagAdapter tagsListAdapter = new TagAdapter(this, R.layout.tag_item, newUserTags);
        tagsListView.setAdapter(tagsListAdapter);

        // SET UP FOOTER
        View footer = getLayoutInflater().inflate(R.layout.footer_editprofile, tagsListView, false);
        tagsListView.addFooterView(footer);
        tagField = (AutoCompleteTextView) findViewById(R.id.autoCompleteTags);
        addTagButton = (ImageView) findViewById(R.id.addTagButton);
        confirmButton = (Button) findViewById(R.id.confirmButton);

        // SET UP AUTOCOMPLETE TAG VIEW
        String[] userTags = getResources().getStringArray(R.array.userTags);
        ArrayAdapter<String> adapterTags = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, userTags);
        tagField.setAdapter(adapterTags);

        // SET UP LISTENER ON AUTOCOMPLETE ITEM
        tagField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                newUserTags.add(tagField.getText().toString());
                tagsListAdapter.notifyDataSetChanged();
                tagField.setText("");
            }
        });


        // SET FIELDS TO CURRENT USER'S INFO
        nameTextField.setText(user.getUsername());
        spinner.setSelection(majorArray.indexOf(user.getMajor()));
        spinnerLevels.setSelection(levelsArray.indexOf(user.getUserLevel()));
        avatar.setImageBitmap(getSmallBitmap(this, user));

        // HANDLE CHANGE PHOTO BUTTON
        changePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgPickingIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                imgPickingIntent.addCategory(Intent.CATEGORY_OPENABLE);
                imgPickingIntent.setType("image/*");
                startActivityForResult(imgPickingIntent, PICK_IMAGE_REQUEST);
            }
        });

        // HANDLE DELETE PHOTO BUTTON
        deletePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAvatar(Uri.parse(user.getDefaultPhoto()));
                avatar.setImageBitmap(getSmallBitmap(v.getContext(), user));
            }
        });
        // HANDLE CONFIRM BUTTON
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(nameTextField.getText().toString());
                user.setMajor(spinner.getSelectedItem().toString());
                user.setUserLevel(spinnerLevels.getSelectedItem().toString());
                if (user.getUsername().equals("")) {
                    nameTextField.setError("Username cannot be blank.");
                }
                else {
                    user.setTags(newUserTags);
                    Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        // HANDLE ADD TAG BUTTON
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                System.out.println("TAG FIELD TEXT: " + tagField.getText().toString());
                newUserTags.add(tagField.getText().toString());
                tagsListAdapter.notifyDataSetChanged();
                tagField.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomAlertDialog))
                .setTitle("Cancel")
                .setMessage("Are you sure you want to exit? Any information you have entered will be lost.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.setAvatar(currAvatar);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    user.setAvatar(imageUri);
                    avatar.setImageBitmap(getSmallBitmap(this, user));
                }
        }
    }
}
