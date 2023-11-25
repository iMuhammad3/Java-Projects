public class Main {
    public static void main(String[] args) {
        try {
            App app = new App();
            app.run();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}