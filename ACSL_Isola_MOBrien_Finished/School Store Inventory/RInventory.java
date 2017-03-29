/**
 * This version of class Inventory will implement adding, deleting, changing, purchasing, selling, and
 * listing from an existing inventory assuming random order.
 * 
 * Rachel Choi 
 * A.P. Computer Science - Unit 6 - ArrayLists
 * 
 */
import java.text.NumberFormat;
import java.util.ArrayList;

public class RInventory implements InventoryInterface
{
    private static final int SQIN_PER_SQFT = 144;
    private ArrayList<Item> listOfItems;
    private double storageSpace;
    private double totalValue;
    private double totalSpace;
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance();

    //Constructor for objects of class Inventory
    public RInventory(double storage)
    {
        listOfItems = new ArrayList<Item>();
        totalSpace = 0;
        totalValue = 0;
        storageSpace = storage;
    }

    /**
     * Add an item to the inventory
     * 
     * @param   name, cost/unit, size/unit of the item being added
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean add(String name, double cost, double size)
    {
        int index = search(name);
        if(index != -1){
            System.out.println("Duplicate.");
            return false;
        }
        Item adding = new Item(name, cost, size);
        listOfItems.add(adding);
        return true;
    }

    /**
     * Delete an item from the inventory
     * 
     * @param   name of the item being deleted
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean delete(String name)
    {
        int index = search(name);
        if(index == -1){ 
            System.out.println("Not found.");
            return false;
        }
        Item removing = listOfItems.get(index); 
        totalValue -= removing.totalCost(); 
        totalSpace -= removing.totalSize(); 
        //replace Xth item with Nth and decrease N by 1
        int last = listOfItems.size()-1;
        listOfItems.set(index, listOfItems.get(last));
        listOfItems.remove(last);
        return true;
    }

    /**
     * Change an item already in the inventory
     * 
     * @param   name, quantity, cost/unit, size/unit of the item being changed
     *                  values of "" or 0 if they are not to be changed
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean change(String name, String newName, double cost, double size)
    {
        int index = search(name);
        if(index == -1){
            System.out.println("Not found.");
            return false;
        }
        Item oldItem = listOfItems.get(index);
        totalSpace -= oldItem.getQuantity() * oldItem.getUnitSize();
        totalValue -= oldItem.getQuantity() * oldItem.getUnitCost();
        if(newName.equals(""))
            name = oldItem.getItemName();
        if(cost == 0)
            cost = oldItem.getUnitCost();
        if(size == 0)
            size = oldItem.getUnitSize();
        totalSpace += oldItem.getQuantity() * size;
        totalValue += oldItem.getQuantity() * cost;
        listOfItems.set(index, oldItem);
        return true;
    }

    /**
     * Purchase an item for the inventory
     * 
     * @param   name, quantity of the item being purchased
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean purchase(String name, int quantity)
    {
        int index = search(name);
        if(index == -1){
            System.out.println("Not found.");
            return false;
        }
        Item purchasingItem = listOfItems.get(index);
        if(purchasingItem.getUnitSize() * quantity + totalSpace > storageSpace * SQIN_PER_SQFT){
            System.out.println("Out of space.");
            return false;
        }
        totalSpace += purchasingItem.getUnitSize() * quantity;
        totalValue += purchasingItem.getUnitCost() * quantity;
        listOfItems.set(index, purchasingItem);
        System.out.println("Value of purchase: " + quantity);
        return true;
    }

    /**
     * Sell an item that is in the inventory
     * 
     * @param   name, quantity of the item being sold
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean sell(String name, int quantity)
    {
        int index = search(name);
        if(index == -1){
            System.out.println("Not found.");
            return false;
        }
        Item sellingItem = listOfItems.get(index);
        if(sellingItem.getQuantity() < quantity){
            System.out.println("Out of stock.");
            return false;
        }
        totalSpace -= sellingItem.getUnitSize() * quantity;
        totalValue -= sellingItem.getUnitCost()* quantity;
        listOfItems.set(index, sellingItem);
        System.out.println("Value of transaction: " + quantity);
        return true;
    }

    /**
     * List the items, quantities, costs, and sizes of all items in the inventory
     * 
     * @param   none
     * @return  nothing
     */
    public void list()
    {
        //heading
        System.out.println("Item Name\t" + "Quantity\t" + "Cost\t" + "Size\t" + "Total Cost\t" + "Total Size");
        for (int x = 0; x < listOfItems.size(); x++)
            System.out.println(listOfItems.get(x).toString() + "\t\t" + listOfItems.get(x).totalCost() + "\t\t" + listOfItems.get(x).totalSize());
        System.out.println("Total Space: \t\t" + totalSpace + "\t\t" + "Total Value: \t\t" + totalValue);
        System.out.println();
    }

    /**
     * Search for an item in the inventory using a linear search
     * 
     * @param   name of the item
     * @return  position where it is found, -1 if not found
     */
    private int search(String name)

    {
        for (int x = 0; x < listOfItems.size(); x++){
            if (listOfItems.get(x).equals(name))
                return x;
        }
        return -1;
    }

    /**
     * Sort the inventory by name using the Max/Min Sort
     * 
     * @param   none
     * @return  none
     */
    private void sort()
    {
        for (int x = 0; x < listOfItems.size()-1; x++){
            for (int y = x + 1; y < listOfItems.size(); y++){
                if(listOfItems.get(x).compareTo(listOfItems.get(y)) > 0){
                    Item temp = listOfItems.get(x);
                    listOfItems.set(x, listOfItems.get(y));
                    listOfItems.set(y, temp);
                }
            }
        }
    }
}
