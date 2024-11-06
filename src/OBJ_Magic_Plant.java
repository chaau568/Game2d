import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Magic_Plant extends SuperObject {
  public OBJ_Magic_Plant() {
    name = "magic_plant";
    try {
      image = ImageIO.read(getClass().getResourceAsStream(name + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
