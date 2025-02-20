import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String itemId;
    private String itemName;
    private int quantity;
    private String location;

    public Inventory(String itemId, String itemName, int quantity, String location) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.location = location;
    }

    @Override
    public String toString() {
        return itemId + "," + itemName + "," + quantity + "," + location;
    }

    public void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/inventory.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Inventory data saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving inventory data: " + e.getMessage());
        }
    }

    public static List<String> loadInventory(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Inventory.txt"))) {
            String line;
            List<String> item = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(name)) {
                    item.add(line);
                }
            }
            return item;
        } catch (IOException e) {
            System.out.println("          => Error loading cargo: " + e.getMessage());
        }
        return null;
    }
    public static void removeInventory(String itemid) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/inventory.txt"))) {
            String line;
            StringBuilder newData = new StringBuilder();
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (!line.split(",")[0].equals(itemid)) {
                    newData.append(line).append("\n");
                } else {
                    found = true;
                }
            }
                if (found) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/inventory.txt"))) {
                        writer.write(newData.toString());
                        System.out.println("          => Inventory removed successfully.");
                    }
                } else {
                    System.out.println("          => Inventory not found.");
                }
            } catch (IOException e) {
                System.out.println("          => Error during removal: " + e.getMessage());
            }
    }
}