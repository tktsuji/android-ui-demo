package edu.ucsb.cs.cs185.group.a185;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAnAccountActivity extends AppCompatActivity {
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        final EditText nameTextField = (EditText) findViewById(R.id.UsernameText);
        final EditText majorTextField = (EditText) findViewById(R.id.MajorText);

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(nameTextField.getText().toString());
                user.setMajor(majorTextField.getText().toString());
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
