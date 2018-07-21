package com.example.jabari.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jabari.myapplication.model.Questionnaire;
import com.example.jabari.myapplication.webService.APIClient;
import com.example.jabari.myapplication.webService.APIInterface;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    RecyclerView rv_Answers;
    TextView tv_Question;
    List<Questionnaire> questionnaireList;
    Map<String, String> result = new HashMap<String, String>();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Question = (TextView) findViewById(R.id.tv_Question);
        rv_Answers = (RecyclerView) findViewById(R.id.rv_Answer);

        QuestionnaireRequest();
    }

    public void onClickNext(View view) {
        if (index < questionnaireList.size() - 1) {
            index++;
            CreateQuestion(questionnaireList.get(index).getQuestion(), questionnaireList.get(index).getAnswers());
        }
    }
    public void onClickPrev(View view){
        try{
            if(index < questionnaireList.size() - 1){
                index--;
                CreateQuestion(questionnaireList.get(index).getQuestion(),questionnaireList.get(index).getAnswers());
            }
        }
        catch (Exception o){
            Toast.makeText(MainActivity.this,"سوالی وجود ندارد",Toast.LENGTH_LONG);}
        }

    private void CreateQuestion(String Question, String[] Answers) {
        tv_Question.setText(Question);
        MyAdapter adapter = new MyAdapter(MainActivity.this, Answers);
        rv_Answers.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rv_Answers.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    private void QuestionnaireRequest() {
        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<List<Questionnaire>> call = apiInterface.getQuestionnaire();
        call.enqueue(new Callback<List<Questionnaire>>() {
            @Override
            public void onResponse(Call<List<Questionnaire>> call, Response<List<Questionnaire>> response) {
                if (response.isSuccessful()) {
                    questionnaireList = response.body();
                    CreateQuestion(questionnaireList.get(index).getQuestion(), questionnaireList.get(index).getAnswers());
                } else {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questionnaire>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(String Answer) {
        result.put(questionnaireList.get(index).getQuestion(),Answer);
    }
}