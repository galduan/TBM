package com.example.tbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {


    private Button backToSignin, btnLogin;
    private EditText first_nameV,last_nameV,phone_numV;
    private  EditText passwordV,emailV;

    private FirebaseAuth auth;
    private ProgressDialog PD;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backToSignin = (Button) findViewById(R.id.back_to_sign_up);
        btnLogin = (Button) findViewById(R.id.sign_in_button_R);

        first_nameV  = ((EditText) findViewById(R.id.first_name));
        last_nameV = ((EditText) findViewById(R.id.last_name));
        passwordV = ((EditText) findViewById(R.id.password));
        emailV = ((EditText) findViewById(R.id.email));
        phone_numV = ((EditText) findViewById(R.id.phone_num));


        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference().child("main");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(),   LoginActivity.class);
                startActivity(myIntent);

            }
        });


        backToSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailV.getText().toString().trim();
                String  password = passwordV.getText().toString().trim();

                try {
                    if (password.length() > 0 && email.length() > 0  ) {
                        PD.show();
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                    RegisterActivity.this,
                                                    "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                        } else {

                                            addUser();
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                RegisterActivity.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void addUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("main");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                     {
                         String email = emailV.getText().toString().trim();
                         String  password = passwordV.getText().toString().trim();
                         String first_name = first_nameV.getText().toString().trim();
                         String  last_name = last_nameV.getText().toString().trim();
                         String  phone_num = phone_numV.getText().toString().trim();
                        String id = auth.getUid();
                        UserInfo user = new UserInfo(first_name,last_name, email,password,phone_num);
                        ref.child("userinfo").child(id).setValue(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
