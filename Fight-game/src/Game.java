import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    static Fighter fighter;
    static void startGame() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to the Fighter game!");
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        fighter = new Fighter(name);
        System.out.println("You can buy some weapons and fight the monster");

        while(true){
            System.out.println("--------------");
            System.out.println("1. Buy a weapon");
            System.out.println("2. Show all weapons");
            System.out.println("3. Change current weapon");
            System.out.println("4. Show current weapon");
            System.out.println("5. Fight the monster");
            System.out.println("--------------");

            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    buyWeapon();
                    break;
                case 2:
                    showWeapons();
                    break;
                case 3:
                    changeWeapon();
                    break;
                case 4:
                    System.out.println("Current weapon is " + fighter.getCurrentWeapon().getName());
                    System.out.println();
                    break;
                case 5:
                    try{
                        fight();
                    } catch(Exception e){e.printStackTrace();}
                    break;
            }
        }
    }

    static void fight() throws Exception{
        System.out.println("Loading fight...");
        Thread.sleep(1000);
        System.out.println(
                fighter.getName() + " is walking by the castle and finds a huge monster trying to smash and destroy the castle."
        );
        Thread.sleep(400);
        System.out.println("The monster is approaching, what are you going to do now?");
        System.out.println("1. Kick");
    }

    static void buyWeapon(){
        System.out.println("--------------");
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("What weapon would you like to buy?");
        System.out.println("1. Dagger (10 gold)");
        System.out.println("2. Sword (8 gold)");
        System.out.println("3. Hammer (6 gold)");
        System.out.println("4. Bow and arrow (4 gold)");
        System.out.println("--------------");

        choice = scanner.nextInt();
        switch (choice){
            case 1:
                purchaseWeapon("Dagger");
            break;
            case 2:
                purchaseWeapon("Sword");
                break;
            case 3:
                purchaseWeapon("Hammer");
                break;
            case 4:
                purchaseWeapon("Bow and arrow");
                break;
            default:
                System.out.println("Please select a valid option");
                break;
        }
        System.out.println();
    }

    static void purchaseWeapon(String name){
        System.out.println("--------------");
        Weapon weapon = new Weapon(name);
        int damage = weapon.getDamage();
        int gold = (int) fighter.getGold();
        if(damage > gold){
            System.out.println("You don't have enough gold to purchase this weapon!");
            System.out.println("Gold required: " + damage);
            System.out.println("Gold left: " + gold);
            return;
        }
        fighter.addWeapon(weapon);
        fighter.setGold(gold - damage);
        System.out.println("You have bought the " + weapon.getName() + " which deals " + weapon.getDamage() + " damage!");
        System.out.println("You have " + gold + " gold left.");
        System.out.println("--------------");
        System.out.println();
    }

    static void showWeapons(){
        System.out.println("--------------");
        System.out.println("These are all your weapons: ");
        ArrayList<Weapon> weapons = fighter.getWeapons();
        for(int i = 0;i < weapons.size();i++){
            System.out.println((i+1) + ". " + weapons.get(i).getName());
        }
        System.out.println("--------------");
        System.out.println();
    }

    static void changeWeapon(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What weapon would you like to switch to?");
        showWeapons();
        int choice = scanner.nextInt();
        Weapon changedWeapon = null;
        for(int i = 1;i < fighter.getWeapons().size();i++){
            if(choice == i){
                changedWeapon = fighter.getWeapons().get(i);
            }else {
                System.out.println("Nope.");
                return;
            }
        }
        fighter.setCurrentWeapon(changedWeapon);
    }

}
