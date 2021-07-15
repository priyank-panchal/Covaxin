package com.example.covaxin;

import android.content.Context;

import java.util.List;

public class ShowDataOfPerson {
    List<IDProofDetailsdb> lst;
    IDProofDetailsdb idProofDetailsdb;
    public ShowDataOfPerson(){

    }
    public ShowDataOfPerson(Context context){

    }

    public List<IDProofDetailsdb> getLst() {
        return lst;
    }
    public void setLst(List<IDProofDetailsdb> lst) {
        this.lst = lst;
    }
}
