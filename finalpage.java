package com.example.pc.localmarketting;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.auth.FirebaseAuth;

import javax.xml.transform.Result;

public class finalpage extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    Button btnnext;
    Button btnmobile,btntv,btnelectronics,btnlaptop,btnmedicine,btngrocerrys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnmobile = (Button) findViewById(R.id.btnmobile);
        btntv = (Button) findViewById(R.id.btntv);
        btnelectronics = (Button) findViewById(R.id.btnelectronics);
        btnlaptop = (Button) findViewById(R.id.btnlaptop);
        btnmedicine = (Button) findViewById(R.id.btnmedicine);
        btngrocerrys = (Button) findViewById(R.id.btngrocerrys);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, discription.class);
                startActivity(intent);
            }
        });
        btnmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });


        btntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });


        btnelectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });


        btnlaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });


        btngrocerrys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });


        btnmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalpage.this, Querypage.class);
                startActivity(intent);
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_finalpage_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_rating) {
            Intent intent=new Intent(finalpage.this,rateus.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_notify) {
            Intent intent=new Intent(finalpage.this,notification.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent=new Intent(finalpage.this,profile.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent=new Intent(finalpage.this,helps.class);
            startActivity(intent);
        } else if (id == R.id.nav_TandC) {
            Intent intent=new Intent(finalpage.this,TandC.class);
            startActivity(intent);
            startActivity(intent);
        } else if (id == R.id.nav_invite) {
            if (id == R.id.nav_invite) {
                Intent  intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody="Local Marketting";
                String sharesubject="Local Marketting";
                intent.putExtra(Intent.EXTRA_SUBJECT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
                startActivity(Intent.createChooser(intent,"Share using"));
                return true;
            }

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(finalpage.this, welcome.class));
           finalpage.this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
