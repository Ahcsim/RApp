package net.ictcampus.voektm.rapp;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private boolean click = false;

    public ImageItem(Bitmap image, String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClicked(){
        click = true;
    }

    public void setUnclicked(){
        click = false;
    }

    public boolean getClicked(){
        return click;
    }
}