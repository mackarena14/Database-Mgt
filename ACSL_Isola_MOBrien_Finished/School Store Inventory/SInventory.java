/**
 * This version of class Inventory will implement adding, deleting, changing, purchasing, selling, and
 * listing from an existing inventory assuming random order.
 * 
 * @author Jason Bowen
 * @version A.P. Computer Science - Unit 4 - Arrays
 */
import java.text.NumberFormat;
import java.util.ArrayList;

public class SInventory implements InventoryInterface
{
    private static final int SQIN_PER_SQFT = 144;
    private ArrayList<Item> listOfItems;
    private double storageSpace;
    private double totalSpace;
    private double totalValue;
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance();

    //Constructor for objects of class Inventory
    public SInventory(double storage)
    {
        listOfItems = new ArrayList<Item>();
        storageSpace = storage;
        totalSpace = 0;
        totalValue = 0;
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
        if (index < listOfItems.size() && name.equals(listOfItems.get(index).getItemName()))
        {
            System.out.println("Duplicate.");
            return false;
        }
        Item newItem = new Item(name, cost, size);
        listOfItems.add(index, newItem);
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
        if (index >= listOfItems.size() ||
        !name.equals(listOfItems.get(index).getItemName()))
        {
            System.out.println("Item not found.");
            return false;
        }
        totalSpace -= listOfItems.get(index).totalSize();
        totalValue -= listOfItems.get(index).totalCost();
        listOfItems.remove(index);
        return true;
    }

    /**
     * Change an item already in the inventory
     * 
     * @param   name, quantity, cost/unit, size/unit of the item being changed
     *          values of "" or 0 if they are not to be changed
     * @return  boolean value to indicate whether it was successful 
     */
    public boolean change(String name, String newName, double cost, double size)
    {
        int index = search(name);
        if (index >= listOfItems.size() && !name.equals(listOfItems.get(index).getItemName()))
        {
            System.out.println("Item not found.");
            return false;
        }
        Item oldItem = listOfItems.get(index);
        if (newName.equals("\"\"") || newName.equals(""))
        {
            newName = oldItem.getItemName();
        }
        if (cost == 0)
        {
            cost = oldItem.getUnitCost();
        }
        if (size == 0)
        {
            size = oldItem.getUnitSize();
        }
        Item newItem = new Item(newName, cost, size);
        totalSpace -= oldItem.getQuantity() * oldItem.getUnitSize();
        totalValue -= oldItem.getQuantity() * oldItem.getUnitCost();
        totalSpace += oldItem.getQuantity() * size;
        totalValue += oldItem.getQuantity() * cost;
        listOfItems.remove(index);
        index = search(newName);
        listOfItems.add(index, newItem);
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
        if (index >= listOfItems.size() ||
        !name.equals(listOfItems.get(index).getItemName()))
        {
            System.out.println("Item not found.");
            return false;
        }
        if (listOfItems.get(index).getUnitSize() * quantity + totalSpace > storageSpace * SQIN_PER_SQFT)
        {
            System.out.println("Not enough space.");
            return false;
        }
        listOfItems.get(index).setQuantity(listOfItems.get(index).getQuantity() + quantity);
        totalSpace += listOfItems.get(index).getUnitSize() * quantity;
        totalValue += listOfItems.get(index).getUnitCost()* quantity;
        System.out.println("Cost: " + listOfItems.get(index).getUnitCost()* quantity);
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
        if (index >= listOfItems.size() ||
        !name.equals(listOfItems.get(index).getItemName()))
        {
            System.out.println("Item not found.");
            return false;
        }
        if (listOfItems.get(index).getQuantity() < quantity)
        {
            System.out.println("Out of item.");
            return false;
        }
        listOfItems.get(index).setQuantity(listOfItems.get(index).getQuantity() - quantity);
        totalSpace -= listOfItems.get(index).getUnitSize() * quantity;
        totalValue -= listOfItems.get(index).getUnitCost()* quantity;
        System.out.println("Sale: " + listOfItems.get(index).getUnitCost()* quantity);
        return true;
    }

    /**
     * List the items, quanitities, costs, and sizes of all items in the inventory
     * 
     * @param   none
     * @return  nothing
     */
    public void list()
    {
        System.out.println("Item Name\t" + "Quantity\t" + "Cost\t" + "Size\t" + "Total Cost\t" + "Total Size");
        for (int x = 0; x < listOfItems.size(); x++)
        {
            System.out.println(listOfItems.get(x).toString() + "\t\t" + listOfItems.get(x).totalCost() + "\t\t" + listOfItems.get(x).totalSize());
        }
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
        int low = 0, high = listOfItems.size() - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (listOfItems.get(mid).getItemName().equals(name))
                return mid;
            if (name.compareTo(listOfItems.get(mid).getItemName()) > 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
}
