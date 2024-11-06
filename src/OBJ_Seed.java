import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Seed extends SuperObject {
  public OBJ_Seed() {
    name = "seed";
    try {
      image = ImageIO.read(getClass().getResourceAsStream(name + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
