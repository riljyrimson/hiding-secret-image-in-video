package info.jef.pduploader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class ForgotPasswordActivity extends Activity {
    EditText UsernameEt;
    String username="";

    DataBase db = new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        UsernameEt = (EditText)findViewById(R.id.txthoteluser);
        //  PasswordEt = (EditText)findViewById(R.id.txthotelpass);
    }

    public void OnLogin(View view) {
        username = UsernameEt.getText().toString();
        // String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username);
    }
    public class BackgroundWorker extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://www.grapestechs.com/UserLogin3.php";
            if(type.equals("login")) {
                try {
                    String user_name = params[1];
                    String password ="login";
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            alertDialog.show();
            if(result.equalsIgnoreCase("login success")) {
                String password=db.forgetlogin(username);
String mobile=db.forgetlogin1(username);
                Toast.makeText(ForgotPasswordActivity.this, "Login Ok"+mobile+"password"+password, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotPasswordActivity.this, GraphicalLoginActivity.class);
                intent.putExtra("logged",username);
                startActivity(intent);
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mobile, null, ""+password, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild due to Low Network, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                ForgotPasswordActivity.this.finish();
            }
            else
            {
                Toast.makeText(ForgotPasswordActivity.this, "Login Failed"+result, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotPasswordActivity.this, UserLoginActivity.class);
                startActivity(intent);
                ForgotPasswordActivity.this.finish();
            }
        }




        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}