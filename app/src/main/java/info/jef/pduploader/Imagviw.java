package info.jef.pduploader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Imagviw extends AppCompatActivity {
    WebView web;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagviw);
        Intent d=getIntent();
        a=d.getStringExtra("code");
        web=(WebView)findViewById(R.id.w);
        web.loadUrl("http://www.grapestechs.com/ariljy_hide/image.php?code="+a+"");
    }
}
