package info.jef.pduploader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sentlistedit extends AppCompatActivity {
TextView ed1,ed2,ed3,ed4,ed5;
Button img,video;
String a="";
    String b;
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentlistedit);
        //ed1=(TextView) findViewById(R.id.editText);
        ed2=(TextView) findViewById(R.id.editText2);
        ed3=(TextView) findViewById(R.id.editText3);
        ed4=(TextView) findViewById(R.id.editText4);
        ed5=(TextView) findViewById(R.id.editText5);
        img=(Button)findViewById(R.id.image);
        video=(Button)findViewById(R.id.video);

        Intent i=getIntent();
        //id,code,from1,to1,subject,message
        a=i.getStringExtra("code");
          b=i.getStringExtra("from1");
        //b=i.getStringExtra("code");
         c=i.getStringExtra("to1");
        String d=i.getStringExtra("subject");
        String e=i.getStringExtra("message");
       // String f=i.getStringExtra("message");
        //ed1.setText(a);
        ed2.setText(b);
        ed3.setText(c);
        ed4.setText(d);
        ed5.setText(e);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Imagviw.class);
                i.putExtra("code",a);
                startActivity(i);

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d=new Intent(getApplicationContext(),VideoView.class);
                d.putExtra("from",b);
                d.putExtra("to",c);
                startActivity(d);

            }
        });

    }
}
