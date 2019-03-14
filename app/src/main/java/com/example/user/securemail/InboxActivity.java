package com.example.user.securemail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InboxActivity extends AppCompatActivity {
String logged="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        Intent i=getIntent();
        logged=i.getStringExtra("logged");
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("http://grapestechs.com/securemail/inbox.php"));
        startActivity(viewIntent);
    }
}
