public class User {
    private String username;
    private String fullName;
    private int id;
    private double balance;
    private String[] transactionHistory;
    private String lastTransaction;

    public User(int id, String username, String fullName){
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.balance = 0.0;
    }
    public User(int id, String username, String fullName, double balance){
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
