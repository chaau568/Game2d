import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveData {
    DataStorage ds = new DataStorage();

    public void save(int worldX, int worldY, int fruitNum, int manaNum, int mission, int magic_plant, int magic_fire,
            int hintNum, int miss1, int miss2, int miss3, int countMission, int countUnLockDoor, int countUnLockHome,
            int countUnLockMission1, int countUnLockMission2, int countUnLockMission3, int countUnLockPlant,
            int countUnLockFire, int countGrowthPlant, int gotSeed, int missionComplete, int fruitMissionNum) {
        ds.playerPositionX = worldX;
        ds.playerPositionY = worldY;
        ds.playerFruitNum = fruitNum;
        ds.playerManaNum = manaNum;
        ds.mission = mission;
        ds.magic_plant = magic_plant;
        ds.magic_fire = magic_fire;
        ds.hintNum = hintNum;
        ds.mission1 = miss1;
        ds.mission2 = miss2;
        ds.mission3 = miss3;
        ds.countMission = countMission;
        ds.countUnLockDoor = countUnLockDoor;
        ds.countUnLockHome = countUnLockHome;
        ds.countUnLockMission1 = countUnLockMission1;
        ds.countUnLockMission2 = countUnLockMission2;
        ds.countUnLockMission3 = countUnLockMission3;
        ds.countUnLockPlant = countUnLockPlant;
        ds.countUnLockFire = countUnLockFire;
        ds.countGrowthPlant = countGrowthPlant;
        ds.gotSeed = gotSeed;
        ds.missionComplete = missionComplete;
        ds.fruitMissionNum = fruitMissionNum;
        try {
            FileWriter writer = new FileWriter("save.txt");
            writer.write(ds.playerPositionX + "\n" + ds.playerPositionY + "\n" + ds.playerFruitNum + "\n"
                    + ds.playerManaNum + "\n" + ds.mission + "\n" + ds.magic_plant + "\n" + ds.magic_fire + "\n"
                    + ds.hintNum + "\n" + ds.mission1 + "\n" + ds.mission2 + "\n" + ds.mission3 + "\n" + ds.countMission
                    + "\n" + ds.countUnLockDoor + "\n" + ds.countUnLockHome + "\n" + ds.countUnLockMission1 + "\n"
                    + ds.countUnLockMission2 + "\n" + ds.countUnLockMission3 + "\n" + ds.countUnLockPlant + "\n"
                    + ds.countUnLockFire + "\n" + ds.countGrowthPlant + "\n" + ds.gotSeed + "\n" + ds.missionComplete + "\n" + ds.fruitMissionNum);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] load() {
        int x = 0, y = 0, fruit = 0, mana = 0, mission = 0, magic_plant = 0, magic_water = 0, hint = 0, miss1 = 0,
                miss2 = 0, miss3 = 0, countMission = 0, unLockDoor = 0, unLockHome = 0, unLockMiss1 = 0, unLockMiss2 = 0, 
                unLockMiss3 = 0, unLockPlant = 0, unLockFire = 0, growthPlant = 0, gotSeed = 0, missionComplete = 0, fruitMissionNum = 0, count = 0;
        int[] data = new int[25];

        try {
            FileReader reader = new FileReader("save.txt");
            BufferedReader bw = new BufferedReader(reader);
            String line;
            while ((line = bw.readLine()) != null) {
                String numbers[] = line.split("\n");
                if (count == 0) {
                    x = Integer.parseInt(numbers[0]);
                } else if (count == 1) {
                    y = Integer.parseInt(numbers[0]);
                } else if (count == 2) {
                    fruit = Integer.parseInt(numbers[0]);
                } else if (count == 3) {
                    mana = Integer.parseInt(numbers[0]);
                } else if (count == 4) {
                    mission = Integer.parseInt(numbers[0]);
                } else if (count == 5) {
                    magic_plant = Integer.parseInt(numbers[0]);
                } else if (count == 6) {
                    magic_water = Integer.parseInt(numbers[0]);
                } else if (count == 7) {
                    hint = Integer.parseInt(numbers[0]);
                } else if (count == 8) {
                    miss1 = Integer.parseInt(numbers[0]);
                } else if (count == 9) {
                    miss2 = Integer.parseInt(numbers[0]);
                } else if (count == 10) {
                    miss3 = Integer.parseInt(numbers[0]);
                } else if (count == 11) {
                    countMission = Integer.parseInt(numbers[0]);
                } else if (count == 12) {
                    unLockDoor = Integer.parseInt(numbers[0]);
                } else if (count == 13) {
                    unLockHome = Integer.parseInt(numbers[0]);
                } else if (count == 14) {
                    unLockMiss1 = Integer.parseInt(numbers[0]);
                } else if (count == 15) {
                    unLockMiss2 = Integer.parseInt(numbers[0]);
                } else if (count == 16) {
                    unLockMiss2 = Integer.parseInt(numbers[0]);
                } else if (count == 17) {
                    unLockPlant = Integer.parseInt(numbers[0]);
                } else if (count == 18) {
                    unLockFire = Integer.parseInt(numbers[0]);
                } else if (count == 19) {
                    growthPlant = Integer.parseInt(numbers[0]);
                } else if (count == 20) {
                    gotSeed = Integer.parseInt(numbers[0]);
                } else if (count == 21) {
                    missionComplete = Integer.parseInt(numbers[0]);
                } else {
                    fruitMissionNum = Integer.parseInt(numbers[0]);
                }
                count++;
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        data[0] = x;
        data[1] = y;
        data[2] = fruit;
        data[3] = mana;
        data[4] = mission;
        data[5] = magic_plant;
        data[6] = magic_water;
        data[7] = hint;
        data[8] = miss1;
        data[9] = miss2;
        data[10] = miss3;
        data[11] = countMission;
        data[12] = unLockDoor;
        data[13] = unLockHome;
        data[14] = unLockMiss1;
        data[15] = unLockMiss2;
        data[16] = unLockMiss3;
        data[17] = unLockPlant;
        data[18] = unLockFire;
        data[19] = growthPlant;
        data[20] = gotSeed;
        data[21] = missionComplete;
        data[22] = fruitMissionNum;
        return data;
    }

}
