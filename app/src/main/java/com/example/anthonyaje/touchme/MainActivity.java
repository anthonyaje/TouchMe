package com.example.anthonyaje.touchme;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    boolean color_flag=true;
    View rootView=null;
    ImageView box=null;
    Long ts = null;
    String TAG = "TOUCHME";
    boolean first_touch=true;
    Bitmap prev_bmp=null;
    int prev_pixel=0;
    long clickTime;

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
//        rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        rootView.setBackgroundColor(Color.GRAY);

        img_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b = Bitmap.createBitmap(500, 500,  Bitmap.Config.ARGB_8888);
                ImageView imageview=(ImageView) findViewById(R.id.imageView);

                clickTime=System.currentTimeMillis();
                Log.d(TAG, "Click time: " + clickTime);

                if(color_flag){
                    ts = System.currentTimeMillis();
                    //rootView.setBackgroundColor(getResources().getColor(android.R.color.black));
                    //box.setBackgroundColor(getResources().getColor(android.R.color.black));
                    b.eraseColor(Color.WHITE);
                    imageview.setImageBitmap(b);
                }else{
                    ts = System.currentTimeMillis();
                    //rootView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    //box.setBackgroundColor(getResources().getColor(android.R.color.white));
                    b.eraseColor(Color.BLACK);
                    imageview.setImageBitmap(b);
                }
                color_flag=!color_flag;

                /*//while(true){
                    rootView.setDrawingCacheEnabled(true);
                    Bitmap bmp_new = rootView.getDrawingCache();
                    //Bitmap bmp_new = ((BitmapDrawable)box.getDrawable()).getBitmap();
                    int pixel_new = bmp_new.getPixel(0,0);
                    //if(prev_pixel!=pixel_new){
                        Log.d(TAG, "pixel value current： " +pixel_new );
                        Log.d(TAG, "pixel value previou： " +prev_pixel);
                        long renderTime=System.currentTimeMillis();
                        Log.d(TAG, "Rendered time current: " + renderTime);
                        Log.d(TAG, "Click to Render time: " + (renderTime-clickTime));
                        //break;
                    //}
                    prev_pixel = pixel_new;
                */

                BitmapDrawable bmp_box = (BitmapDrawable) imageview.getDrawable();
                Bitmap bmp = bmp_box.getBitmap();
                int pixel = bmp.getPixel(2,2);
                Log.d(TAG, "box pixel value: " +pixel);
                if(prev_pixel!=pixel){
                    Log.d(TAG, "prev pixel value: " +prev_pixel);
                    long renderTime=System.currentTimeMillis();
                    Log.d(TAG, "Rendered time current: " + renderTime);
                    Log.d(TAG, "Click to Render time: " + (renderTime-clickTime));

                }
                Log.d(TAG, "box pixel value: " +pixel);
                prev_pixel=pixel;

                /*
                Log.d(TAG, "pixel value: "+pixel_new);
                for(int i=20;i<100;i++)
                    for(int j=20;j<100;j++)
                        bmp_new.setPixel(i, j, Color.GREEN);

                pixel_new = bmp_new.getPixel(20,20);
                Log.d(TAG, "pixel value: " + pixel_new);
                 */
            }
        });

        //new thread for polling pixel change
        //Thread pixelPollingThread = new Thread(new PixelChecking());
        //pixelPollingThread.start();



    }

    class PixelChecking implements Runnable{
        public void run(){
            try{
                //todo get the pixel and timestamp when it changes
                while(true){
                    Bitmap bmp_new = rootView.getDrawingCache();
                    int pixel_new = bmp_new.getPixel(20,300);
                    prev_bmp = bmp_new;
                    prev_pixel = pixel_new;
                    if(prev_pixel!=pixel_new){
                        Log.d(TAG, "pixel value current" +pixel_new );
                        Log.d(TAG, "pixel value previou" +prev_pixel );

                        long renderTime=System.currentTimeMillis();
                        Log.d(TAG, "Rendered time current: " + renderTime);
                        Log.d(TAG, "Click to Render time: " + (renderTime-clickTime));
                    }
                    //SystemClock.sleep(5);
                }
            } finally {

            }
        }
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

