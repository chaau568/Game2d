import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;

public class UI {

    Graphics2D g2;
    GamePanel gp;
    public String[] messageDialogue = new String[20];
    public int commandNum = 0;
    public int checkMission = 0;
    public int checkAltar = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // Title State
        if (gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        }

        // Game State
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen(g2);
        }
        if (gp.gameState == gp.inventoryState) {
            drawInventory(g2);
        }
        if (gp.gameState == gp.altarState) {
            drawAltar(g2);
        }

        // Event State
        if (gp.player.checkKeep == true) {
            drawKeep(g2);
        }
        if (gp.player.missionNum == 1) {
            drawMission(g2);
        }
        if (gp.player.magicPlantNum == 1) {
            drawGotSomeObject(g2);
        }
        if (gp.player.magicFireNum == 1) {
            drawGotSomeObject(g2);
        }
        if (gp.player.hintNum == 1) {
            drawHint(g2);
        }
        if (gp.player.miss1Num == 1) {
            drawMission1(g2);
        }
        if (gp.player.miss2Num == 1) {
            drawMission2(g2);
        }
        if (gp.player.miss3Num == 1) {
            drawMission3(g2);
        }
        if (gp.player.unLockHomeNum == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockDoorNum == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockMission1Num == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockMission2Num == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockMission3Num == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockPlantNum == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.unLockFireNum == 1) {
            drawUnLockDoor(g2);
        }
        if (gp.player.gotSeedNum == 1) {
            drawGotSomeObject(g2);
        }
        if (gp.player.missionCompletNum == 1) {
            drawMissionCompleteted(g2);
        }
    }

    public void drawTitleScreen(Graphics2D g2) {
        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
        String text = "A Farmer";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;
        g2.setColor(Color.GRAY);
        g2.drawString(text, (x + 3), (y + 3));
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // A Farmer
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 2);
        if (gp.countAction < 25) {
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        } else if (gp.countAction > 50) {
            gp.countAction = 0;
        } else {
            g2.drawImage(gp.player.down2, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        }
        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.setColor(Color.GRAY);
        g2.drawString(text, (x + 3), (y + 1));
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("=>", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.GRAY);
        g2.drawString(text, (x + 3), (y + 1));
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("=>", x - gp.tileSize, y);
        }

        text = "EXIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.GRAY);
        g2.drawString(text, (x + 3), (y + 1));
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString("=>", x - gp.tileSize, y);
        }
    }

    public void drawInventory(Graphics2D g2) {
        int x = gp.screenWidth - (gp.tileSize * 5) - 20;
        int y = gp.tileSize;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 9;

        // Box Dialogue
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 35, 35);

        // Dialogue Seed
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
        x += gp.tileSize;
        y += (gp.tileSize / 2);
        g2.drawImage(gp.objInventory.seed, x, y, 50, 50, null);
        x += 60;
        y += 25;
        g2.drawString(" x " + gp.player.fruitNum, x, y);
        x -= 60;
        if (commandNum == 0) {
            g2.drawString("=>", x - 30, y);
        }

        // Dialogue Mana
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
        y += gp.tileSize;
        g2.drawImage(gp.objInventory.mana, x, y, 50, 50, null);
        g2.drawString(" x " + gp.player.manaNum, x + 60, (y + 25));
        if (commandNum == 1) {
            g2.drawString("=>", x - 30, (y + 25));
        }

        // Dialogue Mission
        if (gp.player.checkGotMission == 1) {
            y += gp.tileSize + 25;
            g2.drawImage(gp.objInventory.mission, x, y, 50, 50, null);
            if (commandNum == 2) {
                g2.drawString("=>", x - 30, (y + 25));
                if (checkMission == 1) {
                    String text = "";
                    x -= 40;
                    y += 60;
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
                    if (gp.player.countMission == 3) {
                        text = "Clear!!!";
                        g2.drawString("1) Mission " +  text, x, y);
                    } else {
                        text = "Not clear!!!";
                        g2.drawString("1) " + text + " you got ==> " + gp.player.countMission + "/3", x, y);
                    }
                    y += 30;
                    if (gp.player.missionCompleted != 1) {
                        g2.drawString("2) " + gp.player.fruitMissionNum + " / 2000", x, y);
                    }
                    else {
                        g2.drawString("2) Mission Clear!!!", x, y);
                    }
                }
            }
        }

        // Dialogue Magic of plant
        if (gp.player.checkGotMagicPlant == 1) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
            y += gp.tileSize * 2;
            g2.drawImage(gp.objInventory.magic_plant, x, y, 50, 50, null);
            if (commandNum == 3) {
                g2.drawString("=>", x - 30, (y + 25));
            }
        }

        // Dialogue Magic of fire
        if (gp.player.checkGotMagicFire == 1) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
            y += gp.tileSize * 2;
            g2.drawImage(gp.objInventory.magic_fire, x, y, 50, 50, null);
            if (commandNum == 4) {
                g2.drawString("=>", x - 30, (y + 25));
            }
        }

    }

    public void drawMission(Graphics2D g2) {
        int x = gp.screenWidth / 2 - (gp.tileSize * 5);
        int y = 20;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 6;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15));
        x += 20;
        y += 40;
        g2.drawString(messageDialogue[0], x, y);
        y += 20;
        g2.drawString(messageDialogue[1], x, y);
        y += 20;
        g2.drawString(messageDialogue[2], x, y);
        y += 20;
        g2.drawString(messageDialogue[3], x, y);
        y += 20;
        g2.drawString(messageDialogue[4], x, y);
        y += 20;
        g2.drawString(messageDialogue[5], x, y);
        y += 20;
        g2.drawString(messageDialogue[6], x, y);
        y += 20;
        g2.drawString(messageDialogue[7], x, y);
        y += 40;
        g2.drawString(messageDialogue[8], x, y);
        y += 20;
        g2.drawString(messageDialogue[9], x, y);
        y += 20;
        g2.drawString(messageDialogue[10], x, y);
    }

    public void drawAltar(Graphics2D g2) {
        int x = gp.tileSize;
        int y = 20;
        int width = gp.tileSize * 8;
        int height = gp.tileSize;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21));
        x += gp.tileSize;
        y += 30;
        g2.drawString("Already worshiped: " + gp.player.fruitMissionNum + " / 2000", x, y);

        if (gp.player.fruitNum >= 11) {
            x = gp.tileSize * 9;
            y = gp.tileSize * 4;
            width = gp.tileSize * 2;
            height = gp.tileSize;
            c = new Color(0, 0, 0, 175);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 35, 35);
            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, width, height, 35, 35);
            x += 13;
            y += 30;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21));
            g2.drawString("ENTER", x, y);

        } else {
            x = gp.tileSize * 7;
            y = gp.tileSize * 4;
            width = gp.tileSize * 7;
            height = gp.tileSize;
            c = new Color(0, 0, 0, 175);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 35, 35);
            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, width, height, 35, 35);
            x += 9;
            y += 30;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21));
            g2.drawString("Fruits of life does not enough!!!", x, y);
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        int x = gp.tileSize * 3;
        int y = gp.tileSize * 2;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 8;
        String text = "";
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        // Play
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56));
        text = "PLAY";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));
            g2.drawString("=>", (gp.tileSize * 3) + 25, (y - 10));
        }
        // Save
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56));
        text = "SAVE GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));
            g2.drawString("=>", (gp.tileSize * 3) + 25, (y - 10));
        }
        // Exit
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56));
        text = "EXIT";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));
            g2.drawString("=>", (gp.tileSize * 3) + 25, (y - 10));
        }
    }

    // Dialogue Hint
    public void drawHint(Graphics2D g2) {
        int x = gp.screenWidth / 2 - (gp.tileSize * 2);
        int y = 20;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 3;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += (gp.tileSize / 2);
        y += 30;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.drawString(messageDialogue[0], x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
        y += 30;
        g2.drawString(messageDialogue[1], x, y);
        y += 30;
        g2.drawString(messageDialogue[2], x, y);
        y += 30;
        g2.drawString(messageDialogue[3], x, y);
    }

    // Dialogue Mission 1
    public void drawMission1(Graphics2D g2) {
        int x = gp.screenWidth / 2 - (gp.tileSize * 2);
        int y = 20;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 4;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += (gp.tileSize / 2);
        y += 30;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21F));
        g2.drawString(messageDialogue[0], x, y);
        y += 30;
        g2.drawString(messageDialogue[1], x, y);
        y += 30;
        g2.drawString(messageDialogue[2], x, y);
        y += 30;
        g2.drawString(messageDialogue[3], x, y);
    }

    // Dialogue Mission 2
    public void drawMission2(Graphics2D g2) {
        int x = gp.screenWidth / 2 - (gp.tileSize * 2);
        int y = 20;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 4;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += (gp.tileSize / 2);
        y += 30;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21F));
        g2.drawString(messageDialogue[0], x, y);
        y += 30;
        g2.drawString(messageDialogue[1], x, y);
        y += 30;
        g2.drawString(messageDialogue[2], x, y);
        y += 30;
        g2.drawString(messageDialogue[3], x, y);
    }

    // Dialogue Mission 3
    public void drawMission3(Graphics2D g2) {
        int x = (gp.screenWidth / 2) - (gp.tileSize * 2);
        int y = 20;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 4;
        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += (gp.tileSize / 2);
        y += 30;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21F));
        g2.drawString(messageDialogue[0], x, y);
        y += 30;
        g2.drawString(messageDialogue[1], x, y);
        y += 30;
        g2.drawString(messageDialogue[2], x, y);
    }

    // Can Keep Fruit
    public void drawKeep(Graphics2D g2) {
        int x = gp.tileSize * 9;
        int y = gp.tileSize * 4;
        int width = gp.tileSize;
        int height = gp.tileSize;
        Color c = new Color(255, 255, 255, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += 4;
        y += 30;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21));
        g2.drawString("G!!!", x, y);
    }

    // Unlock Door
    public void drawUnLockDoor(Graphics2D g2) {
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 2;
        int width = gp.tileSize * 8;
        int height = gp.tileSize;
        Color c = new Color(255, 255, 255, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += 10;
        y += 32;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16));
        g2.drawString(messageDialogue[0], x, y);
    }

    // Got seed, magic of plant or magic of fire
    public void drawGotSomeObject(Graphics2D g2) {
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 2;
        int width = gp.tileSize * 7;
        int height = gp.tileSize;
        Color c = new Color(255, 255, 255, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        x += 10;
        y += 32;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16));
        g2.drawString(messageDialogue[0], x, y);
    }

    // Draw Dialogue when player completed mission
    public void drawMissionCompleteted(Graphics2D g2) {
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.tileSize * 12;
        int height = gp.tileSize * 3;
        Color c = new Color(255, 225, 225, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
        String text = "MISSION COMPLETED";
        x += 20;
        y += (gp.tileSize * 2) - 10;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        g2.drawString(text, x, y);
    }

    public void showPosition(Graphics2D g2, int x, int y) {
        int width = gp.tileSize * 5;
        int height = gp.tileSize / 2;
        Color c = new Color(255, 255, 255, 175);
        g2.setColor(c);
        g2.fillRoundRect((gp.tileSize / 2), (gp.tileSize / 2), width, height, 35, 35);
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect((gp.tileSize / 2), (gp.tileSize / 2), width, height, 35, 35);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15));
        g2.drawString("Position X: " + x + " Position Y: " + y, (gp.tileSize / 2) + 20, (gp.tileSize / 2) + 20);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth / 2) - (length / 2);
        return x;
    }

    public void setDialogeMission() {
        // Story
        messageDialogue[0] = "Story";
        messageDialogue[1] = "The kingdom they once called home lay in ruins.";
        messageDialogue[2] = "Decades had passed since its fall,";
        messageDialogue[3] = "and the land was now a barren wasteland.";
        messageDialogue[4] = "The people, gaunt and weary,";
        messageDialogue[5] = "struggled to survive in the harsh conditions.";
        messageDialogue[6] = "Hunger gnawed at their bellies,";
        messageDialogue[7] = "a constant reminder of the prosperity they had lost.";
        messageDialogue[8] = "Mission";
        messageDialogue[9] = "1) Solve a case.";
        messageDialogue[10] = "2) Farmland reclamation.";
    }

    public void setDialogeHint() {
        // Hint
        messageDialogue[0] = "Hint";
        messageDialogue[1] = "Long ago, people offered... to the God";
        messageDialogue[2] = "of Life in exchange for the protection";
        messageDialogue[3] = "of their land from evil forces.";
    }

    public void setDialogeMission1() {
        // Mission 1
        messageDialogue[0] = "Mission 1";
        messageDialogue[2] = "The origin of the evil comes from a";
        messageDialogue[2] = "contaminated water source. To get rid of it,";
        messageDialogue[3] = "you must cleanse it with.....";
    }

    public void setDialogeMission2() {
        // Mission 2
        messageDialogue[0] = "Mission 2";
        messageDialogue[1] = "Growing the Tree of Life requires an";
        messageDialogue[2] = "immense amount of mana, and you must";
        messageDialogue[3] = "also have a seed from the Tree of Life.";
    }

    public void setDialogeMission3() {
        // Mission 3
        messageDialogue[0] = "Mission 3";
        messageDialogue[1] = "The magic of fire can destroy the poisonous tree,";
        messageDialogue[2] = "and this will help to appease the god of life.";
    }

    public void setDialogeUnLockHome() {
        // How to unlock door ==> unLockHomeNum
        messageDialogue[0] = "You must receive the mission first.";
    }

    public void setDialogeUnLockDoor() {
        // How to unlock door ==> unLockDoorNum
        messageDialogue[0] = "You must complete the first mission first.";
    }

    public void setDialogeUnLockMission1() {
        // How to unlock door ==> unLockMission1Num
        messageDialogue[0] = "You must receive the hint first.";
    }

    public void setDialogeUnLockMission2() {
        // How to unlock door ==> unLockMission2Num
        messageDialogue[0] = "You must receive the magic of plants first.";
    }

    public void setDialogeUnLockMission3() {
        // How to unlock door ==> unLockMission3Num
        messageDialogue[0] = "You must receive the magic of fire first.";
    }

    public void setDialogeUnLockPlant() {
        // How to unlock door ==> unLockPlantNum
        messageDialogue[0] = "You must receive the seed of the Tree of Life first.";
    }

    public void setDialogeUnLockFire() {
        // How to unlock door ==> unLockFireNum
        messageDialogue[0] = "You must receive the Magic of Life.";
    }

    public void setDialogeGotMagicPlant() {
        // Got magic of plant
        messageDialogue[0] = "You have received the Magic of Life.";
    }

    public void setDialogeGotMagicFire() {
        // Got magic of fire
        messageDialogue[0] = "You have received the Magic of Fire.";
    }

    public void setDialogeGotSeed() {
        // Got seed of tree of life
        messageDialogue[0] = "You have received the Seed of the Tree of Life.";
    }
}
