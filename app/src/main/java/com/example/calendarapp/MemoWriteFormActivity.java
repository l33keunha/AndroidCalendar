package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MemoWriteFormActivity extends AppCompatActivity {

    EditText inputMemoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_write_form);

        TextView today = (TextView)findViewById(R.id.today);
        inputMemoText = (EditText)findViewById(R.id.inputMemo);
        Intent intent = getIntent();
        Button memoBtn = (Button)findViewById(R.id.memoBtn);
        Button deletetBtn = (Button)findViewById(R.id.deleteBtn);

        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month",0);
        int day = intent.getIntExtra("day",0);
        String memoContent = null;
        String inputToday = year + "년" + month + "월" + day + "일";
        today.setText(inputToday);

        if(intent.getStringExtra("memoContent") != null){
            memoContent = intent.getStringExtra("memoContent").toString();
            inputMemoText.setText(memoContent);
            memoBtn.setText("수정하기");
            deletetBtn.setVisibility(View.VISIBLE);
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("USER01");

        memoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Memo memo = new Memo();
                inputMemoText = (EditText) findViewById(R.id.inputMemo);
                String inputMemo = inputMemoText.getText().toString();
                String date = year + "-" + month + "-" + day;
                String m  = "memo" + date;

                memo.setDate(date);
                memo.setContent(inputMemo);

                myRef.child(m).setValue(memo);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        deletetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = year + "-" + month + "-" + day;
                String m  = "memo" + date;

                myRef.child(m).child("content").setValue("");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }
}