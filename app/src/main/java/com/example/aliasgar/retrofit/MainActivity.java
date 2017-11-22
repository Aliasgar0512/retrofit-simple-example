package com.example.aliasgar.retrofit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.aliasgar.retrofit.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.example.aliasgar.retrofit.MainActivity.RetroFitService.retrofit;

public class MainActivity extends AppCompatActivity
{
    public interface RetroFitService
    {
        @GET("repos/{owner}/{repo}/contributors")
        Call<List<Repo>> repos(@Path("owner")String User,@Path("repo")String repo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView
                (MainActivity.this,R.layout.activity_main);

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetroFitService retroFitService = retrofit.create
                        (RetroFitService.class);
                Call<List<Repo>>call = retroFitService.repos("square","retrofit");

                call.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Repo>> call,
                                           @NonNull Response<List<Repo>> response)
                    {
                        binding.txt1.setText(response.body().toString());
                        Log.e("Main Activity", "onResponse: " + response.body().toString());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Repo>> call,
                                          @NonNull Throwable t)
                    {
                        binding.txt1.setText("Somting went wrong");
                    }
                });
            }
        });

    }
}
