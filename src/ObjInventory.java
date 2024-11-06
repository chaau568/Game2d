import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjInventory extends Entity{
    GamePanel gp;

    public ObjInventory(GamePanel gp) {
        this.gp = gp;
        getImageObject();
    }

    public void getImageObject() {
        seed = setup("seed.png");
        mission = setup("mission.png");
        mana = setup("mana.png");
        magic_plant = setup("magic_plant.png");
        magic_fire = setup("magic_fire.png");
    }

    public BufferedImage setup(String imageName) {
        UtityTool uTool = new UtityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

}
