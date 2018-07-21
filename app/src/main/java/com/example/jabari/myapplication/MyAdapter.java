package com.example.jabari.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import me.omidh.liquidradiobutton.LiquidRadioButton;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String[] Answers;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(String Answer);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyAdapter(Context context, String[] Answers) {
        this.context = context;
        this.Answers = Answers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.rb_Answer.setText(Answers[position]);
        holder.rb_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(holder.rb_Answer.getText().toString());
                boolean state = holder.rb_Answer.isChecked();
                if (!state)
                {
                    holder.rb_Answer.setChecked(false);
                }else{
                    holder.rb_Answer.setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Answers.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public LiquidRadioButton rb_Answer;

        public MyViewHolder(View itemView) {
            super(itemView);
            rb_Answer = (LiquidRadioButton) itemView.findViewById(R.id.rb_Answer);
        }
    }
}
