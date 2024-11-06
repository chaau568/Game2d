import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Altar extends SuperObject {
  public OBJ_Altar() {
    name = "altar";
    try {
      image = ImageIO.read(getClass().getResourceAsStream( name + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }
}
