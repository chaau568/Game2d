import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorMission1 extends SuperObject {
  public OBJ_DoorMission1() {
    name = "doormission1";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
