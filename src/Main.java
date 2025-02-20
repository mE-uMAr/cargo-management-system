import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



            System.out.println("=======================================================");
            System.out.println("=          Welcome to the International Cargo         =");
            System.out.println("=               Service Management System             =");
            System.out.println("=======================================================");
            System.out.println();
            System.out.println("              +--------------------------+");
            System.out.println("              |          MENU            |");
            System.out.println("              +--------------------------+");
            System.out.println("              |                          |");
            System.out.println("              | 1. Sign Up               |");
            System.out.println("              | 2. Log In                |");
            System.out.println("              |                          |");
            System.out.println("              +--------------------------+");
            System.out.print("                =>   Choose an option: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    signUp(scanner);
                } else if (choice == 2) {
                    User user = login(scanner);
                    if (user != null) {
                        handleUserActions(user, scanner);
                    }

                    
                } else {
                    System.out.println("Invalid choice.");
                }


            } catch (Exception e) {
                System.out.println("Error request " + e.getMessage());
                scanner.nextLine();
            }


    }


        private static void signUp (Scanner scanner){
            System.out.println();
            System.out.println("          +-----------------------------------+");
            System.out.println("          |           SIGN-UP FORM            |");
            System.out.println("          +-----------------------------------+");
            System.out.print("            =>   Enter username: ");
            String username = scanner.nextLine();
            String check = User.checkUser(username);
            if (check == null) {
                System.out.print("            =>   Enter password: ");
                String password = scanner.nextLine();
                System.out.println("\n           =>   Select role:");
                System.out.println("           +-------------------+");
                System.out.println("           | 1. Admin          |");
                System.out.println("           | 2. Staff          |");
                System.out.println("           | 3. Customer       |");
                System.out.println("           +-------------------+");
                System.out.print("           =>   Choose a role: ");
                int roleChoice = scanner.nextInt();
                scanner.nextLine();

                String role = "";
                if (roleChoice == 1) {
                    System.out.println("+--------------------------------------------------------+");
                    System.out.println("|    Enter company's Admin Signup Authentication Pin     | ");
                    System.out.println("+--------------------------------------------------------+");
                    boolean adminpin = false;
                    while (!adminpin) {
                        String admin_pin = scanner.next();
                        if (admin_pin.equals("1234")) {
                            adminpin = true;
                            role = "Admin";
                        } else {
                            System.out.println("Invalid admin Signup Authentication pin !");
                        }
                    }
                } else if (roleChoice == 2) {
                    role = "Staff";
                } else if (roleChoice == 3) {
                    role = "Customer";
                } else {
                    System.out.println("Invalid role.");
                    return;
                }

                User.signUp(username, password, role);

            } else {
                System.out.println("User already exist");
            }

        }

        private static User login (Scanner scanner){
            System.out.println();
            System.out.println("          +-----------------------------------+");
            System.out.println("          |             LOG-IN                |");
            System.out.println("          +-----------------------------------+");
            System.out.print("          =>   Enter username: ");
            String username = scanner.nextLine();

            System.out.print("          =>   Enter password: ");
            String password = scanner.nextLine();

            User user = User.login(username, password);
            if (user == null) {
                System.out.println("+------------------------------------------------+");
                System.out.println("|                  Login failed !                 |");
                System.out.println("+------------------------------------------------+");
            }
            return user;
        }

        private static void handleUserActions (User user, Scanner scanner){
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("          +-----------------------------------+");
                System.out.println("          |           USER DASHBOARD          |");
                System.out.println("          +-----------------------------------+");
                System.out.println("          Welcome, " + sessionManager.getUsername() + "!");
                user.view();
                System.out.print("          =>   Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (user instanceof Admin) {
                    handleAdminActions((Admin) user, choice, scanner);
                } else if (user instanceof Staff) {
                    handleStaffActions((Staff) user, choice, scanner);
                } else if (user instanceof Customer) {
                    handleCustomerActions((Customer) user, choice, scanner);
                }
                scanner.nextLine();
                System.out.print("Do you want to continue? (yes/no): ");
                String continueChoice = scanner.nextLine();
                running = continueChoice.equalsIgnoreCase("yes");
            }
        }

        private static void handleAdminActions (Admin admin,int choice, Scanner scanner){
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter new user's username: ");
                        String newUsername = scanner.nextLine();
                        String existingUser = User.checkUser(newUsername);
                        if (existingUser != null) {
                            System.out.println("User " + existingUser + " already exist!");
                            break;
                        } else {
                            System.out.print("Enter password: ");
                            String newPassword = scanner.nextLine();
                            System.out.print("Select role: \n1. Admin \n2. Staff \n3. Customer ");
                            int select = scanner.nextInt();
                            String role = "";
                            switch (select) {
                                case 1:
                                    role = "Admin";
                                    break;
                                case 2:
                                    role = "Staff";
                                    break;
                                case 3:
                                    role = "Customer";
                                    break;
                                default:
                                    System.out.println("Invalid Input");
                            }

                            admin.addUser(newUsername, newPassword, role);
                            break;
                        }
                    case 2:
                        System.out.print("Enter username to remove: ");
                        String usernameToRemove = scanner.nextLine();
                        admin.removeUser(usernameToRemove);
                        break;
                    case 3:
                        admin.showInventory();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("An error : " + e.getMessage());
            }

        }

        private static void handleStaffActions (Staff staff,int choice, Scanner scanner){
            switch (choice) {
                case 1:
                    staff.manageCargo();
                    break;
                case 2:
                    staff.updateInventory();
                    break;
                case 3:
                    staff.manageShips();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        private static void handleCustomerActions (Customer customer,int choice, Scanner scanner){
            switch (choice) {
                case 1:
                    customer.trackCargo();
                    break;
                case 2:
                    customer.addCargo();
                    break;
                case 3:
                    customer.requestInsurance();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
