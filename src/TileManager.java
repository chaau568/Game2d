import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class TileManager {
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNum[][];
  public int spriteCounter = 0;
  public int spriteBase = 1750;
  public ArrayList<plantGrowth> growthList = new ArrayList<>();
  public ArrayList<changeMapMissionCompleted> missionCompletedList = new ArrayList<>();

  public TileManager(GamePanel gp) {
    this.gp = gp;
    tile = new Tile[30];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    getTileImage();
    // Setup
    // updateGrowth(11, 37, 42);
    // growthList.add(new plantGrowth(this, 11, 37, 42));
  }

  // Select if new game or load game ==> load some map
  public void setTileStart() {
    if (gp.player.checkStartGame == 1) {
      loadMap("world1.txt");
    } else if (gp.player.checkStartGame == 2) {
      loadMap("world1save.txt");
      checkTileAfterSave();
    } else {
      loadMap("world1.txt");
    }
  }

  // Setup Tile Images
  public void getTileImage() {
    setup(0, "grass1.png", false);
    setup(1, "way_vertical.png", false);
    setup(2, "way_horizontal.png", false);
    setup(3, "wood.png", false);
    setup(4, "wall1.png", true);
    setup(5, "water.png", true);
    setup(6, "tree2.png", true);
    setup(7, "tree3.png", true);
    setup(8, "grass3.png", false);
    setup(9, "water2.png", true);
    setup(10, "plant1_v2.png", false);
    setup(11, "plant2_v2.png", false);
    setup(12, "plant3_v2.png", false);
    setup(13, "plant4_v2.png", false);
    setup(14, "plant5_v2.png", false);
    setup(15, "plant6_v2.png", true);
  }

  public void setup(int index, String imageName, boolean collision) {
    UtityTool uTool = new UtityTool();

    try {
      tile[index] = new Tile();
      tile[index].image = ImageIO.read(getClass().getResourceAsStream(imageName));
      tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
      tile[index].collision = collision;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Load map
  public void loadMap(String filePath) {
    try {
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      int col = 0;
      int row = 0;
      while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
        String line = br.readLine();
        while (col < gp.maxWorldCol) {
          String numbers[] = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          mapTileNum[col][row] = num;
          col++;
        }
        if (col == gp.maxWorldCol) {
          col = 0;
          row++;
        }
      }
      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Save map
  public void saveMap(String filePath) {
    try {
      FileWriter fw = new FileWriter(filePath);
      BufferedWriter bw = new BufferedWriter(fw);

      for (int row = 0; row < gp.maxWorldRow; row++) {
        StringBuilder line = new StringBuilder();
        for (int col = 0; col < gp.maxWorldCol; col++) {
          line.append(mapTileNum[col][row]);
          if (col < gp.maxWorldCol - 1) {
            line.append(" ");
          }
        }
        bw.write(line.toString());
        bw.newLine();
      }
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Method for edit map
  public void editMap(int x, int y, String direction, String preDirection) {
    for (int col = 0; col < gp.maxWorldCol; col++) {
      for (int row = 0; row < gp.maxWorldRow; row++) {
        // dig
        if ((col == x) && (row == y) && direction == "dig"
            && (mapTileNum[col][row] == 0 || mapTileNum[col][row] == 6 || mapTileNum[col][row] == 14)) {
          mapTileNum[col][row] = 10;
          growthList.add(new plantGrowth(this, 10, col, row));
        }
        else if ((col == x) && (row == y) && direction == "dig" && mapTileNum[col][row] == 7) {
            mapTileNum[col][row] = 8;
            gotMana();
        }
        // create
        else if ((col == x) && (row == y) && (mapTileNum[col][row] == 10) && direction == "create") {
          if (gp.player.fruitNum >= 1 && gp.player.manaNum >= 3) {
            mapTileNum[col][row] = 11;
            growPlants(col, row, 11);
            gp.player.fruitNum--;
            gp.player.growthPlantNum++;
            gp.player.manaNum -= 3;
          }
        }
        // keep
        else if ((col == x) && (row == y) && (mapTileNum[col][row] == 15) && direction == "keep") {
          mapTileNum[col][row] = 14;
          growPlants(col, row, 14);
          keepFruit();
        }
      }
    }
    // saveMap("world1save.txt");
  }

  // update tileNum ==> They can growth.
  public void growPlants(int x, int y, int numT) {
    int col, row;
    for (int i = 0; i < growthList.size(); i++) {
      col = growthList.get(i).getCol();
      row = growthList.get(i).getRow();
      if (col == x && row == y) {
        growthList.get(i).setNumTile(numT);
        break;
      }
    }
  }

  // Check tile after save
  public void checkTileAfterSave() {
    int col, row, temp;
    for (int i = 0; i < gp.maxWorldCol; i++) {
      for (int j = 0; j < gp.maxWorldRow; j++) {
        temp = 0;
        for (int x = 10; x <= 15; x++) {
          if (mapTileNum[i][j] == x) {
            col = i;
            row = j;
            if (growthList.isEmpty()) {
              growthList.add(new plantGrowth(this, x, col, row));
              temp = 1;
            } else {
              for (int k = 0; k < growthList.size(); k++) {
                if (growthList.get(k).getCol() == col && growthList.get(k).getRow() == row) {
                } else {
                  growthList.add(new plantGrowth(this, x, col, row));
                  temp = 1;
                  break;
                }
              }
            }
            if (temp == 1) {
              break;
            }
          }
        }
      }
    }
  }

  // Cut tree evil ==> it can random num of mana
  public void gotMana() {
    int random = (int) (Math.random() * 3);
    if (random == 0) {
      random = 1;
    }
    gp.player.manaNum += random;
  }

  // Keep fruit ==> it can random num of fruit
  public void keepFruit() {
    int random = (int) (Math.random() * 7);
    if (random == 0) {
      random = 1;
    }
    gp.player.fruitNum += random;
    gotMana();
  }

  // Update tile growth into map for save.
  public void updateGrowth(int tileN, int x, int y) {
    mapTileNum[x][y] = tileN;
    saveMap("world1save.txt");
  }

  // Check this tile can dig or create ==> (0 = grass, 6 = tree, 9 = ground for
  // plant);
  public boolean checkCanChange(int x, int y, int check) {
    if (mapTileNum[x][y] == check) {
      return true;
    }
    return false;
  }

  // Update time for growth
  public void update() {
    for (int i = 0; i < growthList.size(); i++) {
      growthList.get(i).growth();
    }
    for (int i = 0; i < growthList.size(); i++) {
      if (growthList.get(i).getTimeM() == 0 && !growthList.isEmpty()) {
        growthList.remove(i);
      }
    }
    if (gp.player.missionCompleted == 1 &&  gp.player.checkMissionCompleted == 0) {
      for (int i = 0; i < gp.maxWorldCol; i++) {
        for (int j = 0; j < gp.maxWorldRow; j++) {
          for (int x = 7; x <= 9; x++) {
            if (mapTileNum[i][j] == x) {
              if (x == 7) {
                updateGrowth(6, i, j);
              }
              else if (x == 8) {
                 updateGrowth(0, i, j);
              } else {
                updateGrowth(5, i, j);
              }
            }
          }
        }
      }
      // for (int i = 0; i < missionCompletedList.size(); i++) {
      //   missionCompletedList.get(i).changeMap();
      // }
    }
  }

  // Update map after Mission Completed
  public void missionCompleteChangeMap() {
    int count = 0;
    for (int i = 0; i < gp.maxWorldCol; i++) {
      for (int j = 0; j < gp.maxWorldRow; j++) {
        for (int x = 7; x <= 9; x++) {
          if (mapTileNum[i][j] == x) {
            missionCompletedList.add(new changeMapMissionCompleted(this, x, i, j));
            System.out.println(count+ ":  " + missionCompletedList.get(count).toString() + " " + x + " col: " + i + " rol: " + j);
            count++;
            break;
          }
        }
      }
    }
  }

  // Draw map
  public void draw(Graphics2D g2) {
    int worldRow = 0;
    int worldCol = 0;

    // if (gp.player.missionCompleted == 1 && gp.player.checkMissionCompleted == 0) {
    //   missionCompleteChangeMap();
    //   gp.player.checkMissionCompleted = 1;
    // }

    update();

    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
      int tileNum = mapTileNum[worldCol][worldRow];
      int worldX = worldCol * gp.tileSize;
      int worldY = worldRow * gp.tileSize;
      int screenX = worldX - gp.player.worldX + gp.player.screenX;
      int screenY = worldY - gp.player.worldY + gp.player.screenY;

      if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
          worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
          worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
          worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
      }

      worldCol++;

      if (worldCol == gp.maxWorldCol) {
        worldCol = 0;
        worldRow++;
      }
    }
  }

}
