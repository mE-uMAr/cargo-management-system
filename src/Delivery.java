import java.io.*;
public class Delivery {
    private String username;
    private String deliveryId;
    private String cargoId;
    private String pickupStatus;
    private String deliveryStatus;


    public Delivery(String username, String deliveryId, String cargoId, String pickupStatus, String deliveryStatus) {
        this.username = username;
        this.deliveryId = deliveryId;
        this.cargoId = cargoId;
        this.pickupStatus = pickupStatus;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return username + "," + deliveryId + "," + cargoId + "," + pickupStatus + "," + deliveryStatus ;
    }

    public void saveDelivery() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/delivery.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Delivery saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving delivery: " + e.getMessage());
        }
    }

    public static void loadUserDeliveries(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/delivery.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    System.out.println("Delivery: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading deliveries: " + e.getMessage());
        }
    }
}