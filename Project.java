import java.util.Random;
import java.util.Scanner;

public class Project {
    public static void   main(String[] args){
        BarberShop shop = new BarberShop();
        Random random = new Random();
        Scanner scanner= new Scanner(System.in);

        System.out.println("Press 'space' to simulate an event or any other key to exit.");
        System.out.println();
        System.out.println("    X               Events                       State of the Shop");
        System.out.println("+---------+--------------------+------------------------------------------------------------+");

        while (true){
            String input = scanner.nextLine();

            if(input.equals(" ")){
                int x = random.nextInt(4);
                String event = switch (x) {
                    case 0 -> shop.clientLeaves();
                    case 1 -> shop.clientArrives("VIP");
                    case 2, 3 -> shop.clientArrives("ORD");
                    default -> "";
                };

                System.out.print("  " + x + " ---> \t\t (" + event + ") \t\t [ " + shop.displayShopState() + " ]");
            }else {
                System.out.println("Exiting program.");
                break;
            }
        }
        scanner.close();
    }
}