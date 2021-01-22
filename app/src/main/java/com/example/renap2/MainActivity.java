package com.example.renap2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interfas.jsonPruebaApi;
import Retrofit.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView texjason;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texjason = findViewById(R.id.json);
        getPost();

    }
    private void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPruebaApi JsonPruebaApi = retrofit.create(Interfas.jsonPruebaApi.class);
        Call<Post> call = JsonPruebaApi.getPost();
        call.enqueue(new Callback<Post>(){
            @Override
            public void onResponse(Call<Post> Call, Response<Post> response) {
                if (!response.isSuccessful()){
                    texjason.setText( "codigo HOLA "+ response.code());
                    return ;
                }
                Post ListP = response.body();

                    String content  = "";
                    content += "id: " + ListP.getId() + "\n";
                    content += "type: " + ListP.getType() + "\n";
                    content += "setup: " + ListP.getSetup() + "\n";
                    content += "punchline: " + ListP.getPunchline() + "\n";
                    texjason.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                texjason.setText(t.getMessage());
            }
        });
    }
}