package com.eiffelyk.www.multi_image2one;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ArrayList<ImageView> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        arrayList = new ArrayList<>();
        arrayList.add(imageView1);
        arrayList.add(imageView2);
        arrayList.add(imageView3);
        arrayList.add(imageView4);
    }


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
            Log.d("馋猫","menuItem");
            imageView5.setImageBitmap(SaveBitmap(arrayList));
            //SaveBitmap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /***
     * 创建的画布的大小和第一个imageView一样大
     * @param arrayList  ImageView数组
     * @return  Bitmap 和成后的Bitmap
     */
    private Bitmap SaveBitmap(ArrayList<ImageView> arrayList) {
        if (arrayList==null|arrayList.size()<=0) return null;
        Bitmap bmOverlay = Bitmap.createBitmap(arrayList.get(0).getWidth(), arrayList.get(0).getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bmOverlay);
        for (ImageView imageView :arrayList){
            imageView.setDrawingCacheEnabled(true);
            canvas.drawBitmap(imageView.getDrawingCache(), 0, 0, null);
            imageView.setDrawingCacheEnabled(false);
        }
        canvas.save(Canvas.ALL_SAVE_FLAG);// 保存
        canvas.restore();// 存储
        return bmOverlay;
    }
}
