package com.pgl8.htmlparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by pepe on 19/08/14.
 */
public class ActividadFino extends Activity{
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actfino);
        new obtenerHTML().execute();
    }
    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        super.onBackPressed();
        ActividadFino.this.finish();
    }

    //Necesita ser una tarea asíncrona
    //Haremos una clase interna para ello
    class obtenerHTML extends AsyncTask<String, Void, String> {
        private int cont=0;
        //private static final String TAG = "Principal";

        @Override
        protected String doInBackground(String... params) {
            //Log.v(TAG, control);

            //Log.v(TAG, "SI");
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.losvinosdejerez.com/productos.php?id=12");

            try {
                HttpResponse response = client.execute(request);
                String html;
                InputStream in = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder str = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    if (cont == 688 || cont == 690 || cont == 691 || cont == 692 || cont == 693 ||
                            cont == 694 || cont == 701 || cont == 703) {
                        str.append(line);//.replace("<br />", "\n\n"));
                    }
                    cont++;
                }

                in.close();
                html = str.toString();
                return html;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                //Quitamos etiquetas HTML en el resultado
                String resultado = android.text.Html.fromHtml(result).toString();

                //Cambiamos el TextView para el texto de salida
                TextView txt = (TextView)findViewById(R.id.TextView2);
                txt.setText(resultado);
            }
        }
    }
}
