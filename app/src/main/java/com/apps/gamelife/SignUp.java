package com.apps.gamelife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variable
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hook to all xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);


        //save data in firebase on button click


    }

    public void registerUser(View view) {

        if (!validateName()|!validateUsername()|!validateEmail()|!validatePhoneNo()|!validatePassword()) {
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);

    }

    private boolean validateName() {
        String val = regName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regName.setError("Field Is Empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field Is Empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("UserName To Long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Space Is Not Allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field Is Empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid Email Address");
            return false;
        } else {
            regEmail.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field Is Empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regPassword.setError("Field Is Empty");
            return false;
        } else {
            regPassword.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
}

