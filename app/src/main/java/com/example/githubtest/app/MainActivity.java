package com.example.githubtest.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final int SUBACTIVITY_REQUEST_CODE = 1000;
    private static final int TAKE_PICTURE_REQUEST_CODE = 1010;

    private Uri mPhotoPathUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void handleTakePictureButton(Button button) {
        mPhotoPathUri = PhotoHelper.generateTimeStampPhotoFileUri();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoPathUri);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        boolean handled = true;
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_SubActivity1:
                this.onShowSubActivity1();
                break;
            default:
                handled = super.onOptionsItemSelected(item);
                break;
        }
        return handled;

    }

    private void onShowSubActivity1()
    {
        Intent intent = new Intent(this,SubActivity1.class);
        startActivityForResult(intent, SUBACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SUBACTIVITY_REQUEST_CODE:
                handleProvideInfoResult(resultCode, data);
                break;
            case TAKE_PICTURE_REQUEST_CODE:
                handleTakePictureResult(resultCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleTakePictureResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String photoPathName = mPhotoPathUri.getPath();
            //MediaStore...where pictures go, a layer on top of the filesystem
            //it provides thumbnails for you



        } else {
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
    }

    private void handleProvideInfoResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra(SubActivity1.NAME_EXTRA);
            TextView main = (TextView) findViewById(R.id.textViewMain);
            main.setText(name);
        }
        else {
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
    }

}
