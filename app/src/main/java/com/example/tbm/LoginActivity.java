package com.example.tbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText inputEmail, inputPassword;
    private Button btnSignUp, btnLogin;
    private TextView forget_password_button;

    private FirebaseAuth auth;

    private ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnLogin = (Button) findViewById(R.id.sign_in_button);
        forget_password_button = (TextView) findViewById(R.id.forget_password_button);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(myIntent);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            PD.dismiss();
                                            Toast.makeText(
                                                    LoginActivity.this,
                                                    "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            PD.dismiss();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                });
                    } else {
                        Toast.makeText(
                                LoginActivity.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        forget_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetAndChangePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


}

