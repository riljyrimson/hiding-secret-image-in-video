package info.jef.pduploader;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HomeActivity extends TabActivity {



    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("");
        //Assign id to Tabhost.
        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");

        //Setting up tab 1 name.
        TabMenu1.setIndicator("User Login");
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this, UserLoginActivity.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("User Register");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this,UserRegistrationActivity.class));

        //Setting up tab 2 name.

        //Adding tab1, tab2, tab3 to tabhost view.

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);

    }
}


