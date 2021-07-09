package com.junglist963.task_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    RecyclerView rv;
    List<Model> model = new ArrayList<>();

    ImageAdapter adapter = new ImageAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromJson();

        rv = findViewById(R.id.rv);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.setList(model);
    }

    public void getDataFromJson() {
        String strJson = null;
        String data = "";
        try {
            InputStream inputStream = getAssets().open("dog_urls.json");
            int size = inputStream.available();
            byte [] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            strJson = new String(buffer, StandardCharsets.UTF_8);
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("urls");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                data = jsonArray.getString(i);
                model.add(new Model(data));
            }


        } catch (JSONException | IOException e) {e.printStackTrace();}
    }


}