package info.jef.pduploader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GraphicalLoginActivity extends AppCompatActivity {
    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9,b10,b11,b12;
    Button submit;
    DataBase db = new DataBase(this);
    String password="",logged="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_login);
        Intent i=getIntent();
        logged=i.getStringExtra("logged");
        b1 = (ImageButton) findViewById(R.id.b01);
        b2 = (ImageButton) findViewById(R.id.b02);
        b3 = (ImageButton) findViewById(R.id.b03);
        b4 = (ImageButton) findViewById(R.id.b04);
        b5 = (ImageButton) findViewById(R.id.b05);
        b6 = (ImageButton) findViewById(R.id.b06);
        b7 = (ImageButton) findViewById(R.id.b07);
        b8 = (ImageButton) findViewById(R.id.b08);
        b9 = (ImageButton) findViewById(R.id.b09);
        b10 = (ImageButton) findViewById(R.id.b010);
        b11 = (ImageButton) findViewById(R.id.b011);
        b12 = (ImageButton) findViewById(R.id.b012);
        submit=(Button)findViewById(R.id.goo01);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b10.setEnabled(true);
        b11.setEnabled(true);
        b12.setEnabled(true);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"öne";
                b1.setEnabled(false);

                b1.setBackgroundResource(R.drawable.donelogo);
                //  b1.setImageResource(android.R.color.transparent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"two";
                b2.setEnabled(false);

                b2.setBackgroundResource(R.drawable.donelogo);
                //  b2.setImageResource(android.R.color.transparent);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"three";
                b3.setEnabled(false);

                b3.setBackgroundResource(R.drawable.donelogo);
                // b3.setImageResource(android.R.color.transparent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"four";
                b4.setEnabled(false);

                b4.setBackgroundResource(R.drawable.donelogo);
                // b4.setImageResource(android.R.color.transparent);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"five";
                b5.setEnabled(false);

                b5.setBackgroundResource(R.drawable.donelogo);
                // b5.setImageResource(android.R.color.transparent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"six";
                b6.setEnabled(false);

                b6.setBackgroundResource(R.drawable.donelogo);
                // b6.setImageResource(android.R.color.transparent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"seven";
                b7.setEnabled(false);

                b7.setBackgroundResource(R.drawable.donelogo);
                //  b7.setImageResource(android.R.color.transparent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"eight";
                b8.setEnabled(false);

                b8.setBackgroundResource(R.drawable.donelogo);
                //b8.setImageResource(android.R.color.transparent);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"nine";
                b9.setEnabled(false);

                b9.setBackgroundResource(R.drawable.donelogo);
                // b9.setImageResource(android.R.color.transparent);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"ten";
                b10.setEnabled(false);

                b10.setBackgroundResource(R.drawable.donelogo);
                // b10.setImageResource(android.R.color.transparent);
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"eleven";
                b11.setEnabled(false);

                b11.setBackgroundResource(R.drawable.donelogo);
                // b11.setImageResource(android.R.color.transparent);
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+":"+"twelve";
                b12.setEnabled(false);

                b12.setBackgroundResource(R.drawable.donelogo);
                // b12.setImageResource(android.R.color.transparent);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pa=db.locklogin(password);
                if(pa.equalsIgnoreCase(password)) {
                    Intent h = new Intent(GraphicalLoginActivity.this, OptionsActivity.class);
                    h.putExtra("logged",logged);
                    startActivity(h);
                }
                else
                {


                    Toast.makeText(GraphicalLoginActivity.this,"Login Failed :",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

