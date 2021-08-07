package com.example.calendarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarAdapter extends RecyclerView.Adapter {

    private String[] dayList;
    private int year;
    private int month;

//    public interface OnItemClickListener {
//        void onItemClick(View v, int position) ;
//    }
//
//    private OnItemClickListener mListener = null ;
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }


    public CalendarAdapter(String[] dayList, int year, int month)
    {
        this.dayList = dayList;
        this.year = year;
        this.month = month;
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

                        dayList[pos] = year + "/" + month + "/" + pos;

                        notifyItemChanged(pos);
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
