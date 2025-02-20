import java.io.*;
import java.util.*;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    @Override
    public void view() {
        System.out.println("          +-----------------------------------+");
        System.out.println("          |         Admin Dashboard           |");
        System.out.println("          +-----------------------------------+");
        System.out.println("          |                                   |");
        System.out.println("          | 1. Add User                       |");
        System.out.println("          | 2. Remove User                    |");
        System.out.println("          | 3. View Inventory                 |");
        System.out.println("          |                                   |");
        System.out.println("          +-----------------------------------+");
    }

    public void addUser(String username, String password, String role) {
        signUp(username, password, role);
    }

    public void removeUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            StringBuilder newData = new StringBuilder();
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (!line.split(",")[0].equals(username)) {
                    newData.append(line).append("\n");
                } else {
                    found = true;
                }
            }

            if (found) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt"))) {
                    writer.write(newData.toString());
                    System.out.println("          +-----------------------------------+");
                    System.out.println("          |    User removed successfully.     |");
                    System.out.println("          +-----------------------------------+");
                }
            } else {
                System.out.println("          |           User not found.         |");
            }
        } catch (IOException e) {
            System.out.println("Error during user removal: " + e.getMessage());
        }
    }
    public void showInventory(){
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Inventory.txt"))) {
            String line;
            List<String> item = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("          +-----------------------------------+");
                System.out.println("           Item id : " + data[0] );
                System.out.println("           Item name : " + data[1]);
                System.out.println("           Quantity : " + data[2]);
                System.out.println("           Location : " + data[3]);
                System.out.println("          +-----------------------------------+");
            }
            if (line == null){
                System.out.println("           \n          No inventory found\n          ");

            }
    } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}