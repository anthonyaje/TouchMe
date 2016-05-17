package com.example.anthonyaje.touchme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean color_flag=true;
    View rootView=null;
    Long ts = null;
    String TAG = "TouchMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View tvView = findViewById(R.id.tv_hello);
        rootView = tvView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(android.R.color.white));

        Button bt_flip = (Button) findViewById(R.id.button_flip);

        bt_flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_flag){
                    ts = System.currentTimeMillis();
                    Log.d(TAG,color_flag+" time :"+ts);
                    rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
                }else{
                    ts = System.currentTimeMillis();
                    Log.d(TAG,color_flag+" time :"+ts);
                    rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
                color_flag=!color_flag;
            }
        });
    }

/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(color_flag){
            rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
        }else{
            rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        return color_flag=!color_flag;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

