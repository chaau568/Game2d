import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Mission3 extends SuperObject {
  public OBJ_Mission3() {
    name = "mission3";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("mission.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}