public class Item implements Comparable
{
    // instance variables
    private String itemName;
    private int quantityOnHand;
    private double unitSize;
    private double unitCost;

    //  Constructors
    public Item()
    {
        itemName = "item";
        quantityOnHand = 0;
        unitSize = 1;
        unitCost = 1;
    }

    public Item(String name, double cost, double size)
    {
        name = itemName;
        quantityOnHand = 0;
        cost = unitCost;
        size = unitSize;
    }

    public Item(Item oldItem)
    {
        itemName = oldItem.itemName;
        quantityOnHand = oldItem.quantityOnHand;
        unitCost = oldItem.unitCost;
        unitSize = oldItem.unitSize;
    }

    // Accessor functions
    public String getItemName()
    {
        return itemName;
    }

    public double getUnitSize()
    {
        return unitSize;
    }

    public double getUnitCost()
    {
        return unitCost;
    }

    public int getQuantity()
    {
        return quantityOnHand;
    }
    // Mutator functions
    public void setItemName(String name)
    {
        itemName = name;
    }

    public void setUnitSize(double size)
    {
        unitSize = size;
    }

    public void setUnitCost(double cost)
    {
        unitCost = cost;
    }

    public void setQuantity(int quantity)
    {
        quantityOnHand = quantity;
    }

    //Calculating Methods
    public double totalCost()
    {
        return unitCost * quantityOnHand;
    }

    public double totalSize()
    {
        return unitSize * quantityOnHand;
    }

    public int compareTo(Object item)
    {
        if(this.itemName.compareTo(((Item)item).itemName) == 0)
        {
            return 0;
        }
        else if(this.itemName.compareTo(((Item)item).itemName) == 1)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public String toString()
    {
        String str = itemName;
        if (itemName.length() < 8)
        {
            str += "\t";
        }
        return str + "\t" + quantityOnHand + "\t" + unitCost + "\t" + unitSize;
    }
}
