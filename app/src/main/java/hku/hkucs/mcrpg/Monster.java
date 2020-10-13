package hku.hkucs.mcrpg;

public class Monster {

    private String name;
    private String imagePath;
    private String abilityName[];
    private int abilityID[]; // id -1 = no ability, 0 = normal attack
    private int abilityCD[];
    private int abilityCD_ADJ[];
    private int abilityCD_MAX[];
    private int health;
    final private int baseDamage = 5;
    private int currentDamage;
    private boolean abilityCast[]; // for checking if any ability needed to be casted
    private boolean effect_onFire;


    public Monster() {
        initialize();
    }

    public void initialize() {
        name = "monsterName";
        imagePath = "";
        abilityName = new String[]{"Normal Attack", "Ability 2", "Ability 3", "Ability 4"};
        abilityID = new int[]{0, -1, -1, -1};
        abilityCD_MAX = new int[]{0, 0, 0, 0};
        abilityCD = abilityCD_MAX.clone();
        health = 100;
        abilityCast = new boolean[]{false, false, false, false};
        effect_onFire = false;
        return;
    }

    public void setName(String monsterName) {
        name = monsterName;
        return;
    }

    public String getName() {
        return name;
    }

    public void setImagePath(String path) {
        imagePath = path;
        return;
    }

    public String getImagePath() {
        return imagePath;
    }

    // Input: int array of 4
    // This will also set the ability names.
    public void setAbilityID(int ID[]) {
        abilityID = ID.clone();
        for (int i = 0; i < 4; i++) {
            switch (abilityID[i]) {
                case 0:
                    abilityName[i] = "Normal Attack";
                    break;
                case 1:
                    abilityName[i] = "Decrease Answering Time";
                    break;
                case 2:
                    abilityName[i] = "Increase Question Difficulty";
                    break;
                case 3:
                    abilityName[i] = "Increase Player Skill CD";
                    break;
                case 4:
                    abilityName[i] = "Scramble Answer";
                    break;
                case 5:
                    abilityName[i] = "Lock Player Skills";
                    break;
                case 6:
                    abilityName[i] = "Double-edge Sword";
                    break;
                default:
                    abilityName[i] = "Null Ability " + String.valueOf(i + 1);
                    break;
            }
        }
        return;
    }

    public int getAbilityID(int index) {
        return abilityID[index];
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

    public void setHealth(int healthInput) {
        health = healthInput;
        return;
    }

    //  return value from 0 to 100
    public int getHealth() {
        if (health <= 0) return 0;
        else return health;
    }

    public void setAbilityCD_ADJ(int stage){
        abilityCD_ADJ = abilityCD_MAX.clone();
        for (int i = 0; i < 4; i++) {
            abilityCD_ADJ[i] = abilityCD_MAX[i] - stage + 1;
            if (abilityCD_ADJ[i] <= 0) abilityCD_ADJ[i] = 1;
        }
        abilityCD = abilityCD_ADJ.clone();
    }

    // Input: int array of 4
    public void setAbilityCD_MAX(int coolDown[]) {
        abilityCD_MAX = coolDown.clone();
        setAbilityCD_ADJ(1);
        return;
    }

    public int getAbilityCD(int index) {
        return abilityCD[index];
    }

    public String getAbilityName(int index) {
        return abilityName[index];
    }

    public void setAbilityCast(int index, boolean status) {
        abilityCast[index] = status;
        return;
    }

    public boolean getAbilityCast(int index) {
        return abilityCast[index];
    }

    public void setDamage(int multiplier) {
        currentDamage = baseDamage * multiplier;
        return;
    }

    public int getDamage() {
        return currentDamage;
    }

    //  Called when every correct answer is made.
    //
    //  return -1 if the monster is dead
    //         1  if the monster is not dead
    public int underAttack(int damageValue) {
        health -= damageValue;
        if (health <= 0) return -1;
        else return 1;
    }

    //  Called when turn ended
    //  Lower all ability cd by 1
    public void endTurn() {
        for (int i = 0; i < 4; i++) {
            if (--abilityCD[i] <= 0) {
                abilityCast[i] = true;
                System.out.println(abilityCD == null);
                System.out.println(abilityCD_ADJ == null);
                abilityCD[i] = abilityCD_ADJ[i];
            }
        }
        return;
    }

    public boolean isDead() {
        if (health <= 0) return true;
        else return false;
    }
}
