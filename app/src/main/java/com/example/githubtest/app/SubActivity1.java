package com.example.githubtest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SubActivity1 extends Activity {



    public static final String NAME_EXTRA = "nameExtra";

    public EditText getmNameTextValue() {
        if (mNameTextValue == null) {
            mNameTextValue = (EditText) findViewById(R.id.editTextName);
        }
        return mNameTextValue;
    }

    private EditText mNameTextValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity1);

        setupViews();
    }

    private void setupViews() {
        Button saveButton = (Button) findViewById(R.id.emoticonSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveButton((Button) view);
            }
        });

    }

    private void handleSaveButton(Button view) {

        Editable editable = this.getmNameTextValue().getText();
        String name = editable != null ? editable.toString(): "";

        Intent resultIntent = new Intent();
        resultIntent.putExtra(NAME_EXTRA, name);

        setResult(RESULT_OK, resultIntent); //up to here all you do is load up data
        finish(); //this actually makes it available to next activity
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
