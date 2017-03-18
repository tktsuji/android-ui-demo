package edu.ucsb.cs.cs185.group.a185;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

import static edu.ucsb.cs.cs185.group.a185.Utility.getSmallBitmap;

public class EditProfileActivity extends AppCompatActivity {
    User user = User.getInstance();
    Spinner spinner, spinnerLevels;
    EditText nameTextField, majorErrorText, levelErrorText;
    Button confirmButton, changePicButton, deletePicButton;
    ImageView avatar;
    Uri currAvatar;

    private static final int PICK_IMAGE_REQUEST = 9876;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameTextField = (EditText) findViewById(R.id.UsernameText);
        majorErrorText = (EditText) findViewById(R.id.invisibleMajorErrorText);
        levelErrorText = (EditText) findViewById(R.id.invisibleLevelErrorText);
        spinner = (Spinner) findViewById(R.id.majorSpinner);
        spinnerLevels = (Spinner) findViewById(R.id.levelSpinner);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        changePicButton = (Button) findViewById(R.id.changePicButton);
        deletePicButton = (Button) findViewById(R.id.deletePicButton);
        avatar = (ImageView) findViewById(R.id.avatar);
        currAvatar = user.getAvatar();

        // SET UP MAJORS SPINNER
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

        // SET UP LEVELS SPINNER
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
                //user.addTag(editProfileTags.getText().toString());
                if (user.getUsername().equals("")) {
                    nameTextField.setError("Username cannot be blank.");
                }
                else {
                    Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(intent);
                }
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
