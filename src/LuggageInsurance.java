import java.io.*;
public class LuggageInsurance {
    private String username;
    private String insuranceId;
    private String cargoId;
    private double amount;

    public LuggageInsurance(String username , String insuranceId, String cargoId, double amount) {
        this.username = username;
        this.insuranceId = insuranceId;
        this.cargoId = cargoId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return username + "," + insuranceId + "," + cargoId + "," + amount;
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/luggage_insurance.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
            System.out.println("          => Insurance data saved successfully.");
        } catch (IOException e) {
            System.out.println("          => Error saving insurance data: " + e.getMessage());
        }
    }

    public static String loadInsurance(String cargoid) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/luggage_insurance.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] detail = line.split(",");
                if (detail[1].equalsIgnoreCase(cargoid)){
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("          => Error loading insurance data: " + e.getMessage());
        }
        return null;
    }
}