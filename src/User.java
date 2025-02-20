import java.io.*;
public abstract class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public abstract void view();

    @Override
    public String toString() {
        return username + "," + password + "," + role;
    }

    public static void signUp(String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt", true))) {
            writer.write(username + "," + password + "," + role);
            writer.newLine();
            System.out.println("          => User signed up successfully.");
        } catch (IOException e) {
            System.out.println("          => Error during signup: " + e.getMessage());
        }
    }

    public static User login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username) && details[1].equals(password)) {

                    sessionManager.setUsername(username);

                    switch (details[2]) {
                        case "Admin":
                            return new Admin(username, password);
                        case "Staff":
                            return new Staff(username, password);
                        case "Customer":
                            return new Customer(username, password);
                        default:
                            System.out.println("          => Invalid role in data.");
                            return null;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("          => Error during login: " + e.getMessage());
        }
        System.out.println("          => Invalid username or password.");
        return null;
    }
    public static String checkUser(String username){
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if (details[0].equals(username)){
                    return details[0];
                }
            }
        } catch (Exception e) {
            System.out.println("          => Error : " + e.getMessage());
        }
        return null;
    }
}