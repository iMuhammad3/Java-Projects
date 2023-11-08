import java.util.ArrayList;

public class Fighter {

    private int health = 100;
    private String name;
    private double gold = 10;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private Weapon currentWeapon;
    Fighter(String name) {
        this.name = name;
        this.weapons.add(new Weapon("Fist"));
        this.weapons.add(new Weapon("Kick"));
        currentWeapon = weapons.get(0);
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String health) {
        this.name = name;
    }

    public double getGold() {
        return this.gold;
    }
    public void setGold(double gold) {
        this.gold = gold;
    }

}
