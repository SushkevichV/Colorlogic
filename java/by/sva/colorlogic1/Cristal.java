package by.sva.colorlogic1;

import android.widget.ImageView;

public class Cristal {
    private int id;
    private ImageView imageView;
    private int color;

    public Cristal(int id, ImageView imageView, int color) {
        this.id = id;
        this.imageView = imageView;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
