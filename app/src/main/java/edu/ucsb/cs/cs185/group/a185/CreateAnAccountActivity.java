package edu.ucsb.cs.cs185.group.a185;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateAnAccountActivity extends AppCompatActivity {
    User user = User.getInstance();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        final EditText nameTextField = (EditText) findViewById(R.id.UsernameText);
        final EditText umailTextField = (EditText) findViewById(R.id.umailText);
        final EditText passwordTextField = (EditText) findViewById(R.id.passwordText);
        final RadioGroup levelRadioGroup = (RadioGroup) findViewById(R.id.levelRadioGroup);

        //final EditText majorTextField = (EditText) findViewById(R.id.MajorText);

        // CODE TO SHOW HINT IN SPINNER DROPDOWN MENU
        String[] majorArray = getResources().getStringArray(R.array.majors_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, majorArray) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                ((TextView)v.findViewById(android.R.id.text1)).setTextSize(20);
                return v;
            }
            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        spinner = (Spinner) findViewById(R.id.majorSpinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

        // ON CONFIRMATION OF ACCOUNT CREATION
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(nameTextField.getText().toString());
                user.setUmail(umailTextField.getText().toString());
                user.setPassword(passwordTextField.getText().toString());
                String major = spinner.getSelectedItem().toString();
                user.setMajor(major);
                int selectedId = levelRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                user.setUserLevel(radioButton.getText().toString());
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
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
                .setMessage("Are you sure you want to exit? The information you have entered will be lost.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
