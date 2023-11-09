public class Main {
    public static void main(String[] args){
        Shop shop = new Shop();
        try{
            shop.startShopping();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}