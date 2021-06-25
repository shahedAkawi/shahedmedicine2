package com.example.shahedmedicine2.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shahedmedicine2.R;
//0
//1 new class                         //2  extends ....
public class MyUserAdapetr  extends ArrayAdapter<MyUser>
{

    //3.  fix error
    public MyUserAdapetr(@NonNull Context context, int resource) {
        super(context, resource);
    }
   //4. override getview
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //4.1 build item XML view
        View view= LayoutInflater.from(getContext()).inflate(R.layout.task_item2,parent,false);
        //4.2
        //finddviewbyid
        ImageView imgClient=view.findViewById(R.id.imgtmClient);
        ImageButton imgCall=view.findViewById(R.id.imItmCall);
        Button btnDel=view.findViewById(R.id.btnItemDel);
        TextView tvName=view.findViewById(R.id.tvitmname);

        //4.3 get data object
        final MyUser myUser = getItem(position);
        //4.4 set object data on the view
        tvName.setText(myUser.getFname());

        //4.5 add event listener
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "calling:"+myUser.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        //4.6 return view
        return view;
    }
}
