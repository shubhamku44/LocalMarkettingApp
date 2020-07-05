package com.example.pc.localmarketting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.ValidateAccountRequestCreator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    EditText txtname, txtpass, txtmail, txtcontact;
    Button btnsignup;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnsignup=findViewById(R.id.btnsignup);
        txtname=findViewById(R.id.txtname);
        txtpass=findViewById(R.id.txtpass);
        txtmail=findViewById(R.id.txtmail);
        txtcontact=findViewById(R.id.txtcontact);

       btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();

            }
        });


    }

    private void CreateAccount()
    {
        final String name = txtname.getText().toString();
        final String pass = txtpass.getText().toString();
        final String mail = txtmail.getText().toString();
        final String contact = txtcontact.getText().toString();
        final  DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(contact).exists()))
                {
                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("pass",pass);
                    userdataMap.put("mail",mail);
                    userdataMap.put("contact",contact);


                    RootRef.child("Users").child(contact).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Congratulations you are Registerd !!!!", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Please check Database Connectivity!!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });



    }
}
