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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import static android.view.View.GONE;

public class CreateAnAccountActivity extends AppCompatActivity {
    User user = User.getInstance();
    Spinner spinner;
    EditText nameTextField, umailTextField, passwordTextField, confirmPassTextField,
             majorErrorText;
    RadioGroup levelRadioGroup;
    String major = "";
    String level = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        nameTextField = (EditText) findViewById(R.id.UsernameText);
        umailTextField = (EditText) findViewById(R.id.umailText);
        passwordTextField = (EditText) findViewById(R.id.passwordText);
        confirmPassTextField = (EditText) findViewById(R.id.confirmPasswordText);
        majorErrorText = (EditText) findViewById(R.id.invisibleMajorErrorText);
        levelRadioGroup = (RadioGroup) findViewById(R.id.levelRadioGroup);

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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if (! (spinner.getItemAtPosition(pos).equals("Major")) ) {
                    // ERASE ERROR MESSAGE FROM VIEW NOW THAT MAJOR IS SELECTED
                    majorErrorText.setVisibility(GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        // ON CONFIRMATION OF ACCOUNT CREATION
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SET USER'S INFO TO USER'S INPUT
                user.setUsername(nameTextField.getText().toString());
                user.setUmail(umailTextField.getText().toString());
                user.setPassword(passwordTextField.getText().toString());
                major = spinner.getSelectedItem().toString();
                user.setMajor(major);
                int selectedId = levelRadioGroup.getCheckedRadioButtonId();
                if (selectedId != -1) { // IF AN ITEM WAS SELECTED
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    level = radioButton.getText().toString();
                    user.setUserLevel(level);
                }

                // CHECK THAT USER'S INPUT IS VALID
                if (isInfoValid()) {
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // CHECKS THAT FIELDS ARE FILLED, EMAIL HAS @UMAIL.UCSB.EDU, AND PASSWORD IS CORRECTLY CONFIRMED
    // TODO: error message if grade level has not been selected, strings should be from resource
    public boolean isInfoValid() {
        boolean isAllInfoValid = true;
        String umail = umailTextField.getText().toString().toLowerCase();
        if (umail.length() == 0 || !umail.contains("@umail.ucsb.edu")) {
            umailTextField.setError("Please enter your full umail address.");
            isAllInfoValid = false;
        }
        String password = passwordTextField.getText().toString();
        if (password.length() == 0) {
            passwordTextField.setError("Please enter a password.");
            isAllInfoValid = false;
        }
        String confirmPassword = confirmPassTextField.getText().toString();
        if (!password.equals(confirmPassword)) {
            confirmPassTextField.setError("Password does not match.");
            isAllInfoValid = false;
        }
        if (nameTextField.getText().length() == 0) {
            nameTextField.setError("Please enter a username.");
            isAllInfoValid = false;
        }
        if (major.equals("Major")) {
            majorErrorText.setError("Please select your major.");
            isAllInfoValid = false;
        }
        if (level.length() == 0) {
            isAllInfoValid = false;
        }

        return isAllInfoValid;
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
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
