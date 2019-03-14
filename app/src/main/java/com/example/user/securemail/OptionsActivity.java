package com.example.user.securemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends Activity {
String logged="";
    Button compose,inbox,ext,vid,upd,pcl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent i=getIntent();
      logged=i.getStringExtra("logged");
        compose=(Button)findViewById(R.id.cmp);
       inbox=(Button)findViewById(R.id.inbox);
        ext=(Button)findViewById(R.id.exit);
        vid=(Button)findViewById(R.id.vidcall);
        upd=(Button)findViewById(R.id.updat);
        pcl=(Button)findViewById(R.id.pcloud);
        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(OptionsActivity.this,ComposeActivity.class);
j.putExtra("logged",logged);
                startActivity(j);
            }
        });
pcl.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("http://www.grapestechs.com/securemail/personnalcloud.php?name="+logged+""); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});
inbox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Uri uri = Uri.parse("http://www.grapestechs.com/securemail/inbox.php?name="+logged+""); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
});
ext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(OptionsActivity.this,HomeActivity.class);
        startActivity(i);
    }
});
vid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      //  String packageName = "org.appspot.apprtc";
        //startActivity(getPackageManager().getLaunchIntentForPackage(packageName));
        Intent i=new Intent(OptionsActivity.this,Attach2Activity.class);
        i.putExtra("logged",logged);
        startActivity(i);
    }
});
upd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("http://www.grapestechs.com/securemail/sent.php?name="+logged+""); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});
    }
}
