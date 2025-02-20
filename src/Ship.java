import java.io.*;
import java.util.*;
public class Ship {
    private String shipId;
    private String name;
    private int capacity;
    private String route;
    private String departureDate;

    public Ship(String shipId, String name, int capacity, String route, String departureDate) {
        this.shipId = shipId;
        this.name = name;
        this.capacity = capacity;
        this.route = route;
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return shipId + "," + name + "," + capacity + "," + route + "," + departureDate;
    }

    public void saveShip() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/ships.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Ship data saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving ship data: " + e.getMessage());
        }
    }

    public static List<String> loadShips() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/ships.txt"))) {
            String line;
            List<String> ships = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                ships.add(line);
            }
            return ships;
        } catch (IOException e) {
            System.out.println("          => Error loading ship data: " + e.getMessage());
        }
        return null;
    }
}