package com.example.pc.localmarketting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Querypage extends AppCompatActivity {
    Button ask;
    Button camera;
    Button next;
    RecyclerView recyclerView;
    AdminAdapter adapter;
    List<Merchant> registerList;

    ImageView img1;
    int image_request_code = 1888;
    EditText queryEditText;
    Query searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querypage);
        img1 = (ImageView) findViewById(R.id.img1);
        ask = (Button) findViewById(R.id.ask);
        camera = (Button) findViewById(R.id.camera);
        next = (Button) findViewById(R.id.next);

        registerList=new ArrayList<>();
        recyclerView=findViewById(R.id.merchantList);

        recyclerView.setLayoutManager(new LinearLayoutManager(Querypage.this));
        adapter = new AdminAdapter(Querypage.this,registerList);

       queryEditText = findViewById(R.id.txt1);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQueryString = queryEditText.getText().toString().trim();
                searchQuery = FirebaseDatabase.getInstance().getReference().child("Merchant").orderByChild("categories").equalTo(searchQueryString);
                searchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            registerList.clear();
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                Merchant merchantDetails = data.getValue(Merchant.class);
                                registerList.add(merchantDetails);
                                adapter.notifyDataSetChanged();
                                //Toast.makeText(getApplicationContext(),userDetails.getUsername(),Toast.LENGTH_LONG).show();
                                //Toast.makeText(Querypage.this, "mobile: " + userDetails.getMobile(), Toast.LENGTH_SHORT).show();
                            }
                            recyclerView.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  startActivityForResult(intent, image_request_code);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Querypage.this, finalpage.class));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == image_request_code) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img1.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
