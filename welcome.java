package com.example.pc.localmarketting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class welcome extends AppCompatActivity {
    EditText txtname, txtpass;
    Button login, user, merchant,login2;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference users;
    DatabaseReference user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtname = (EditText) findViewById(R.id.txtmail);
        txtpass = (EditText) findViewById(R.id.txtpass);

        login = (Button) findViewById(R.id.login);
        login2=findViewById(R.id.login2);
        user = (Button) findViewById(R.id.user);
        merchant = (Button) findViewById(R.id.merchant);
        database= FirebaseDatabase.getInstance();
        users=database.getReference("Users");
        user1=database.getReference("Merchant");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin1(txtname.getText().toString(),txtpass.getText().toString());
            }

        });


        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin2(txtname.getText().toString(),txtpass.getText().toString());
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();


            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Signup user", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Signup merchant.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Signup2.class);
                startActivity(intent);
            }
        });


    }

    private void signin2(final String contact,final String pass) {
        user1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(contact).exists())
                {
                    if (!contact.isEmpty())
                    {
                        Merchant login1=dataSnapshot.child(contact).getValue(Merchant.class);
                        if (login1.getPass().equals(pass))
                        {
                            Intent i1=new Intent(welcome.this,Querypage.class);
                            startActivity(i1);

                            Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"please register",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void signin1(final String contact, final String pass) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(contact).exists())
                {
                    if (!contact.isEmpty())
                    {
                        Users login=dataSnapshot.child(contact).getValue(Users.class);
                        if (login.getPass().equals(pass))
                        {
                            Intent i1=new Intent(welcome.this,Querypage.class);
                            startActivity(i1);

                            Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"please register",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(welcome.this, Querypage.class);
            startActivity(intent);
            welcome.this.finish();
        }
        super.onStart();
    }

    private void loginUser(String email, String pass) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuth = FirebaseAuth.getInstance();
                    Toast.makeText(welcome.this, "success", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(welcome.this, Querypage.class));
                } else
                    Toast.makeText(welcome.this, "Errorrrrrrr", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(welcome.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
