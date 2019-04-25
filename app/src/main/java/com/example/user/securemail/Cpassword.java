package info.jef.pduploader;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cpassword extends AppCompatActivity {
EditText uname,current,newpass;
Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpassword);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        uname=(EditText)findViewById(R.id.uname);
        current=(EditText)findViewById(R.id.current);
        newpass=(EditText)findViewById(R.id.newp);
        change=(Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Changepass()){

                }
            }
        });
    }
    public boolean Changepass(){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("error");
        ad.setIcon(android.R.drawable.alert_dark_frame);
        ad.setPositiveButton("close", null);
        if (uname.getText().length() == 0) {
            ad.setMessage("please input username");
            ad.show();
            uname.requestFocus();
            return false;

        }



        String url = "http://www.grapestechs.com/ariljy_hide/changepassword.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Susername",uname.getText().toString()));
        params.add(new BasicNameValuePair("Scpassword",current.getText().toString()));
        params.add(new BasicNameValuePair("Snpassword",newpass.getText().toString()));
        String resultServer = getHttpPost(url, params);
        String strStatusID = "0";
        String strError = "unknownstatus";
        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (strStatusID.equals("0")) {
            ad.setMessage(strError);
            ad.show();
        } else {

            Toast.makeText(this, "Changed Successfully", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),UserLoginActivity.class);
            startActivity(i);

        }

        return true;
    }
    public String getHttpPost(String url, List<NameValuePair> params) {
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

}
