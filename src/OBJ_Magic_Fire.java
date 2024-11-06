import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Magic_Fire extends SuperObject {
  public OBJ_Magic_Fire() {
    name = "magic_fire";
    try {
      image = ImageIO.read(getClass().getResourceAsStream(name + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
