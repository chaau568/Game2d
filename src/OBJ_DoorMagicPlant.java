import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorMagicPlant extends SuperObject {
  public OBJ_DoorMagicPlant() {
    name = "doormagicplant";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}