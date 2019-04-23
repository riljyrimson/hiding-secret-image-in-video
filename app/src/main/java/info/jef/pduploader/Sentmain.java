package info.jef.pduploader;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Sentmain extends AppCompatActivity implements SentProductsAdapter.ClickListener {
    String URL_PRODUCTS;
    String logged="";
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
   // private static final String URL_PRODUCTS = "https://www.grapestechs.com/BCA_Disaster/Rescue_memberlist.php?district="+district1+"";

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;
String district1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmain);
        Intent d=getIntent();
        district1=d.getStringExtra("district");
        logged=d.getStringExtra("logged");
        URL_PRODUCTS = "https://www.grapestechs.com/securemail/SentMail.php?user="+logged+"";

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
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
                                productList.add(new Product(
                                       // product.getInt("id"),
                                        product.getString("id"),
                                        product.getString("code"),
                                        product.getString("from1"),
                                        product.getString("to1"),
                                        product.getString("subject"),
                                        product.getString("message")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            SentProductsAdapter adapter = new SentProductsAdapter(Sentmain.this, productList);
                            recyclerView.setAdapter(adapter);
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
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void onClick(int position) {
        //Toast.makeText(this, "position" + position, Toast.LENGTH_SHORT).show();
Product p=productList.get(position);
String a=p.getid();
String b=p.getcode();
String c=p.getfrom1();
String d=p.getto1();
String e=p.getsubject();
String f=p.getmessage();
Intent i=new Intent(Sentmain.this,Sentlistedit.class);
i.putExtra("id",a);
i.putExtra("code",b);
i.putExtra("from1",c);
i.putExtra("to1",d);
i.putExtra("subject",e);
i.putExtra("message",f);
startActivity(i);
//id,code,from1,to1,subject,message
    }
}
