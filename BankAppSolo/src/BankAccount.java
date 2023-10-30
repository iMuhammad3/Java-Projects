public class BankAccount {
    double balance;
    String username;
    String userId;

    public BankAccount(String username){
        this.username = username;
        this.balance = 0;
        this.userId = generateId();
    }

    @Override
    public String toString() {
        String username = "Username: " + this.username;
        String userId = "User Id: " + this.userId;
        String balance = "Balance: " + this.balance;
        return username + "\n" + userId + "\n" + balance + "\n";
    }

    String generateId(){
        StringBuilder randomId = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        for(int i = 0; i < 12; i++){
            int randomIndex = (int) Math.floor(Math.random() * chars.length());
            randomId.append(chars.charAt(randomIndex));
        }

        return randomId.toString();
    }

    void withdraw(double amount){
        this.balance -= amount;
        System.out.println(amount + " has been debited from your account");
        System.out.println("New balance: " + this.balance);
    }

    void deposit(double amount){
        this.balance += amount;
        System.out.println(amount + " has been credited to your account");
        System.out.println("New balance: " + this.balance);
    }
}
