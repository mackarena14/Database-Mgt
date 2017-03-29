/**
 * Write a description of interface InventoryList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface InventoryInterface
{
    boolean add(String name, double cost, double size);
    boolean delete(String name);
    boolean change(String name, String newName, double cost, double size);
    boolean purchase(String name, int quantity);
    boolean sell(String name, int quantity);
    void list();
}
