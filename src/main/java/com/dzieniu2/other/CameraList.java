package com.dzieniu2.other;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class CameraList {

    private ArrayList<Image> cameras;

    private int selected;

    public CameraList(ArrayList<Image> cameras){
        this.cameras = cameras;
        selected = -1;
    }

    public Image next(){
        if(selected<cameras.size()-1){
            selected++;
            return cameras.get(selected);
        }else{
            selected = 0;
            return cameras.get(selected);
        }
    }

    public Image previous(){
        if(selected>=1){
            selected--;
            return cameras.get(selected);
        }else{
            selected = cameras.size()-1;
            return cameras.get(selected);
        }
    }

    public int getSelected(){
        return selected;
    }
}
