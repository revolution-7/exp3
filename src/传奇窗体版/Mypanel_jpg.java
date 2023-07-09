package 传奇窗体版;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mypanel_jpg extends JPanel implements Serializable{
    Image image1;
    Image image2;
    ArrayList<Double> size_real = new ArrayList<Double>();
    ArrayList<Integer> size = new ArrayList<Integer>();
    int width =450;
    int heigth = 550;
    int x1,y1;
    int x2,y2;

    public Mypanel_jpg(hero h,monster m) {
        setBounds(0, 0, 1000, heigth);
        image1 = new ImageIcon("src/传奇窗体版/"+h.job+".jpg").getImage();
        image2 = new ImageIcon("src/传奇窗体版/"+m.name+".jpg").getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pack(image1);
        pack(image2);
        transform();
        g.drawImage(image1, x1, y1, size.get(3),size.get(4),this);
        g.drawImage(image2, x2, y2, size.get(0),size.get(1),this);
    }

    public void pack(Image im) {
        size_real.add(0,(double)im.getWidth(null));
        size_real.add(1,(double)im.getHeight(null));
        size_real.add(2,1.0);
        while(size_real.get(0)*size_real.get(2)<=width && size_real.get(1)*size_real.get(2)<=heigth) {
            size_real.set(2, size_real.get(2)+0.0000001);
        }
        while(size_real.get(0)*size_real.get(2)>width || size_real.get(1)*size_real.get(2)>heigth) {
            size_real.set(2, size_real.get(2)-0.0000001);
        }
        size_real.set(0, size_real.get(0)*size_real.get(2));
        size_real.set(1, size_real.get(1)*size_real.get(2));
    }
    public void transform() {
        int size_small = size_real.size();
        for (int i = 0;i<size_small;i++) {
            size.add(i,(int)Math.round(size_real.get(i)));
        }
        x2=500+(500-size.get(0))/2;
        y2=(heigth-size.get(1))/2;
        x1=(500-size.get(3))/2;
        y1=(heigth-size.get(4))/2;
    }
}
