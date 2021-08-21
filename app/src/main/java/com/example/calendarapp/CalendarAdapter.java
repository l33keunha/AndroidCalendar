package com.example.calendarapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private String[] dayList;
    private int year;
    private int month;
    private int emptyDay;
    private ArrayList<Memo> memoList;


    public CalendarAdapter(Context context, String[] dayList, int year, int month, int emptyDay, ArrayList<Memo> memoList)
    {
        this.dayList = dayList;
        this.year = year;
        this.month = month;
        this.mContext = context;
        this.emptyDay = emptyDay-1;
        this.memoList = memoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.calendar_item, parent, false);
        CalendarAdapter.DateViewHolder ca = new CalendarAdapter.DateViewHolder(view);
        return ca;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String day_text = dayList[position];
        DateViewHolder dateViewHolder = (DateViewHolder)holder;
        dateViewHolder.day_item.setText(day_text);
        String today = year + "-" + month + "-" + day_text;

        for(Memo m : memoList){
            if(m.getDate().equals(today)){
                dateViewHolder.day_item.setText(m.getContent());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dayList.length;
    }


    public class DateViewHolder extends RecyclerView.ViewHolder {
        TextView day_item;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            day_item = itemView.findViewById(R.id.day_item);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos = getBindingAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.

                        Intent intent = new Intent(mContext, MemoWriteFormActivity.class);
                        intent.putExtra("month", month);
                        intent.putExtra("year", year);
                        intent.putExtra("day", pos-emptyDay);

                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {
        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
