import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Game Panel Setting
    final int originalTileSize = 16; // 16x16
    final int scale = 3;
    public int tileSize = originalTileSize * scale; // 48x48
    public int maxScreenCol = 16; // 16
    public int maxScreenRow = 12; // 12
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 90;
    public final int maxWorldRow = 60;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // Setting
    public int countAction = 0;
    int loadPlayerData = 0;
    int loadTile = 0;
    int loadObject = 0;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];
    public UI ui = new UI(this);
    public ObjInventory objInventory = new ObjInventory(this);

    // Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleState = 3;
    public final int inventoryState = 4;
    public final int altarState = 5;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                countAction++;
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState || gameState == inventoryState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if (gameState == titleState) {
            loadTile = 0;
            loadPlayerData = 0;
            ui.draw(g2);
        } else {
            // Tile
            if (loadTile == 0) {
                tileM.setTileStart();
            }
            loadTile = 1;
            tileM.draw(g2);

            // Player, Object 
            if (loadPlayerData == 0) {
                player.setDefaultValues();
                if (player.checkGotMission == 1) {
                    obj[0] = null;
                }
                if (player.checkGotMagicPlant == 1) {
                    obj[1] = null;
                }
                if (player.checkGotMagicFire == 1) {
                    obj[2] = null;
                }
                if (player.checkGotHint == 1) {
                    obj[3] = null;
                }
                if (player.checkMission1 == 1) {
                    obj[4] = null;
                }
                if (player.checkMission2 == 1) {
                    obj[5] = null;
                }
                if (player.checkMission3 == 1) {
                    obj[6] = null;
                }
                if (player.checkUnLockDoor == 1) {
                    obj[7] = null;
                }
                if (player.checkUnLockHome == 1) {
                    obj[8] = null;
                }
                if (player.checkUnLockMission1 == 1) {
                    obj[9] = null;
                }
                if (player.checkUnLockMission2 == 1) {
                    obj[10] = null;
                }
                if (player.checkUnLockMission3 == 1) {
                    obj[11] = null;
                }
                if (player.checkUnLockPlant == 1) {
                    obj[12] = null;
                }
                if (player.checkUnLockFire == 1) {
                    obj[13] = null;
                }
                if (player.gotSeed == 1) {
                    obj[15] = null;
                }
            }
            loadPlayerData = 1;
            player.draw(g2);

            // Object
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // Show Position
            if (keyH.checkPosition == true) {
                ui.showPosition(g2, (player.worldX / tileSize), (player.worldY / tileSize));
            }
            ui.draw(g2);
        }
        g2.dispose();
    }
}
