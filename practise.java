import java.util.Scanner;

public class practise {

    public static String uppercase(String items) {
        String[] words = items.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                String first = words[i].substring(0, 1).toUpperCase();
                String rest = words[i].substring(1);
                result.append(first).append(rest).append(" ");
            }
        }
        return result.toString().trim(); // remove extra space
    }
    public static int getItem(Scanner scanner){
        int item;
        do {
            System.out.print("How many item would you like to buy(max 5): ");
            item = scanner.nextInt();
            scanner.nextLine();

            if (item > 5 || item <= 0) {
                System.out.println("Invalid! Enter between 1 to 5.");
            }
        } while (item > 5 || item <= 0);
        return item;
    };
    public static void getproduct(int item,Scanner scanner,String[] items,int[] quantity,double[] price){
        for (int i = 0; i < item; i++) {

            System.out.print("\nEnter product " + (i + 1) + " Name: ");
            items[i] = scanner.nextLine();

            while (true) {
                System.out.print("Enter unit price: $");
                price[i] = scanner.nextDouble();

                if (price[i] > 0) break;
                else System.out.println("Price must be greater than 0. Try again.");
            }


            while (true) {
                System.out.print("Enter quantity: ");
                quantity[i] = scanner.nextInt();

                if (quantity[i] > 0) break;
                else System.out.println("Quantity must be greater than 0. Try again.");
            }

            scanner.nextLine();
        }
    };
    public static double subtotalprice(int item,double[] itemtotal,double[] price,int[] quantity){
double subtotal=0;
    for (int i = 0; i < item; i++) {
        itemtotal[i] = price[i] * quantity[i];
        subtotal += itemtotal[i];
    }
    return subtotal;
};
    public static double discountgiven(double subtotal){
    double discount_rate;
    if (subtotal > 100) discount_rate = 0.2;
    else if (subtotal > 50) discount_rate = 0.1;
    else discount_rate = 0.05;
    return discount_rate;
};
    public static void printReceipt(String[] items, int[] quantity, double[] price,
                                    double[] itemtotal, double subtotal,
                                    double discount, int deliveryFee, double final_price) {

        System.out.println("\n******** Final Receipt ********\n");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("Item %d: %s -> %d * %.2f = %.2f\n",
                    i + 1, uppercase(items[i]), quantity[i], price[i], itemtotal[i]);
        }

        System.out.println("\nSubtotal : $" + subtotal);
        System.out.printf("Discount Applied : $%.2f\n", discount);
        System.out.println("Delivery Fee : $" + deliveryFee);
        System.out.println("\nFinal Total : $" + final_price);

        System.out.println("\nThanks for Your Purchase. See You Again!");
        System.out.println("*****************************************");
    }

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n***Welcome To Lazy_$*$ Shop***");
        System.out.println("*****************************************");
        int item =getItem(scanner);

        String[] items = new String[item];
        double[] price = new double[item];
        int[] quantity = new int[item];
        double[] itemtotal = new double[item];

        getproduct(item,scanner,items,quantity,price);
        double subtotal = subtotalprice(item,itemtotal,price,quantity);
        double discount = subtotal * discountgiven(subtotal);
        int deliveryFee = (subtotal < 50) ? 10 : 0;
        double final_price = subtotal - discount + deliveryFee;
        printReceipt(items, quantity, price, itemtotal,
            subtotal, discount, deliveryFee, final_price);
        scanner.close();
    }
}