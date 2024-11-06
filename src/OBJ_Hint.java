import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Hint extends SuperObject {
  public OBJ_Hint() {
    name = "hint";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("mission.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
