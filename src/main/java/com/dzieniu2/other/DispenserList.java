package com.dzieniu2.other;

import java.util.ArrayList;

public class DispenserList {

    private ArrayList<FuelDispenser> dispensers;

    private int selected;

    public DispenserList(ArrayList<FuelDispenser> dispensers){
        this.dispensers = dispensers;
        selected = -1;
    }

    public FuelDispenser next(){
        if(selected<dispensers.size()-1){
            selected++;
            return dispensers.get(selected);
        }else{
            selected = 0;
            return dispensers.get(selected);
        }
    }

    public FuelDispenser previous(){
        if(selected>=1){
            selected--;
            return dispensers.get(selected);
        }else{
            selected = dispensers.size()-1;
            return dispensers.get(selected);
        }
    }

    public int getSelected(){
        return selected;
    }

    public ArrayList<FuelDispenser> getDispensers() {
        return dispensers;
    }
}
