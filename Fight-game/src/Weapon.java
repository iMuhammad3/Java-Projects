import java.util.Objects;

public class Weapon {

    private final String DAGGER = "Dagger";
    private final String SWORD = "Sword";
    private final String BOW_N_ARROW = "Bow and arrow";
    private final String HAMMER = "Hammer";
    private final String KICK = "Kick";
    private final String FIST = "Fist";

    private String name;
    private int damage;
    Weapon(String name){
        this.name = name;
        switch (name) {
            case DAGGER -> damage = 10;
            case SWORD -> damage = 8;
            case HAMMER -> damage = 6;
            case BOW_N_ARROW -> damage = 4;
            case KICK -> damage = 2;
            case FIST -> damage = 1;
        }
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
