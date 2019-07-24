package com.shubhamkhuva.sqlliteimagebitmapstore;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String applicationID = "ABC0004";
        dbManager = new DBManager(this);
        dbManager.open();
        if(!dbManager.checkIfMyTitleExists(applicationID)) {
            dbManager.insertCIFApplication(applicationID);
        }
        byte[] primary1 = dbManager.fetchCIFApplicationPrimary1(applicationID);
        if(primary1==null){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = false;
                    Bitmap source = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                            R.drawable.images1, options);

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    source.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] bArray = bos.toByteArray();
                    dbManager.updateCIFApplicationPrimary1(applicationID,bArray);
                    byte[]  primary1Save = dbManager.fetchCIFApplicationPrimary1(applicationID);
                    Bitmap bm = BitmapFactory.decodeByteArray(primary1Save, 0 ,primary1Save.length);
                    ImageView im = findViewById(R.id.image);
                    im.setImageBitmap(bm);
                }
            });

        }
        else{
           primary1 = dbManager.fetchCIFApplicationPrimary1(applicationID);
           Bitmap bm = BitmapFactory.decodeByteArray(primary1, 0 ,primary1.length);
            ImageView im = findViewById(R.id.image);
            im.setImageBitmap(bm);
        }
    }
}
