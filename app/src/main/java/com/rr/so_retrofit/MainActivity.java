package com.rr.so_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rr.so_retrofit.data.AnswerAdapter;
import com.rr.so_retrofit.data.model.Item;
import com.rr.so_retrofit.data.model.SOAnswersResponse;
import com.rr.so_retrofit.data.remote.APIUtils;
import com.rr.so_retrofit.data.remote.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private AnswerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.answersList);

        //Set Adapter
        mAdapter = new AnswerAdapter(new ArrayList<Item>(0), new AnswerAdapter.PostItemclickListener(){
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this,
                        "Post id is : "+id, Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        //Set layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set item decoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mService = APIUtils.getSOService();
        loadAnswers();

    }

    public void loadAnswers(){
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d(TAG, "All is well posts loaded");
                }else{
                    Log.d(TAG, "Something went wrong and the code is "+response.code());
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        });
    }
}
