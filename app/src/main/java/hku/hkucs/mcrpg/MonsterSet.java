package hku.hkucs.mcrpg;

public class MonsterSet {

    private Monster monsterSet[];
    private int currentMonsterIndex;
    private int currentStage;

    public MonsterSet() {
        initialize();
    }

    public void initialize() {
        monsterSet = new Monster[]{new Monster(), new Monster(), new Monster()};

        currentMonsterIndex = 0;
        currentStage = 1;

        monsterSet[0].setName("Slime");
        monsterSet[0].setAbilityID(new int[]{0, 1, 2, -1});
        monsterSet[0].setAbilityCD_MAX(new int[]{3, 5, 7, 0});
        monsterSet[0].setAbilityCD_ADJ(currentStage);
        monsterSet[0].setImagePath("monster_slime");
        monsterSet[0].setDamage(currentStage);

        monsterSet[1].setName("Cruelcumber");
        monsterSet[1].setAbilityID(new int[]{0, 3, 4, -1});
        monsterSet[1].setAbilityCD_MAX(new int[]{3, 5, 7, 0});
        monsterSet[1].setAbilityCD_ADJ(currentStage);
        monsterSet[1].setImagePath("monster_cruelcumber");
        monsterSet[1].setDamage(currentStage);

        monsterSet[2].setName("Teeny Sanguini");
        monsterSet[2].setAbilityID(new int[]{0, 5, 6, -1});
        monsterSet[2].setAbilityCD_MAX(new int[]{3, 5, 7, 0});
        monsterSet[2].setAbilityCD_ADJ(currentStage);
        monsterSet[2].setImagePath("monster_teeny_sanguini");
        monsterSet[2].setDamage(currentStage);
    }

    public Monster getCurrentMonster() {
        return monsterSet[currentMonsterIndex];
    }

    public int getCurrentStage() {
        return currentStage;
    }

    //  Called when a monster is defected
    public void defected() {
        if (++currentMonsterIndex >= 3) {
            currentMonsterIndex = 0;
            currentStage++;
        }
        monsterSet[currentMonsterIndex].setHealth(100);
        monsterSet[currentMonsterIndex].setDamage(currentStage);
        monsterSet[currentMonsterIndex].setAbilityCD_ADJ(currentStage);
        return;
    }

}
