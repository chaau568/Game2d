import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DoorMission2 extends SuperObject {
  public OBJ_DoorMission2() {
    name = "doormission2";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( "door_lock.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
