package com.baseapp.eyeem.androidsdk.sample;


import com.baseapp.eyeem.androidsdk.api.EyeEmAPI;
import com.baseapp.eyeem.androidsdk.api.EyeemConnect;


import android.os.Bundle;

import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends EyeemConnect {


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = new EyeEmAPI("DDTfYxpAamf2dDvUAAekRiFaAHK6xexD", "eyeemsdksample://authorize");
        ImageView view = (ImageView) findViewById(R.id.imageView1);

        ((Button) findViewById(R.id.button1)).setOnClickListener(buttonListener);
        if(getIntent().getData() != null && getIntent().getDataString().startsWith(api.redirectURI)){

        	int lastIndex = getIntent().getDataString().lastIndexOf("code");
        	api.accessToken = getIntent().getDataString().substring(lastIndex+5);
            MyPhotosTask task = new MyPhotosTask(api, view);
            task.execute();
        } else {
            PopularPhotosTask task = new PopularPhotosTask(api, view);
            task.execute();
        }
        
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private OnClickListener buttonListener = new OnClickListener() { 

		public void onClick(View view) {
			loginToEyeEm();
		} 
    }; 
}
