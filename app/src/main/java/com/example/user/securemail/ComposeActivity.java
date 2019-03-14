package com.example.user.securemail;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {
   String logged="";
   DataBase db;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Intent i=getIntent();
 logged=i.getStringExtra("logged");
 db=new DataBase(this);
        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // btnSave
        final Button btnSave = (Button) findViewById(R.id.buttonSend);
        // Perform action on click
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SaveData())
                {
                    // When Save Complete
                }
            }
        });
final Button attch=(Button)findViewById(R.id.buttonattach);
attch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent j=new Intent(ComposeActivity.this,Attach2Activity.class);
        j.putExtra("logged",logged);
        startActivity(j);

    }
});
        final Button attch1=(Button)findViewById(R.id.buttonattach1);
        attch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(ComposeActivity.this,AttachVideo.class);
                j.putExtra("logged",logged);
                startActivity(j);

            }
        });
    }

    public boolean SaveData()
    {

        // txtUsername,txtPassword,txtName,txtEmail,txtTel
        final EditText txtto = (EditText)findViewById(R.id.editTextEmail);
        final EditText txtsubject = (EditText)findViewById(R.id.editTextSubject);
        final EditText txtmessage = (EditText)findViewById(R.id.editTextMessage);


        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);

        // Check Username



        String url = "http://www.grapestechs.com/riljy_hide/sendmail.php";

        Random r=new Random();
        int x=r.nextInt(1000);
        String code="SM"+x;

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("from1", logged));
        params.add(new BasicNameValuePair("to1", txtto.getText().toString()));
        params.add(new BasicNameValuePair("subject", txtsubject.getText().toString()));
        params.add(new BasicNameValuePair("message", txtmessage.getText().toString()));



        db.registration(logged,txtto.getText().toString(),txtsubject.getText().toString(),txtmessage.getText().toString());
        // Dialog
        /** Get result from Server (Return the JSON Code)
         * StatusID = ? [0=Failed,1=Complete]
         * Error	= ?	[On case error return custom error message]
         *
         * Eg Save Failed = {"StatusID":"0","Error":"Email Exists!"}
         * Eg Save Complete = {"StatusID":"1","Error":""}
         */

        String resultServer  = getHttpPost(url,params);

        /*** Default Value ***/
        String strStatusID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Prepare Save Data
        if(strStatusID.equals("0"))
        {
            ad.setMessage(strError);
            ad.show();
        }
        else
        {
            Toast.makeText(ComposeActivity.this, "Save Data Successfully", Toast.LENGTH_SHORT).show();

            Intent i=new Intent(ComposeActivity.this,AttachActivity.class);
            i.putExtra("logged",logged);
            i.putExtra("code",code);
            startActivity(i);
        }


        return true;
    }


    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //   getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}