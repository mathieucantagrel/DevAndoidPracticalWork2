package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AuthentificationActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        findViewById(R.id.AuthButton).setOnClickListener(v->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    name = findViewById(R.id.NameLogin);
                    password = findViewById(R.id.PasswordLogin);
                    result = findViewById(R.id.textViewResult);
                    URL url = null;
                    try {

                        String credentials = name.getText().toString() + ":" + password.getText().toString();

                        url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
                        urlConnection.setRequestProperty ("Authorization", basicAuth);
                        try {
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            String s = readStream(in);
                            Log.i("JFL", s);

                            JSONObject auth = new JSONObject(s);
                            String res = auth.getString("authenticated");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(res);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            urlConnection.disconnect();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        });
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}