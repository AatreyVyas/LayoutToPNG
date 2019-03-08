package com.example.layouttopng;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Layout_to_Image {

    static int height, width;
    Parameters parameters;
    View _view;
    Context _context;

    Bitmap bMap;

    public Layout_to_Image(Context context, View view) {
        this._context = context;
        this._view = view;
    }


    public Parameters getViewParameters(View view) {

        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        parameters = new Parameters();
        parameters.setWidth(view.getMeasuredWidth());
        parameters.setHeight(view.getMeasuredHeight());
        return parameters;
    }


    public void storeImage(Bitmap bitmap,String folderName, String fileName) {
        OutputStream fOut = null;
        Uri outputFileUri;
        try {
            File root = new File(Environment.getExternalStorageDirectory()+ File.separator + folderName + File.separator);
            root.mkdirs();
            File sdImageMainDirectory = new File(root, fileName+".PNG");
            outputFileUri = Uri.fromFile(sdImageMainDirectory);
            fOut = new FileOutputStream(sdImageMainDirectory);
        } catch (Exception e) {
            Log.e("Error:","Error occured. Please try again later.");
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
        }
    }

    public Bitmap convert_layout(int width, int height) {

        _view.setDrawingCacheEnabled(true);
        _view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        _view.layout(0, 0, _view.getMeasuredWidth(), _view.getMeasuredHeight());
        _view.buildDrawingCache(true);

        bMap = Bitmap.createBitmap(_view.getMeasuredWidth(), _view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bMap);
        _view.draw(canvas);
        return bMap;
    }
}
