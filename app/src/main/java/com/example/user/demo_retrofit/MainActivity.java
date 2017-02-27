package com.example.user.demo_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.demo_retrofit.model.Repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static Retrofit retrofit = null;
    public TextView textView;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);
        textView2 = (TextView) findViewById(R.id.text_view2);
        textView3 = (TextView) findViewById(R.id.text_view3);
        textView4 = (TextView) findViewById(R.id.text_view4);
        textView5 = (TextView) findViewById(R.id.text_view5);
        textView6 = (TextView) findViewById(R.id.text_view6);

        GitHubService
                .Factory
                .getInstance()
                .getRepo("octocat","all")
                .enqueue(new Callback<List<Repositories>>() {
            @Override
            public void onResponse(Call<List<Repositories>> call, Response<List<Repositories>> response) {
                textView.setText(response.body().get(0).getName().toString());
                textView2.setText(response.body().get(0).getDescription().toString());
                textView3.setText(response.body().get(0).getCreatedAt().toString());
                textView4.setText(response.body().get(0).getUpdatedAt().toString());
                textView5.setText(response.body().get(0).getLanguage().toString());
                textView6.setText(String.valueOf(response.body().get(0).getStargazersCount()));
            }

            @Override
            public void onFailure(Call<List<Repositories>> call, Throwable t) {
                Log.d("Fail", t.getMessage());
            }
        });
    }


}
