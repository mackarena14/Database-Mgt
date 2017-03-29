/** 
 * The class InventoryDriver is responsible for implementing the inventory
 * for a specific store.
 * 
 * @author  Mei-Ling Blackstone 
 * @version A.P. Computer Science - Unit 4 - Arrays
 */
import java.util.Scanner;

public class InventoryDriver
{
    public static final double STORAGE_SPACE = 200;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        //RInventory EHSStore = new RInventory(STORAGE_SPACE);
        SInventory EHSStore = new SInventory(STORAGE_SPACE);
        char choice; 
        String name = "";
        String oldName;
        String newName;
        double cost;
        double size;
        int quantitySold;
        int quantityPurchase;
        boolean confirmed;

        menu();
        do
        {
            System.out.print("What choice would you like(a,d,c,p,s,l,q)? ");
            choice = s.next().toUpperCase().charAt(0);
            switch (choice)
            {
                case 'A':
                System.out.println("What is the name of the item?");
                name = s.next();
                System.out.println("What is the cost of the item?");
                cost = s.nextDouble();
                System.out.println("What is the size of the item?");
                size = s.nextDouble();
                confirmed = EHSStore.add(name, cost, size);
                if(confirmed)
                {
                    System.out.println("The " + name + " has been successfully added.");
                }
                else
                {
                    System.out.println("The " + name + " could not be added.");
                }
                break;
                case 'D':
                System.out.println("What is the old name of the item?");
                oldName = s.next();
                confirmed = EHSStore.delete(oldName);
                if(confirmed)
                {
                    System.out.println("The " + name + " has been successfully deleted.");
                }
                else
                {
                    System.out.println("The " + name + " could not be deleted.");
                } 
                break;
                case 'C':
                System.out.println("What is the name of the item?");
                name = s.next();
                System.out.println("What is the new name of the item?");
                newName = s.next();
                System.out.println("What is the cost of the item?");
                cost = s.nextDouble();
                System.out.println("What is the size of the item?");
                size = s.nextDouble();
                confirmed = EHSStore.change(name, newName, cost, size);
                if(confirmed)
                {
                    System.out.println("The " + name + " has been successfully changed.");
                }
                else
                {
                    System.out.println("The " + name + " could not be changed.");
                }
                break;
                case 'S':
                System.out.print("What is the name of the item?");
                name = s.next();
                System.out.print("How many have been sold?");
                quantitySold = s.nextInt();
                confirmed = EHSStore.sell(name, quantitySold);
                if(confirmed)
                {
                    System.out.println("The " + name + " has been successfully sold.");
                }
                else
                {
                    System.out.println("The " + name + " could not be sold.");
                }
                break;
                case 'P':
                System.out.println("What is the name of the item?");
                name = s.next();
                System.out.println("How many have been purchased?");
                quantityPurchase = s.nextInt();
                confirmed = EHSStore.purchase(name, quantityPurchase);
                if(confirmed)
                {
                    System.out.println("The " + name + " has been successfully purchased.");
                }
                else
                {
                    System.out.println("The " + name + " could not be purchased.");
                }
                break;
                case 'L':
                EHSStore.list();
                break;
                case 'Q':
                break;
                default:
                System.out.println("Please enter one of the menu choices.");
            }
        }
        while (choice != 'q');
    }

    public static void menu()
    {
        System.out.print("What would you like to do?");
        System.out.println(" A - Add an item to the inventory");
        System.out.println(" D - Delete an item in the inventory");
        System.out.println(" C - Change an item in the inventory");
        System.out.println(" S - Sell an item in the inventory");
        System.out.println(" P - Purchase an item from the inventory");
        System.out.println(" L - List the inventory"); 
    }
}
