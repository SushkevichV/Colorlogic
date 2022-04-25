package by.sva.colorlogic1;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Cristals {
    private List<Cristal> cristals = new ArrayList<>();

    public void addCristal(int id, ImageView imageView, int color){

        for (Cristal cristal : cristals){
            if (id == cristal.getId()){
                cristal.setImageView(imageView);
                cristal.setColor(color);
                return;
            }
        }

        Cristal cristal = new Cristal(id, imageView, color);
        cristals.add(cristal);
    }

    public ImageView getCristal(int id) {
        for (Cristal cristal: cristals) {
            if(cristal.getId() == id){
                return cristal.getImageView();
            }
        }
        System.out.println(id + "ImageView не получен");
        return null;
    }
}
