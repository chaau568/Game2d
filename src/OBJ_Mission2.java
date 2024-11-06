import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Mission2 extends SuperObject {
  public OBJ_Mission2() {
    name = "mission2";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("mission.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}