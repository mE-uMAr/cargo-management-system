import java.io.*;
import java.util.Date;
public class Tracking {
    private String cargoId;
    private String currentLocation;
    private Date estimatedDeliveryDate;

    public Tracking(String cargoId, String currentLocation, Date estimatedDeliveryDate) {
        this.cargoId = cargoId;
        this.currentLocation = currentLocation;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    @Override
    public String toString() {
        return cargoId + "," + currentLocation + "," + estimatedDeliveryDate;
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/tracking.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Tracking data saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving tracking data: " + e.getMessage());
        }
    }

    public static String loadTracking(String cargoId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/tracking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(cargoId)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("          => Error loading tracking data: " + e.getMessage());
        }
        return null;
    }
}