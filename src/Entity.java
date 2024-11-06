import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2, up_dig1, up_dig2, down_dig1, down_dig2,
            left_dig1, left_dig2, right_dig1, right_dig2, up_create1, up_create2, down_create1, down_create2,
            left_create1, left_create2, right_create1, right_create2, up_keep1, up_keep2, down_keep1, down_keep2,
            left_keep1, left_keep2, right_keep1, right_keep2, seed, mission, mana, magic_plant, magic_fire;
    public String direction;
    public String preDirection;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY; 
    public boolean collisioOn = false;
}
