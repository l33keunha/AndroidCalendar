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
        Intent intent = getIntent();


        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month",0);
        int day = intent.getIntExtra("day",0);
        String inputToday = year + "년" + month + "월" + day + "일";
        today.setText(inputToday);

        Button memoBtn = (Button)findViewById(R.id.memoBtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

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

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });



    }
}