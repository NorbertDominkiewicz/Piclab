package App.Layout.MiddlePanel;

import App.App;
import App.Layout.MiddlePanel.Photo.Mixer.Transform;
import App.Layout.MiddlePanel.Photo.Mixer.TransformMode;
import App.Layout.MiddlePanel.Photo.Photo;
import App.Layout.MiddlePanel.Photo.PhotoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel implements Runnable {
    public App app;
    public Rectangle drawingArea;
    public Thread drawingThread;
    public PhotoManager photoManager;
    public Dimension screenSize;
    public Point mousePosition;
    public Photo activePhoto;
    public Photo photoToTransform;
    public boolean isAwaitingPhoto;
    public double scaledWidth;
    public double scaledHeight;
    public DrawingPanel(App app) {
        setOpaque(true);
        setBackground(App.FLASH_WHITE);
        startDrawingThread();
        this.app = app;
        photoManager = new PhotoManager();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initArea();
        addMouseMotion();
    }
    private void addMouseMotion() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                for (Photo photo : photoManager.photos){
                    if (photo.photoArea.contains(e.getPoint())){
                        if (photo.canBeSelected) {
                            if(photo.active){
                                photoManager.makeAllPhotosInactive();
                            }
                            else {
                                photoManager.makeAllPhotosInactive();
                                photo.active = !photo.active;
                            }
                        }
                        if (isAwaitingPhoto) {
                            photoToTransform = photo;
                        }
                        activePhoto = photo;
                        mousePosition = e.getPoint();
                        break;
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                activePhoto = null;
                mousePosition = null;
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (activePhoto != null && mousePosition != null) {
                    int dx = e.getX() - mousePosition.x;
                    int dy = e.getY() - mousePosition.y;
                    activePhoto.photoArea.x += dx;
                    activePhoto.photoArea.y += dy;
                    mousePosition = e.getPoint();
                    repaint();
                }
            }
        });
    }
    public void awaitImage(TransformMode mode) {
        isAwaitingPhoto = true;
        photoToTransform = null;
        new Thread(() -> {
            while (photoToTransform == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (Photo photo : photoManager.photos) {
                if (photo.id == photoToTransform.id) {
                    Transform.apply(photo,photoToTransform,mode);
                }
            }
            isAwaitingPhoto = false;
        }).start();
        photoManager.makeAllSelectable();
    }
    private void initArea(){
        drawingArea = new Rectangle();
        drawingArea.setLocation(20, 20);
        drawingArea.width = getWidth();
        drawingArea.height = getHeight();
    }
    private void startDrawingThread(){
        drawingThread = new Thread(this);
        drawingThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (drawingThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        //app.layout.middlePanel.optionsPanel.options.updateButtons(app);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(drawingArea.x, drawingArea.y, getWidth() - 40, getHeight() - 40, 50, 50);
        scaledWidth = screenSize.getWidth() / getWidth();
        scaledHeight = screenSize.getHeight() / getHeight();
        photoManager.drawPhotos(g,scaledWidth,scaledHeight);
    }
}
