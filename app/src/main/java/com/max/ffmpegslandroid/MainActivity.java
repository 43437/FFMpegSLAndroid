package com.max.ffmpegslandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText editText;
    private static final String TAG = "maxS";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native void play();
    public native void setPlayFile(String file);
    public native void stop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.url);
    }

    public void onClicked(View view){

        switch (view.getId()){
            case R.id.start:
                Log.d(TAG, "start");
                final String fileUrl = editText.getText().toString();
                setPlayFile(fileUrl);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        play();
                    }
                }).start();
                break;
            case R.id.stop:
                Log.d(TAG, "stop");
                stop();

                break;
        }

    }

}
