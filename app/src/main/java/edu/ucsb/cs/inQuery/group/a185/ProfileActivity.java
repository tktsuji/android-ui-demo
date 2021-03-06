package edu.ucsb.cs.inQuery.group.a185;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import static edu.ucsb.cs.inQuery.group.a185.Utility.getSmallBitmap;

public class ProfileActivity extends AppCompatActivity {
    User user = User.getInstance();
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        String tags = "";
        if (user.getTags() != null) {
            for(int i = 0; i < user.getTags().size(); i++) {
                if (i != user.getTags().size() - 1)
                    tags = tags + user.getTags().get(i) + ", ";
                else
                    tags = tags + user.getTags().get(i);
            }
        }
        profileTags.setText(tags);
        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setImageBitmap(getSmallBitmap(this, user));

        /*// TEST
        if (user.getTags().size() != 0) {
            profileTags.setText(profileTags.getText().toString());
        }
        else {
            profileTags.setText("");
        }*/
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
