package com.pgl8.htmlparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Creado por pepe en 19/08/14.
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
    class obtenerHTML extends AsyncTask<String, Void, Elements> {
        //private int cont=0;
        //private String html1, html2, html3;
        private static final String TAG = "Principal";
	    LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
	    ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

	    @Override
	    protected void onPreExecute() {
		    //super.onPreExecute();
		    linlaHeaderProgress.setVisibility(View.VISIBLE);
		    scrollView.setVisibility(View.GONE);
	    }

	    @Override
        protected Elements doInBackground(String... params) {
        //protected ArrayList<String> doInBackground(String... params) {
            //Antes de hacer nada hay que comprobar que existe conexión y de qué tipo
            /*ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

            //Comprobar conexión datos
            boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnectedOrConnecting();
            Log.v(TAG, "Comprobado 3g");
            //Comprobar wifi
            boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnectedOrConnecting();
            Log.v(TAG, "Comprobado wifi");
            ArrayList<String> cadenas = new ArrayList<String>();

            if (!is3g && !isWifi) {
                //Mensaje sin conexión
                Toast.makeText(getApplicationContext(), "Asegúrese de tener conexión.", Toast.LENGTH_LONG).show();
            } else {

                //Si hay conexión por datos
                if (is3g) {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet("http://www.sherry.org/es/fichafino.cfm");

                    try {
                        HttpResponse response = client.execute(request);
                        //String html;
                        InputStream in = response.getEntity().getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        //String para Notas de cata
                        StringBuilder str1 = new StringBuilder();
                        //String para Elaboración
                        StringBuilder str2 = new StringBuilder();
                        //String para Servicio
                        StringBuilder str3 = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            if (cont == 30) {
                                str1.append(line);//.replace("<br />", "\n\n"));
                            }
                            if (cont == 32 || cont == 33) {
                                str2.append(line);//.replace("<br />", "\n\n"));
                            }
                            if (cont == 36 || cont == 37) {
                                str3.append(line);//.replace("<br />", "\n\n"));
                            }
                            cont++;
                        }

                        in.close();
                        //html1 = str1.toString();
                        cadenas.add(0, str1.toString());
                        //html2 = str2.toString();
                        cadenas.add(1, str2.toString());
                        //html3 = str3.toString();
                        cadenas.add(2, str3.toString());
                        //return html;

                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                    return cadenas;
                }
                Log.v(TAG, "bucle wifi");
                //Conexión wifi
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet("http://www.sherry.org/es/fichafino.cfm");

                try {
                    HttpResponse response = client.execute(request);
                    //String html;
                    InputStream in = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    //String para Notas de cata
                    StringBuilder str1 = new StringBuilder();
                    //String para Elaboración
                    StringBuilder str2 = new StringBuilder();
                    //String para Servicio
                    StringBuilder str3 = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        if (cont == 262) {
                            str1.append(line);//.replace("<br />", "\n\n"));
                        }
                        if (cont == 267 || cont == 268) {
                            str2.append(line);//.replace("<br />", "\n\n"));
                        }
                        if (cont == 282 || cont == 283) {
                            str3.append(line);//.replace("<br />", "\n\n"));
                        }
                        cont++;
                    }

                    in.close();
                    //html1 = str1.toString();
                    cadenas.add(0, str1.toString());
                    //html2 = str2.toString();
                    cadenas.add(1, str2.toString());
                    //html3 = str3.toString();
                    cadenas.add(2, str3.toString());
                    //return html;

                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            }
            return cadenas;*/

            //Método de obtención con JSOUP
            Elements contenido;
            try {
                Document doc = Jsoup.connect("http://www.sherry.org/es/fichafino.cfm").get();
                contenido = doc.select("div.blanco");
                //Log.v(TAG, contenido.get(2).html().replaceAll("<br>","\n"));

            } catch (IOException e) {
                //e.printStackTrace();
                Log.e(TAG, "Error al cargar contenido", e);
                return null;
            }

            return contenido;
        }

        /*protected void onPostExecute(ArrayList<String> cadenas) {
            if (cadenas != null) {
                //Quitamos etiquetas HTML en el resultado
                String cata = android.text.Html.fromHtml(cadenas.get(0)).toString();
                String elab = android.text.Html.fromHtml(cadenas.get(1)).toString();
                String cons = android.text.Html.fromHtml(cadenas.get(2)).toString();

                //Cambiamos el TextView para el texto de salida
                TextView txt1 = (TextView)findViewById(R.id.textView3);
                txt1.setText(cata);
                TextView txt2 = (TextView)findViewById(R.id.textView5);
                txt2.setText(elab);
                TextView txt3 = (TextView)findViewById(R.id.textView7);
                txt3.setText(cons);
            }
        }*/

        protected void onPostExecute(Elements result) {
            if (result != null) {
                //Localizamos e inicializamos los elementos de la UI
                TextView txt1 = (TextView)findViewById(R.id.textView3);
                TextView txt2 = (TextView)findViewById(R.id.textView5);
                TextView txt3 = (TextView)findViewById(R.id.textView7);
                TextView txt4 = (TextView)findViewById(R.id.textView9);

                //Asignamos los elementos al objeto de la UI determinado
                txt1.setText(result.get(0).text());
                txt2.setText(result.get(1).text());
                txt3.setText(result.get(3).text());

                //Formateo para respetar saltos de línea en datos analíticos
                txt4.setText(Jsoup.parse(result.get(2).html().replaceAll("(?i)<br[^>]*>",
                                                                         "<pre>\n</pre>")).text());

	            //Quitamos Progress circle
	            linlaHeaderProgress.setVisibility(View.GONE);
	            scrollView.setVisibility(View.VISIBLE);
            }
        }
    }
}
