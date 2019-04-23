package info.jef.pduploader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class VideoView extends AppCompatActivity {
    WebView web;
    String a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        Intent d=getIntent();
        a=d.getStringExtra("code");
        b=d.getStringExtra("from");
        c=d.getStringExtra("to");
        web=(WebView)findViewById(R.id.w);
        web.loadUrl("http://tascbca1619.in/jef_hide/video.php?from="+b+"&to="+c+"");

    }
}
