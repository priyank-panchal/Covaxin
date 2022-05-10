package com.example.covaxin;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MixActivity extends BaseAdapter {
    Activity activity;
    List<IDProofDetailsdb> lst;
    public MixActivity(Activity activity,List<IDProofDetailsdb> lst) {
        this.activity = activity;
        IDproofDatabaseOperation idofdata = new IDproofDatabaseOperation(activity);
        this.lst = idofdata.getAllData();
    }
    @Override
    public int getCount() {
        return lst.size();
    }
    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
                return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View person;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        person = inflater.inflate(R.layout.tableshow,parent,false);
        TextView name = person.findViewById(R.id.PhotoIdNumber2);
        TextView gender = person.findViewById(R.id.GenderName2);
        TextView idnumber = person.findViewById(R.id.Nameofuser1);
        TextView PhotoIdName = person.findViewById(R.id.PhotoIdName2);
        TextView yearofbirth = person.findViewById(R.id.yearofbirth2);
        Button btn = person.findViewById(R.id.Delete);
        Button Schedule = person.findViewById(R.id.scheduleApp);
        IDProofDetailsdb idProofDetailsdb = (IDProofDetailsdb) this.getItem(position);
        name.setText(idProofDetailsdb.getID_name());
        gender.setText(idProofDetailsdb.getGender());
        idnumber.setText(idProofDetailsdb.getID_num());
        PhotoIdName.setText(idProofDetailsdb.getID_type());
        yearofbirth.setText(String.valueOf(idProofDetailsdb.getYear()));
        btn.setTag(position);
        Schedule.setTag(position);
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = (int)Schedule.getTag();
                Intent in = new Intent(parent.getContext(),SearchByPin.class);
                in.putExtra("user",String.valueOf(x));
                parent.getContext().startActivity(in);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             IDproofDatabaseOperation iDproofDatabaseOperation = new IDproofDatabaseOperation(activity);
             boolean isValid = iDproofDatabaseOperation.deleteOne(idProofDetailsdb);
                int pos = (int)btn.getTag();
                lst.remove(pos);
                MixActivity.this.notifyDataSetChanged();
            }
        });
        return person;
    }
}