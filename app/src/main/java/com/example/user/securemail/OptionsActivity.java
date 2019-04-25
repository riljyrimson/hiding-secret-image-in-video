package info.jef.pduploader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OptionsActivity extends Activity {
String logged="",URL_PRODUCTS,phone;
    Button compose,inbox,ext,vid,upd,pcl,changep;
    TextView mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent i=getIntent();
      logged=i.getStringExtra("logged");
        compose=(Button)findViewById(R.id.cmp);
       inbox=(Button)findViewById(R.id.inbox);
        ext=(Button)findViewById(R.id.exit);
       // vid=(Button)findViewById(R.id.vidcall);
        upd=(Button)findViewById(R.id.updat);
        pcl=(Button)findViewById(R.id.pcloud);
        changep=(Button)findViewById(R.id.cpass);
        mob=(TextView)findViewById(R.id.mob);

        /*URL_PRODUCTS = "https://www.grapestechs.com/securemail/phone.php?user="+logged+"";

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
                              mob.setText(product.getString("tel"));

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

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);*/


changep.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),Cpassword.class);
        startActivity(i);
    }
});


        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(OptionsActivity.this,ComposeActivity.class);
j.putExtra("logged",logged);
j.putExtra("mob",mob.getText().toString());
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

        /*Uri uri = Uri.parse("http://www.grapestechs.com/securemail/inbox.php?name="+logged+""); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        Intent i=new Intent(getApplicationContext(),listmain.class);
        i.putExtra("logged",logged);
        startActivity(i);

    }
});
ext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(OptionsActivity.this,UserLoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
});
/*vid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      //  String packageName = "org.appspot.apprtc";
        //startActivity(getPackageManager().getLaunchIntentForPackage(packageName));
        Intent i=new Intent(OptionsActivity.this,Attach2Activity.class);
        i.putExtra("logged",logged);
        startActivity(i);
    }
});*/
upd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        /*Uri uri = Uri.parse("http://www.grapestechs.com/securemail/sent.php?name="+logged+""); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/

        Intent i=new Intent(getApplicationContext(),Sentmain.class);
        i.putExtra("logged",logged);
        startActivity(i);


    }
});
    }
}
