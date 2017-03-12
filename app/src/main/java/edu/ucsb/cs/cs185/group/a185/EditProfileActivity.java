package edu.ucsb.cs.cs185.group.a185;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText editProfileUsername = (EditText) findViewById(R.id.editProfileUsername);
        editProfileUsername.setText(user.getUsername());
        final EditText editProfileMajor = (EditText) findViewById(R.id.editProfileMajor);
        editProfileMajor.setText(user.getMajor());
        final EditText editProfileUserLevel = (EditText) findViewById(R.id.editProfileUserLevel);
        editProfileUserLevel.setText(user.getUserLevel());
        final EditText editProfileTags = (EditText) findViewById(R.id.editProfileTags);
        editProfileTags.setText(user.getTags().toString());
        final Button confirmButton = (Button) findViewById(R.id.editProfileConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(editProfileUsername.getText().toString());
                user.setMajor(editProfileMajor.getText().toString());
                user.setUserLevel(editProfileUserLevel.getText().toString());
                user.addTag(editProfileTags.getText().toString());
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
