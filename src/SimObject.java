import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Nick van Endhoven, 2119719 on 20-Sep-17.
 */
public class SimObject {

    Point2D location;
    BufferedImage spritesheet;
    BufferedImage[] sprites;

    int width, heigth;
    float angle, scale;

    public SimObject(Point2D location, float scale, BufferedImage spritesheet) {
        this.location = location;
        this.spritesheet = spritesheet;
        this.scale = scale;

        heigth = spritesheet.getHeight();
        width =  spritesheet.getHeight();
        sprites = new BufferedImage[spritesheet.getWidth() / heigth];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = spritesheet.getSubimage(i * heigth, 0, heigth, heigth);
        }

    }

    void update(){
        angle += 0.01;
    };

    void draw(Graphics2D g2d){
        AffineTransform tx = new AffineTransform();

        tx.translate(location.getX(),location.getY());
        tx.scale(scale,scale);

        for(BufferedImage sprite : sprites) {
            tx.rotate(angle);
            tx.translate(-width/2, -heigth/2);
            g2d.drawImage(sprite, tx, null);
            tx.translate(width/2, heigth/2);
            tx.rotate(-angle);
            tx.translate(0,-1);
        }
    }
}
