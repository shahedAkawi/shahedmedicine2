package com.example.samihtaskmngr2019.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.samihtaskmngr2019.R;

import java.util.zip.Inflater;

public class TasksAdapter extends ArrayAdapter<MyTask>
{

    /**
     *
     * @param context
     */
    public TasksAdapter(@NonNull Context context) {
        super(context, R.layout.task_item);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //building item view
        View vitem= LayoutInflater.from(getContext()).inflate(R.layout.task_item,parent,false);
        TextView tvTitle=vitem.findViewById(R.id.itmTvTitle);
        TextView tvSubject=vitem.findViewById(R.id.itmTvSubject);
        RatingBar rbPrio =vitem.findViewById(R.id.itmRatingPrio);
        CheckBox cbIsCompleted=vitem.findViewById(R.id.itmChbxIsCompleted);
        ImageView ivInfo =vitem.findViewById(R.id.itmImgInfo);

        //getting data source
        MyTask myTask = getItem(position);

        //todo טיפול באירוע מחיקה


        //connect item view to data source
        tvTitle.setText(myTask.getTitle());
        tvSubject.setText(myTask.getSubject());
        rbPrio.setRating(myTask.getImportant());
        cbIsCompleted.setChecked(false);



        return vitem;
    }
}
