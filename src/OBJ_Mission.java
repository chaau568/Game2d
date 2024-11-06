import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Mission extends SuperObject {
    public OBJ_Mission() {
        name = "mission";
        try {
          image = ImageIO.read(getClass().getResourceAsStream(name + ".png"));
        } catch (IOException e) {
          e.printStackTrace();
        }
        collision = true;
      }
}

