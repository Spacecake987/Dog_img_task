package com.junglist963.task_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    RecyclerView rv;
    List<Model> model = new ArrayList<>();
    List<Model> afghanModel = new ArrayList<>();
    List<Model> bassetModel = new ArrayList<>();
    List<Model> bloodModel = new ArrayList<>();
    List<Model> englishModel = new ArrayList<>();
    List<Model> ibizanModel = new ArrayList<>();
    List<Model> walkerModel = new ArrayList<>();
    List<Model> allModel = new ArrayList<>();
    List<Model> models = new ArrayList<>();
    String categorySelect = "";


    ImageAdapter adapter = new ImageAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromJson();


        rv = findViewById(R.id.rv);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(this, 2));

        searchInModel(models);
        adapter.setList(models);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.afghan){
            categorySelect = "hound-afghan";
            searchInModel(afghanModel);
            adapter.setList(afghanModel);

        }
        if (item.getItemId() == R.id.basset){
            categorySelect = "hound-basset";
            searchInModel(bassetModel);
            adapter.setList(bassetModel);

        }
        if (item.getItemId() == R.id.blood){
            categorySelect = "hound-blood";
            searchInModel(bloodModel);
            adapter.setList(bloodModel);

        }
        if (item.getItemId() == R.id.english){
            categorySelect = "hound-english";
            searchInModel(englishModel);
            adapter.setList(englishModel);

        }
        if (item.getItemId() == R.id.ibizan){
            categorySelect = "hound-ibizan";
            searchInModel(ibizanModel);
            adapter.setList(ibizanModel);

        }
        if (item.getItemId() == R.id.walker){
            categorySelect = "hound-walker";
            searchInModel(walkerModel);
            adapter.setList(walkerModel);

        }
        if (item.getItemId() == R.id.all){
            categorySelect = "breeds";
            searchInModel(allModel);
            adapter.setList(allModel);

        }

        return super.onOptionsItemSelected(item);
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
    public void searchInModel(List<Model> list){

        List<Model>insideModel = model;
        String text = "";
        for(int i=0; i < insideModel.size(); i++){
            text = insideModel.get(i).getImgUrl();
            int a = text.indexOf(categorySelect);

            if (a >= 0) {
                list.add(new Model(text));
            }
        }

    }
}