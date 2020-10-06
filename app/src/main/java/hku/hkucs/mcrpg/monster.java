package hku.hkucs.mcrpg;

public class monster {

    private int health;
    private int ability_1_cd;
    private int ability_2_cd;
    private int ability_3_cd;
    private int ability_4_cd;
    private boolean effect_onFire;
    private String name;
    final private int ABILITY_1_CD_MAX = 1;
    final private int ABILITY_2_CD_MAX = 2;
    final private int ABILITY_3_CD_MAX = 4;
    final private int ABILITY_4_CD_MAX = 6;

    public monster() {
        initialize();
    }

    public void initialize() {
        health = 100;
        ability_1_cd = ABILITY_1_CD_MAX;
        ability_2_cd = ABILITY_2_CD_MAX;
        ability_3_cd = ABILITY_3_CD_MAX;
        ability_4_cd = ABILITY_4_CD_MAX;
        effect_onFire = false;
        name = "monsterName";
        return;
    }

    public void setName(String monsterName) {
        name = monsterName;
        return;
    }

    public String getName() {
        return name;
    }

    public void setOnFire() {
        effect_onFire = true;
        return;
    }

    // TODO: manually off fire or fire only last for certain rounds?
    public void setOffFire() {
        effect_onFire = false;
        return;
    }

    public boolean getFireStatus() {
        return  effect_onFire;
    }

    //  return value from 0 to 100
    public int getHealth() {
        if (health <= 0) return 0;
        else return health;
    }

    public int getAbility_1_cd() {
        return ability_1_cd;
    }

    public int getAbility_2_cd() {
        return ability_2_cd;
    }

    public int getAbility_3_cd() {
        return ability_3_cd;
    }

    public int getAbility_4_cd() {
        return ability_4_cd;
    }

    //  return -1 if the monster is dead
    //         1  if the monster is not dead
    public int attack(int damageValue) {
        health -= damageValue;
        if (health <= 0) return -1;
        else return 1;
    }

    //  executed when turn ended
    //  will call other functions to implement ability actions
    public void endTurn() {
        ability_1_cd--;
        ability_2_cd--;
        ability_3_cd--;
        ability_4_cd--;

        if (ability_1_cd <= 0) {
            // TODO implement action
            ability_1_cd = ABILITY_1_CD_MAX;
        }
        if (ability_2_cd <= 0) {
            // TODO implement action
            ability_2_cd = ABILITY_2_CD_MAX;
        }
        if (ability_3_cd <= 0) {
            // TODO implement action
            ability_3_cd = ABILITY_3_CD_MAX;
        }
        if (ability_4_cd <= 0) {
            // TODO implement action
            ability_4_cd = ABILITY_4_CD_MAX;
        }
    }
}
