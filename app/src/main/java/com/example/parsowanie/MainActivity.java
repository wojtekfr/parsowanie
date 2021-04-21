  package com.example.parsowanie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.parsowanie.databinding.ActivityMainBinding;

import org.json.JSONObject;

  public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RequestQueue queue;
    //String url = "https://jsonplaceholder.typicode.com/todos";
    String url = "https://cat-fact.herokuapp.com/facts";
    String responseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
                 binding.feed.setText(responseString);
                 binding.searchResults.setText(binding.searchPrase.getText());
                 binding.invalidateAll();
            //    queue.add(json)
            }
        });

        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, response -> responseString=response.toString(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            responseString = "FAIL";
            }
        });
        queue.add(jsonArrayRequest);

    }
}