package info.jef.pduploader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComposeActivity extends AppCompatActivity {
   String logged="",code,mob,URL_PRODUCTS;
   DataBase db;
   TextView pho;
    EditText txtto,txtsubject,txtmessage;
     Button attch;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Intent i=getIntent();
 logged=i.getStringExtra("logged");
 mob=i.getStringExtra("mob");
 code=i.getStringExtra("code");

          txtto = (EditText)findViewById(R.id.editTextEmail);
          txtsubject = (EditText)findViewById(R.id.editTextSubject);
          txtmessage = (EditText)findViewById(R.id.editTextMessage);



        txtsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phone()){

                }

                  }
        });

        //Toast.makeText(this, ""+mob, Toast.LENGTH_SHORT).show();
        pho=(TextView)findViewById(R.id.ph);
 db=new DataBase(this);
        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Random r=new Random();
        int x=r.nextInt(1000);
         code="SM"+x;


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
 attch=(Button)findViewById(R.id.buttonattach);
attch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //adding our stringrequest to queue



        Intent j=new Intent(ComposeActivity.this,Attach2Activity.class);
        j.putExtra("logged",logged);
        j.putExtra("code",code);
        startActivity(j);

    }
});
        final Button attch1=(Button)findViewById(R.id.buttonattach1);
        attch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Intent j=new Intent(ComposeActivity.this,AttachVideo.class);*/

                Intent j=new Intent(ComposeActivity.this,MainActivity.class);
                j.putExtra("logged",logged);
                j.putExtra("to",txtto.getText().toString());
                startActivity(j);

            }
        });
    }

    public boolean phone(){
        String rece = txtto.getText().toString();

        URL_PRODUCTS = "https://www.grapestechs.com/securemail/phone.php?user=" +rece+ "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                //id,code,from1,to1,subject,message
                                //adding the product to product list

                                // product.getInt("id"),
                                pho.setText(product.getString("tel"));

                            }


                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

return true;
    }

    public boolean SaveData()
    {

        // txtUsername,txtPassword,txtName,txtEmail,txtTel
       /* final EditText txtto = (EditText)findViewById(R.id.editTextEmail);
        final EditText txtsubject = (EditText)findViewById(R.id.editTextSubject);
        final EditText txtmessage = (EditText)findViewById(R.id.editTextMessage);*/


        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);

        // Check Username



        String url = "http://www.grapestechs.com/ariljy_hide/sendmail.php";

      /*  Random r=new Random();
        int x=r.nextInt(1000);
        String code="SM"+x;
*/
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
            String mobile=pho.getText().toString();
            //Toast.makeText(ComposeActivity.this, "Video and Image send successfully", Toast.LENGTH_SHORT).show();
            SmsManager sms= SmsManager.getDefault();
           // Toast.makeText(getApplicationContext(), "Please check your inbox", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),OptionsActivity.class);
            PendingIntent pi= PendingIntent.getActivity(getApplicationContext(),0,i,0);
            sms.sendTextMessage(String.valueOf(mobile),null,"Your Security Code is :"+code,pi,null);
            //Toast.makeText(getApplicationContext(),"message send successfully", Toast.LENGTH_LONG).show();
            //Toast.makeText(this, ""+mobile, Toast.LENGTH_SHORT).show();
            Toast.makeText(ComposeActivity.this, "Video and Image send successfully", Toast.LENGTH_SHORT).show();
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(i);

            //Intent i=new Intent(ComposeActivity.this,AttachActivity.class);
            //i.putExtra("logged",logged);
            //i.putExtra("code",code);
            //startActivity(i);
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