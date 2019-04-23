package info.jef.pduploader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserHomeActivity extends AppCompatActivity {
Button compose,inbox,sent,options;
String logged="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent i=getIntent();
      logged=i.getStringExtra("logged");
        compose=(Button)findViewById(R.id.btncompose);
        inbox=(Button)findViewById(R.id.btninbox);
        sent=(Button)findViewById(R.id.btnsent);
        options=(Button)findViewById(R.id.btnrate);
        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent j=new Intent(UserHomeActivity.this,ComposeActivity.class);
j.putExtra("logged",logged);
startActivity(j);
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

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Uri uri = Uri.parse("http://www.grapestechs.com/securemail/sent.php?name="+logged+""); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(UserHomeActivity.this,ChangePasswordActivity.class);
                j.putExtra("logged",logged);
                startActivity(j);
            }
        });
    }

}
