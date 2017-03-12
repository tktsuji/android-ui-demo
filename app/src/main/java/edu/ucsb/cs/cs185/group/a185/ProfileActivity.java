package edu.ucsb.cs.cs185.group.a185;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView profileUsername = (TextView) findViewById(R.id.profileUsername);
        if (user.getUsername() != null)
            profileUsername.setText(user.getUsername());
        TextView profileMajor = (TextView) findViewById(R.id.profileMajor);
        if (user.getMajor() != null)
            profileMajor.setText(user.getMajor());
        TextView profileGradYear = (TextView) findViewById(R.id.profileUserLevel);
        if (user.getUserLevel() != null)
        profileGradYear.setText(user.getUserLevel());
        TextView profileTags = (TextView) findViewById(R.id.profileTags);
        if (user.getTags() != null)
            profileTags.setText(user.getTags().toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profileEditProfile) {
            Intent intent = new Intent(this,EditProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
