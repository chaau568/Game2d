import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Mission1 extends SuperObject {
  public OBJ_Mission1() {
    name = "mission1";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("mission.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}

