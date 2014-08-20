package com.pgl8.htmlparser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
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

    public void actividadFino(View view) {
        // Intent for the activity to open when user selects the notification
        Intent detailsIntent = new Intent(this, ActividadFino.class);
        startActivity(detailsIntent);

    }

    public void actividadManzanilla(View view) {
        // Intent for the activity to open when user selects the notification
        Intent detailsIntent = new Intent(this, ActividadManzanilla.class);
        startActivity(detailsIntent);

    }
}
