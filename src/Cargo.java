import java.util.*;
import java.io.*;
public class Cargo {
    private String username;
    private String id;
    private String description;
    private int weight;
    private String destination;
    private String status;

    public Cargo(String username, String id, String description, int weight, String destination, String status) {
        this.username = username;
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.destination = destination;
        this.status = status;
    }

    @Override
    public String toString() {
        return username + "," + id + "," + description + "," + weight + "," + destination + "," + status;
    }

    public void saveCargo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/cargo.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Cargo saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving cargo: " + e.getMessage());
        }
    }
    public static List<String> getCargoid(String username){
        List<String> cargoId = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/cargo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    cargoId.add(data[1]);
                }
            }
            return cargoId;
        } catch (IOException e) {
            System.out.println("          => Error loading cargo: " + e.getMessage());
        }
        return null;
    }
    public static List<String> loadCargo(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/cargo.txt"))) {
            String line;
            List<String> cargo = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    cargo.add(line);
                }
            }
            return cargo;
        } catch (IOException e) {
            System.out.println("          => Error loading cargo: " + e.getMessage());
        }
        return null;
    }
}