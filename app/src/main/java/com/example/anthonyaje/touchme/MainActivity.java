package com.example.anthonyaje.touchme;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean color_flag=true;
    View rootView=null;
    ImageView box=null;
    Long ts = null;
    String TAG = "TouchMe";
    boolean first_touch=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* full-screen */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tvView = findViewById(R.id.tv_hello);
        ImageView img_bt = (ImageView) findViewById(R.id.imageView2);

        box = (ImageView) findViewById(R.id.imageView);
        rootView = tvView.getRootView();
        box.setBackgroundColor(getResources().getColor(android.R.color.black));
        //rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
        rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

        img_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_flag){
                    ts = System.currentTimeMillis();
                    Log.d(TAG, color_flag + " time :" + ts);
                    //rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
                    box.setBackgroundColor(getResources().getColor(android.R.color.black));
                }else{
                    ts = System.currentTimeMillis();
                    Log.d(TAG, color_flag + " time :" + ts);
                    //rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    box.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
                color_flag=!color_flag;
            }
        });

    }


/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(first_touch) {
            first_touch=false;
            if (color_flag) {
                ts = System.currentTimeMillis();
                Log.d(TAG, color_flag + " time :" + ts);
                //rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
                box.setBackgroundColor(getResources().getColor(android.R.color.black));
            } else {
                ts = System.currentTimeMillis();
                Log.d(TAG, color_flag + " time :" + ts);
                //rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
                box.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
            first_touch=true;
            color_flag = !color_flag;
        }
        return true;
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

