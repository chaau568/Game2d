import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorMagicFire extends SuperObject {
  public OBJ_DoorMagicFire() {
    name = "doormagicfire";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}