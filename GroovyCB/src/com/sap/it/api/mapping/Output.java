package com.sap.it.api.mapping;

import java.util.ArrayList;

public class Output {

    private ArrayList<String> str;

    Output(){
        str = new ArrayList<String>();
    }

    public void addValue(String value){
        //add code
        str.add(value);
    }

    public ArrayList<String> getValue(){
        return str;
    }
}
