package com.example.githubtest.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

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
                this.onShowSubActivity1(item);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
                break;
        }
        return handled;

    }

    private void onShowSubActivity1(MenuItem item)
    {
        Intent intent = new Intent(this,SubActivity1.class);
        startActivity(intent);
    }


}
