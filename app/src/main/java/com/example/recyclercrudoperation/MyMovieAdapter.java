package com.example.recyclercrudoperation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyMovieAdapter extends RecyclerView.Adapter<MyMovieAdapter.ViewHolder> {

    ArrayList<MyMovieData> List;
    Context context;


    public MyMovieAdapter(ArrayList<MyMovieData> list, Context context) {
        List = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MyMovieData myMovieDataList = List.get(position);
        holder.textViewName.setText(myMovieDataList.getMovieName());
        holder.textViewDate.setText(myMovieDataList.getMovieDate());
        holder.movieImage.setImageResource(myMovieDataList.getMovieImage());
        setAnimation(holder.itemView,position);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_layout);

                EditText editText= dialog.findViewById(R.id.et_first);
                EditText editTextSecond = dialog.findViewById(R.id.et_Second);
                Button button = dialog.findViewById(R.id.btn_add);
                TextView textView = dialog.findViewById(R.id.tv_heading);
                 button.setText("Update");
                 textView.setText("Update Record");


                  editText.setText(myMovieDataList.getMovieName());
                  editTextSecond.setText(myMovieDataList.getMovieDate());

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String name = "";
                            String number = "";


                            if(editText.getText().toString().equals(""))
                            {

                                Toast.makeText(context, "Fill Edit Text", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                name = editText.getText().toString();

                            }


                            if(editTextSecond.getText().toString().equals(""))
                            {

                                Toast.makeText(context, "Fill Movie Date", Toast.LENGTH_SHORT).show();


                            }
                            else
                            {
                                number  = editTextSecond.getText().toString();

                            }

                                List.set(position,new MyMovieData(name,number,R.drawable.batman));
                                notifyItemChanged(position);
                                dialog.dismiss();

                        }
                    });
                    dialog.show();


            }
        });


            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(context).
                            setTitle("Delete").
                            setMessage("Are you Sure You Want To Delete").
                            setIcon(R.drawable.ic_delete).
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    List.remove(position);
                                    notifyItemRemoved(position);

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                            builder.show();




                    return true;
                }
            });



    }
    private  void setAnimation(View viewToAnimation,int position)
    {
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimation.setAnimation(slideIn);

    }

    @Override
    public int getItemCount() {

        return List.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);

        }
    }

}
