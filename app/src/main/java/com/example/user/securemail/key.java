package info.jef.pduploader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class key extends AppCompatActivity {
    EditText key;
    Button ok;
    String a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Intent d=getIntent();
        a=d.getStringExtra("code");
        b=d.getStringExtra("from");
        c=d.getStringExtra("to");
        key=(EditText)findViewById(R.id.key);
        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key.getText().toString().equals(a)){
                    Toast.makeText(key.this, "Matching", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),Imagviw.class);
                    i.putExtra("code",a);
                    startActivity(i);

                }
                else {
                    Toast.makeText(key.this, "Not Matching", Toast.LENGTH_SHORT).show();
                    Intent d=new Intent(getApplicationContext(),VideoView.class);
                    d.putExtra("from",b);
                    d.putExtra("to",c);
                    startActivity(d);
                }
            }
        });

    }
}
