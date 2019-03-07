package com.example.layoutimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.example.layouttopng.Layout_to_Image;

public class MainActivity extends AppCompatActivity {

    Layout_to_Image layout_to_image;
    ScrollView scrollView;
    Bitmap bitmap;
    static int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        layout_to_image = new Layout_to_Image(MainActivity.this,scrollView);
        width = layout_to_image.getViewParameters(scrollView).getWidth();
        height = layout_to_image.getViewParameters(scrollView).getHeight();
        bitmap = layout_to_image.convert_layout(width,height);
        layout_to_image.storeImage(bitmap, "abc", "xyz");
    }

}
