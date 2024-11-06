import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorMission3 extends SuperObject {
  public OBJ_DoorMission3() {
    name = "doormission3";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
