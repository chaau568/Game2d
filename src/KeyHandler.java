import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, keepPressed, magicPressed;
    boolean checkPosition;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Title State
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.player.checkStartGame = 1;
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    gp.player.checkStartGame = 2;
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        // Play State
        else if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_K) {
                magicPressed = true;
            }
            if (code == KeyEvent.VK_T) {
                if (checkPosition == false) {
                    checkPosition = true;
                } else {
                    checkPosition = false;
                }
            }
            if (code == KeyEvent.VK_G) {
                keepPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                }
            }
            if (code == KeyEvent.VK_I) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.inventoryState;
                }
            }
            if (code == KeyEvent.VK_Q && gp.player.altarNum == 1) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.altarState;
                }
            }
        }

        // Pause State
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    if (gp.gameState == gp.pauseState) {
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.commandNum == 1) {
                    gp.player.saveDataPlayer = 1;
                    if (gp.gameState == gp.pauseState) {
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        // Inventory State
        else if (gp.gameState == gp.inventoryState) {
            // gp.player.magicMode = 0;
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    if (gp.player.checkGotMagicFire == 1) {
                        gp.ui.commandNum = 4;
                    } else if (gp.player.checkGotMagicPlant == 1) {
                        gp.ui.commandNum = 3;
                    } else if (gp.player.checkGotMission == 1) {
                        gp.ui.commandNum = 2;
                    } else {
                        gp.ui.commandNum = 1;
                    }
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.player.checkGotMagicFire == 1) {
                    if (gp.ui.commandNum > 4) {
                        gp.ui.commandNum = 0;
                    }
                } else if (gp.player.checkGotMagicPlant == 1) {
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                } else if (gp.player.checkGotMission == 1) {
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                } else {
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }

            }
            if (code == KeyEvent.VK_I) {
                if (gp.gameState == gp.inventoryState) {
                    gp.gameState = gp.playState;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 2) {
                    if (gp.ui.checkMission == 0) {
                        gp.ui.checkMission = 1;
                    } else if (gp.ui.checkMission == 1) {
                        gp.ui.checkMission = 0;
                    }
                } else if (gp.ui.commandNum == 3) {
                    gp.player.magicMode = 1;
                    if (gp.gameState == gp.inventoryState) {
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.commandNum == 4) {
                    gp.player.magicMode = 2;
                    if (gp.gameState == gp.inventoryState) {
                        gp.gameState = gp.playState;
                    }
                }
            }
        }

        // Altar State
        else if (gp.gameState == gp.altarState) {
            if (code == KeyEvent.VK_Q) {
                if (gp.gameState == gp.altarState) {
                    gp.gameState = gp.playState;
                    gp.player.altarNum = 0;
                }
            }
            if (gp.player.altarNum == 1) {
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.player.fruitNum >= 11) {
                        gp.player.fruitNum -= 10;
                        gp.player.fruitMissionNum += 10;
                    }
                    if (gp.player.fruitMissionNum >= 2000) {
                        gp.player.missionCompletNum = 1;
                        gp.player.missionCompleted = 1;
                        if (gp.gameState == gp.altarState) {
                            gp.gameState = gp.playState;
                            gp.player.altarNum = 0;
                        }
                    }
                }
            } else {
                gp.player.altarNum = 0;
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_K) {
            magicPressed = false;
        }
        if (code == KeyEvent.VK_G) {
            keepPressed = false;
        }
    }
}
