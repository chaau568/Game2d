import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorHome extends SuperObject {
  public OBJ_DoorHome() {
    name = "doorhome";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
