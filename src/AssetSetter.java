public class AssetSetter {
  GamePanel gp;

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setObject() {

    gp.obj[0] = new OBJ_Mission();
    gp.obj[0].worldX = 1 * gp.tileSize;
    gp.obj[0].worldY = 58 * gp.tileSize;

    gp.obj[1] = new OBJ_Magic_Plant();
    gp.obj[1].worldX = 38 * gp.tileSize;
    gp.obj[1].worldY = 41 * gp.tileSize;

    gp.obj[2] = new OBJ_Magic_Fire();
    gp.obj[2].worldX = 78 * gp.tileSize;
    gp.obj[2].worldY = 18 * gp.tileSize;

    gp.obj[3] = new OBJ_Hint();
    gp.obj[3].worldX = 50 * gp.tileSize;
    gp.obj[3].worldY = 56 * gp.tileSize;

    gp.obj[4] = new OBJ_Mission1();
    gp.obj[4].worldX = 13 * gp.tileSize;
    gp.obj[4].worldY = 34 * gp.tileSize;

    gp.obj[5] = new OBJ_Mission2();
    gp.obj[5].worldX = 10 * gp.tileSize;
    gp.obj[5].worldY = 10 * gp.tileSize;

    gp.obj[6] = new OBJ_Mission3();
    gp.obj[6].worldX = 80 * gp.tileSize;
    gp.obj[6].worldY = 12 * gp.tileSize;

    gp.obj[7] = new OBJ_Door();
    gp.obj[7].worldX = 40 * gp.tileSize;
    gp.obj[7].worldY = 19 * gp.tileSize;

    gp.obj[8] = new OBJ_DoorHome();
    gp.obj[8].worldX = 5 * gp.tileSize;
    gp.obj[8].worldY = 56 * gp.tileSize;

    gp.obj[9] = new OBJ_DoorMission1();
    gp.obj[9].worldX = 12 * gp.tileSize;
    gp.obj[9].worldY = 36 * gp.tileSize;

    gp.obj[10] = new OBJ_DoorMission2();
    gp.obj[10].worldX = 13 * gp.tileSize;
    gp.obj[10].worldY = 10 * gp.tileSize;

    gp.obj[11] = new OBJ_DoorMission3();
    gp.obj[11].worldX = 80 * gp.tileSize;
    gp.obj[11].worldY = 10 * gp.tileSize;

    gp.obj[12] = new OBJ_DoorMagicPlant();
    gp.obj[12].worldX = 35 * gp.tileSize;
    gp.obj[12].worldY = 43 * gp.tileSize;

    gp.obj[13] = new OBJ_DoorMagicFire();
    gp.obj[13].worldX = 78 * gp.tileSize;
    gp.obj[13].worldY = 20 * gp.tileSize;

    gp.obj[14] = new OBJ_Altar();
    gp.obj[14].worldX = 40 * gp.tileSize;
    gp.obj[14].worldY = 17 * gp.tileSize;

    gp.obj[15] = new OBJ_Seed();
    gp.obj[15].worldX = 11 * gp.tileSize;
    gp.obj[15].worldY = 17 * gp.tileSize;
  }
}
