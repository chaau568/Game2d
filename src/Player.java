
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    SaveData saveData = new SaveData();
    UI ui = new UI(gp);

    public final int screenX;
    public final int screenY;

    // Setting
    // Count show ui
    public int positionDigX, positionDigY, fruitNum, manaNum, missionNum, magicPlantNum, magicFireNum, hintNum,
            miss1Num, fruitMissionNum,
            miss2Num, miss3Num, unLockDoorNum, unLockHomeNum, unLockMission1Num, unLockMission2Num, unLockMission3Num,
            unLockPlantNum, unLockFireNum, altarNum, growthPlantNum, gotSeedNum, missionCompletNum;
    int countObject = 0;
    int countDig = 0;
    int countCreate = 0;
    int countKeep = 0;
    int[] loadData = new int[25];
    boolean checkCreate, checkKeep, checkObject;
    // Use for save
    public int checkStartGame, checkGotMission, checkGotMagicFire, checkGotMagicPlant, clearMision, checkGotHint,
            checkMission1, checkMission2, checkMission3, countMission, checkUnLockDoor, checkUnLockHome,
            checkUnLockMission1, checkUnLockMission2, checkUnLockMission3, checkUnLockPlant, checkUnLockFire, gotSeed,
            missionCompleted;
    public int magicMode = 0, checkMissionCompleted = 0, saveDataPlayer;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // ทำให้ตำแหน่ง screenX อยู่ตรงกลาง
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // ทำให้ตำแหน่ง screenํ อยู่ตรงกลาง

        // Set player solid area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getPlayerImage();
    }

    public void setUpPlayerData() {
        if (checkStartGame == 1) {
            saveData.save(25, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            worldX = 4 * gp.tileSize;
            worldY = 57 * gp.tileSize;
            fruitNum = 0;
            manaNum = 15;
            checkGotMission = 0;
            checkGotMagicPlant = 0;
            checkGotMagicFire = 0;
            checkGotHint = 0;
            checkMission1 = 0;
            checkMission2 = 0;
            checkMission3 = 0;
            countMission = 0;
            checkUnLockDoor = 0;
            checkUnLockHome = 0;
            checkUnLockMission1 = 0;
            checkUnLockMission2 = 0;
            checkUnLockMission3 = 0;
            checkUnLockPlant = 0;
            checkUnLockFire = 0;
            growthPlantNum = 0;
            gotSeed = 0;
            missionCompleted = 0;
        } else if (checkStartGame == 2) {
            loadData = saveData.load();
            worldX = loadData[0] * gp.tileSize;
            worldY = loadData[1] * gp.tileSize;
            fruitNum = loadData[2];
            manaNum = loadData[3];
            checkGotMission = loadData[4];
            checkGotMagicPlant = loadData[5];
            checkGotMagicFire = loadData[6];
            checkGotHint = loadData[7];
            checkMission1 = loadData[8];
            checkMission2 = loadData[9];
            checkMission3 = loadData[10];
            countMission = loadData[11];
            checkUnLockDoor = loadData[12];
            checkUnLockHome = loadData[13];
            checkUnLockMission1 = loadData[14];
            checkUnLockMission2 = loadData[15];
            checkUnLockMission3 = loadData[16];
            checkUnLockPlant = loadData[17];
            checkUnLockFire = loadData[18];
            growthPlantNum = loadData[19];
            gotSeed = loadData[20];
            missionCompleted = loadData[21];
            fruitMissionNum = loadData[22];
        }
    }

    public void setDefaultValues() {
        setUpPlayerData();
        speed = 4; // 4
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setup("up1.png");
        up2 = setup("up2.png");
        left1 = setup("left1.png");
        left2 = setup("left2.png");
        right1 = setup("right1.png");
        right2 = setup("right2.png");
        down1 = setup("down1.png");
        down2 = setup("down2.png");

        up_dig1 = setup("up-dig1.png");
        up_dig2 = setup("up-dig2.png");
        down_dig1 = setup("down-dig1.png");
        down_dig2 = setup("down-dig2.png");
        left_dig1 = setup("left-dig1.png");
        left_dig2 = setup("left-dig2.png");
        right_dig1 = setup("right-dig1.png");
        right_dig2 = setup("right-dig2.png");

        up_create1 = setup("up-create1.png");
        up_create2 = setup("up-create2.png");
        down_create1 = setup("down-create1.png");
        down_create2 = setup("down-create2.png");
        left_create1 = setup("left-create1.png");
        left_create2 = setup("left-create2.png");
        right_create1 = setup("right-create1.png");
        right_create2 = setup("right-create2.png");

        up_keep1 = setup("up-keep1.png");
        up_keep2 = setup("up-keep2.png");
        down_keep1 = setup("down-keep1.png");
        down_keep2 = setup("down-keep2.png");
        left_keep1 = setup("left-keep1.png");
        left_keep2 = setup("left-keep2.png");
        right_keep1 = setup("right-keep1.png");
        right_keep2 = setup("right-keep2.png");
    }

    public BufferedImage setup(String imageName) {
        UtityTool uTool = new UtityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void update() {

        // Save
        if (saveDataPlayer == 1) {
            saveData.save((worldX / gp.tileSize) + 1, (worldY / gp.tileSize) + 1, fruitNum, manaNum,
                    checkGotMission, checkGotMagicPlant, checkGotMagicFire,
                    checkGotHint, checkMission1, checkMission2, checkMission3, countMission, checkUnLockDoor,
                    checkUnLockHome, checkUnLockMission1, checkUnLockMission2, checkUnLockMission3,
                    checkUnLockPlant, checkUnLockFire, growthPlantNum, gotSeed, missionCompleted, fruitMissionNum);
            gp.tileM.saveMap("world1save.txt");
        }
        saveDataPlayer = 0;
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.keepPressed == true
                || keyH.magicPressed == true) {
            if (keyH.upPressed == true) {
                direction = preDirection = "up";
            } else if (keyH.downPressed == true) {
                direction = preDirection = "down";
            } else if (keyH.leftPressed == true) {
                direction = preDirection = "left";
            } else if (keyH.rightPressed == true) {
                direction = preDirection = "right";
            } else if (keyH.magicPressed == true) {
                if (magicMode == 1 && manaNum >= 3) {
                    direction = "create";
                } else if (magicMode == 2) {
                    direction = "dig";
                } else {
                    direction = "xxx";
                }
            } else if (keyH.keepPressed == true) {
                direction = "keep";
            }

            // Check can create
            checkCreate = gp.tileM.checkCanChange((worldX / gp.tileSize), (worldY / gp.tileSize), 10);

            // Check can keep
            checkKeep = gp.tileM.checkCanChange((worldX / gp.tileSize), (worldY / gp.tileSize), 15);

            // Check tile collision
            collisioOn = false;
            gp.cChecker.checkTile(this);

            // Check Object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // If collsion is false, player can move
            if (collisioOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        countDig = countCreate = countKeep = 0;
                        break;
                    case "down":
                        worldY += speed;
                        countDig = countCreate = countKeep = 0;
                        break;
                    case "left":
                        worldX -= speed;
                        countDig = countCreate = countKeep = 0;
                        break;
                    case "right":
                        worldX += speed;
                        countDig = countCreate = countKeep = 0;
                        break;
                    case "dig":
                        countDig++;
                        if (countDig > 60) {
                            gp.tileM.editMap((worldX / gp.tileSize), (worldY / gp.tileSize), direction,
                                    preDirection);
                            countDig = 0;
                        }
                        break;
                    case "create":
                        if (checkCreate == true) {
                            countCreate++;
                            if (countCreate > 40) {
                                gp.tileM.editMap((worldX / gp.tileSize), (worldY / gp.tileSize), direction,
                                        preDirection);
                                countCreate = 0;
                            }
                        }
                        break;
                    case "keep":
                        if (checkKeep == true) {
                            countKeep++;
                            if (countKeep > 30) {
                                gp.tileM.editMap((worldX / gp.tileSize), (worldY / gp.tileSize), direction,
                                        preDirection);
                                countKeep = 0;
                            }
                        }
                        break;
                    case "xxx":
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        // Check got object
        if (missionNum == 1) {
            countObject++;
            if (countObject > 500) {
                missionNum = 0;
                countObject = 0;
            }
        }
        if (magicPlantNum == 1) {
            countObject++;
            if (countObject > 250) {
                magicPlantNum = 0;
                countObject = 0;
            }
        }
        if (magicFireNum == 1) {
            countObject++;
            if (countObject > 250) {
                magicFireNum = 0;
                countObject = 0;
            }
        }
        if (hintNum == 1) {
            countObject++;
            if (countObject > 500) {
                hintNum = 0;
                countObject = 0;
            }
        }
        if (miss1Num == 1) {
            countObject++;
            if (countObject > 500) {
                miss1Num = 0;
                countObject = 0;
            }
        }
        if (miss2Num == 1) {
            countObject++;
            if (countObject > 500) {
                miss2Num = 0;
                countObject = 0;
            }
        }
        if (miss3Num == 1) {
            countObject++;
            if (countObject > 500) {
                miss3Num = 0;
                countObject = 0;
            }
        }
        if (unLockHomeNum == 1) {
            countObject++;
            if (countObject > 180) {
                unLockHomeNum = 0;
                countObject = 0;
            }
        }
        if (unLockDoorNum == 1) {
            countObject++;
            if (countObject > 180) {
                unLockDoorNum = 0;
                countObject = 0;
            }
        }
        if (unLockMission1Num == 1) {
            countObject++;
            if (countObject > 180) {
                unLockMission1Num = 0;
                countObject = 0;
            }
        }
        if (unLockMission2Num == 1) {
            countObject++;
            if (countObject > 180) {
                unLockMission2Num = 0;
                countObject = 0;
            }
        }
        if (unLockMission3Num == 1) {
            countObject++;
            if (countObject > 180) {
                unLockMission3Num = 0;
                countObject = 0;
            }
        }
        if (unLockPlantNum == 1) {
            countObject++;
            if (countObject > 180) {
                unLockPlantNum = 0;
                countObject = 0;
            }
        }
        if (unLockFireNum == 1) {
            countObject++;
            if (countObject > 180) {
                unLockFireNum = 0;
                countObject = 0;
            }
        }
        if (gotSeedNum == 1) {
            countObject++;
            if (countObject > 250) {
                gotSeedNum = 0;
                countObject = 0;
            }
        }
        if (missionCompletNum == 1) {
            countObject++;
            if (countObject > 250) {
                missionCompletNum = 0;
                countObject = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "mission":
                    gp.obj[i] = null;
                    checkGotMission = 1;
                    missionNum = 1;
                    gp.ui.setDialogeMission();
                    break;
                case "magic_plant":
                    gp.obj[i] = null;
                    checkGotMagicPlant = 1;
                    magicPlantNum = 1;
                    gp.ui.setDialogeGotMagicPlant();
                    break;
                case "magic_fire":
                    gp.obj[i] = null;
                    checkGotMagicFire = 1;
                    magicFireNum = 1;
                    gp.ui.setDialogeGotMagicFire();
                    break;
                case "hint":
                    gp.obj[i] = null;
                    checkGotHint = 1;
                    hintNum = 1;
                    gp.ui.setDialogeHint();
                    break;
                case "mission1":
                    gp.obj[i] = null;
                    checkMission1 = 1;
                    miss1Num = 1;
                    countMission++;
                    gp.ui.setDialogeMission1();
                    break;
                case "mission2":
                    gp.obj[i] = null;
                    checkMission2 = 1;
                    miss2Num = 1;
                    countMission++;
                    gp.ui.setDialogeMission2();
                    break;
                case "mission3":
                    gp.obj[i] = null;
                    checkMission3 = 1;
                    miss3Num = 1;
                    countMission++;
                    gp.ui.setDialogeMission3();
                    break;
                case "doorhome":
                    if (checkGotMission == 1) {
                        gp.obj[i] = null;
                        checkUnLockHome = 1;
                        unLockHomeNum = 0;
                    } else {
                        gp.ui.setDialogeUnLockHome();
                        unLockHomeNum = 1;
                    }
                    break;
                case "door":
                    if (countMission == 3) {
                        gp.obj[i] = null;
                        checkUnLockDoor = 1;
                        unLockDoorNum = 0;
                    } else {
                        gp.ui.setDialogeUnLockDoor();
                        unLockDoorNum = 1;
                    }
                    break;
                case "doormission1":
                    if (checkGotHint == 1) {
                        gp.obj[i] = null;
                        checkUnLockMission1 = 1;
                        unLockMission1Num = 0;
                    } else {
                        gp.ui.setDialogeUnLockMission1();
                        unLockMission1Num = 1;
                    }
                    break;
                case "doormission2":
                    if (checkGotMagicPlant == 1) {
                        gp.obj[i] = null;
                        checkUnLockMission2 = 1;
                        unLockMission2Num = 0;
                    } else {
                        gp.ui.setDialogeUnLockMission2();
                        unLockMission2Num = 1;
                    }
                    break;
                case "doormission3":
                    if (checkGotMagicFire == 1) {
                        gp.obj[i] = null;
                        checkUnLockMission3 = 1;
                        unLockMission3Num = 0;
                    } else {
                        gp.ui.setDialogeUnLockMission3();
                        unLockMission3Num = 1;
                    }
                    break;
                case "doormagicplant":
                    if (fruitNum >= 1) {
                        gp.obj[i] = null;
                        checkUnLockPlant = 1;
                        unLockPlantNum = 0;
                    } else {
                        gp.ui.setDialogeUnLockPlant();
                        unLockPlantNum = 1;
                    }
                    break;
                case "doormagicfire":
                    if (checkGotMagicPlant == 1) {
                        gp.obj[i] = null;
                        checkUnLockFire = 1;
                        unLockFireNum = 0;
                    } else {
                        gp.ui.setDialogeUnLockFire();
                        unLockFireNum = 1;
                    }
                    break;
                case "altar":
                    altarNum = 1;
                    break;
                case "seed":
                    gp.obj[i] = null;
                    gotSeed = 1;
                    gotSeedNum = 1;
                    gp.ui.setDialogeGotSeed();
                    fruitNum += 4;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "dig":
                switch (preDirection) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up_dig1;
                        }
                        if (spriteNum == 2) {
                            image = up_dig2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down_dig1;
                        }
                        if (spriteNum == 2) {
                            image = down_dig2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left_dig1;
                        }
                        if (spriteNum == 2) {
                            image = left_dig2;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right_dig1;
                        }
                        if (spriteNum == 2) {
                            image = right_dig2;
                        }
                        break;
                }
                break;
            case "create":
                switch (preDirection) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up_create1;
                        }
                        if (spriteNum == 2) {
                            image = up_create2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down_create1;
                        }
                        if (spriteNum == 2) {
                            image = down_create2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left_create1;
                        }
                        if (spriteNum == 2) {
                            image = left_create2;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right_create1;
                        }
                        if (spriteNum == 2) {
                            image = right_create2;
                        }
                        break;
                }
                break;
            case "keep":
                switch (preDirection) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up_keep1;
                        }
                        if (spriteNum == 2) {
                            image = up_keep2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down_keep1;
                        }
                        if (spriteNum == 2) {
                            image = down_keep2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left_keep1;
                        }
                        if (spriteNum == 2) {
                            image = left_keep2;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right_keep1;
                        }
                        if (spriteNum == 2) {
                            image = right_keep2;
                        }
                        break;
                    default:
                        if (spriteNum == 1) {
                            image = up_keep1;
                        }
                        if (spriteNum == 2) {
                            image = up_keep2;
                        }
                        break;
                }
                break;
            case "xxx":
                switch (preDirection) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        break;
                }

        }
        g2.drawImage(image, screenX, screenY, null);
    }
}