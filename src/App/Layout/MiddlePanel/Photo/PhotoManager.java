package App.Layout.MiddlePanel.Photo;

import java.awt.*;
import java.util.ArrayList;

public class PhotoManager {
    public ArrayList<Photo> photos;
    public PhotoManager() {
        photos = new ArrayList<>();
    }
    public boolean isPhotosActive(){
        for (Photo p : photos) {
            if(p.active) return true;
        }
        return false;
    }
    public void addPhoto(Photo p) {
        photos.add(p);
    }
    public void removePhoto(Photo p) {
        System.out.println("Photo removed");
        photos.remove(p);
    }
    public void makeAllSelectable(){
        for (Photo p : photos) {
            p.canBeSelected = true;
        }
    }
    public Photo getActivePhoto() {
        for (Photo p : photos) {
            if(p.active) return p;
        }
        return null;
    }
    public void makeAllPhotosInactive(){
        for (Photo p : photos) {
            p.active = false;
        }
    }
    public void drawPhotos(Graphics g, double scaledWidth, double scaledHeight) {
        for (Photo p : photos) {
            p.drawPhoto(g, scaledWidth, scaledHeight);
        }
    }
}
