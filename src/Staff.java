import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class Staff extends User {
    Scanner sc = new Scanner(System.in);
    public Staff(String username, String password) {
        super(username, password, "Staff");
    }

    @Override
    public void view() {
        System.out.println("          +-----------------------------------+");
        System.out.println("          |          Staff Dashboard          |");
        System.out.println("          +-----------------------------------+");
        System.out.println("          |  1. Manage Cargo                  |");
        System.out.println("          |  2. Update Inventory              |");
        System.out.println("          |  3. Manage Ships                  |");
        System.out.println("          +-----------------------------------+");
    }
    public static String getUser(String user){
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if (details[0].equals(user)){
                    return line;
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }
    public void manageCargo() {
        System.out.println("          +------------------------------------------+");
        System.out.println("          | Enter customers username To manage Cargo | ");
        System.out.println("          +------------------------------------------+");
        System.out.print("          ");
        String user = sc.next();
        if(getUser(user) != null){
            System.out.println("          +-----------------------------------+");
            System.out.println("          | 1. Manage Cargo info              |");
            System.out.println("          | 2. manage Tracking info           |");
            System.out.println("          | 3. Add Delivery info              |");
            System.out.println("          +-----------------------------------+");
            int choose = sc.nextInt();
            List<String> loadCargo = Cargo.loadCargo(user);
            switch (choose){
                case 1:
                    try{
                    if (loadCargo!=null && !loadCargo.isEmpty()){
                        System.out.println("          +-----------------------------------+");
                        int i = 1;
                        System.out.println("          User " +  user + " have cargo(s) : ");
                        for (String cargo : loadCargo){
                            String[] carg = cargo.split(",");
                            System.out.println("          "+i +". " +"Cargo id : "+ carg[1]);
                            System.out.println("               "+ "Product category: "+ carg[2]);
                            System.out.println("               "+ "Cargo Status: "+ carg[5]);
                            i++;
                        }
                        System.out.println("          +-----------------------------------+");
                        System.out.println("          Add another cargo for user ? (yes/no)");
                        System.out.println("          +-----------------------------------+");
                        try{
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("yes")){
                                System.out.println("          => Enter ID : ");
                                String id = sc.next();
                                sc.nextLine();
                                System.out.print("          => Enter Category : ");
                                String description = sc.nextLine();
                                System.out.print("          => Enter weight : ");
                                int weight = sc.nextInt();
                                System.out.print("          => Enter destination : ");
                                String destination = sc.nextLine();
                                sc.nextLine();
                                System.out.print("          => Enter status : ");
                                String status = sc.nextLine();
                                Cargo newCargo = new Cargo(user , id , description , weight , destination ,status);
                                newCargo.saveCargo();
                                break;
                            } else if (choice.equalsIgnoreCase("no")) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Error " + e.getMessage());
                        }

                    }
                    else {
                        System.out.println("          +--------------------------------------------+");
                        System.out.println("          | No cargo found add new for user ? (yes/no) |");
                        System.out.println("          +--------------------------------------------+");
                        String choice = sc.next();
                        if (choice.equalsIgnoreCase("yes")) {
                            System.out.println("          => Enter ID : ");
                            String id = sc.next();
                            sc.nextLine();
                            System.out.print("          => Enter Category : ");
                            String description = sc.nextLine();
                            System.out.print("          => Enter weight : ");
                            int weight = sc.nextInt();
                            sc.nextLine();
                            System.out.print("          => Enter destination : ");
                            String destination = sc.nextLine();
                            sc.nextLine();
                            System.out.print("          => Enter status : ");
                            String status = sc.nextLine();
                            Cargo newCargo = new Cargo(user, id, description, weight, destination, status);
                            newCargo.saveCargo();
                        } else if (choice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    }catch(Exception e){
                        System.out.println("Error occured : " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        List<String> cargoIds = Cargo.getCargoid(user);
                        if (cargoIds != null && !cargoIds.isEmpty()) {
                            int i = 1;
                            for (String cargoId : cargoIds) {
                                System.out.println("          +--------------------------------------+");
                                System.out.println("          " + i + ". " + cargoId);
                                i++;
                            }
                            System.out.println("          +--------------------------------------+");
                            System.out.println("          +--------------------------------------+");
                            System.out.println("          |     Select cargo to add Tracking     |");
                            System.out.println("          |        (select serial number)        |");
                            System.out.println("          +--------------------------------------+");

                            try {
                                System.out.print("          ");
                                int id = sc.nextInt();
                                String cargoid = cargoIds.get(id - 1);
                                sc.nextLine();
                                System.out.print("          => Enter Current location : ");
                                String currentLocation = sc.nextLine();
                                Date EstimatedDate = null;
                                while (EstimatedDate == null) {
                                    try {
                                        System.out.println("          => Enter a Estimated delivery Date in format (mm/dd/yyyy)");
                                        String dateInp = sc.nextLine();
                                        EstimatedDate = new Date(dateInp);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("          => Invalid Date format try again");
                                    }
                                }
                                Tracking newTracking = new Tracking(cargoid, currentLocation, EstimatedDate);
                                newTracking.save();
                            } catch (Exception e) {
                                System.out.println("Error " + e.getMessage());
                            }
                        }else {
                            System.out.println("          +------------------------------------------------+");
                            System.out.println("          | User don't have any cargo to set Tracking info |");
                            System.out.println("          +------------------------------------------------+");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occured : " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        List<String> cargoIds = Cargo.getCargoid(user);
                        if (cargoIds != null && !cargoIds.isEmpty()) {
                            int i = 1;
                            for (String cargoId : cargoIds) {
                                System.out.println("          +--------------------------------------+");
                                System.out.println("          " + i + ". " + cargoId);
                                i++;
                            }
                            System.out.println("          +--------------------------------------+");
                            System.out.println("          +--------------------------------------+");
                            System.out.println("          |     Select cargo to add Delivery     |");
                            System.out.println("          |        (select serial number)        |");
                            System.out.println("          +--------------------------------------+");
                            try {
                                System.out.print("          ");
                                int id = sc.nextInt();
                                String cargoid = cargoIds.get(id - 1);
                                sc.nextLine();
                                String deliveryid = cargoid +"-"+ user ;
                                System.out.println("          => Enter delivery status - Delivered (yes/no)");
                                System.out.print("          ");
                                String deliveryStatus = sc.next();
                                String pickupstatus = null;
                                while (pickupstatus == null) {
                                    if (deliveryStatus.equalsIgnoreCase("yes")) {
                                        pickupstatus = "yes";
                                    } else if (deliveryStatus.equalsIgnoreCase("no")) {
                                        pickupstatus = "no";
                                    } else {
                                        System.out.println("          => Enter a valid choice : ");
                                    }
                                }
                                Delivery newDelivary = new Delivery(user , deliveryid , cargoid , pickupstatus , deliveryStatus);
                                newDelivary.saveDelivery();
                            } catch (Exception e) {
                                System.out.println("Error " + e.getMessage());
                            }
                        }
                        else {
                            System.out.println("        +---------------------------------------------------------+");
                            System.out.println("        |   Can't add Delivery info as No cargo found for User !  |");
                            System.out.println("        +---------------------------------------------------------+");
                        }
                    } catch (Exception e) {
                        System.out.println("Error occured : " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("          => Invalid input");
            }

        } else{
            System.out.println("          => No user found for given username !");
        }

    }


    public void updateInventory() {
        System.out.println("          +-----------------------------------+");
        System.out.println("          | 1. Show item                      |");
        System.out.println("          | 2. Add item                       |");
        System.out.println("          | 3. Delete item                    |");
        System.out.println("          +-----------------------------------+");
        try{
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.println("          +-----------------------------------+");
                    System.out.println("          => Enter item name to search : ");
                    String Itemname = sc.nextLine();
                    List<String> items = Inventory.loadInventory(Itemname);
                    if (items != null && !items.isEmpty()){
                        System.out.println("          => Found Items : ");
                        System.out.println("          +-----------------------------------+");
                        for (int i = 0 ; i< items.size() ; i++){
                            System.out.println("          "+ i+1 + ".  " + items.get(i));
                        }
                        System.out.println("          +-----------------------------------+");
                    }
                    else {
                        System.out.println("          +-----------------------------------+");
                        System.out.println("          | No item found for given item name |");
                        System.out.println("          +-----------------------------------+");
                    }
                    break;
                case 2:
                    try{
                        System.out.println("          +-----------------------------------+");
                        System.out.print("          => Enter item id : ");
                        String itemid = sc.nextLine();
                        System.out.print("          => Enter item name : ");
                        String itemName = sc.nextLine();
                        System.out.print("          => Enter item Quantity : ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        String location = "warehouse";
                        Inventory newInventory = new Inventory(itemid , itemName , quantity,location);
                        newInventory.saveInventory();
                        System.out.println("          +-----------------------------------+");
                    } catch (Exception e) {
                        System.out.println("Error : " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("          +----------------------------------------+");
                    System.out.println("          | Enter item id to remove from inventory |");
                    System.out.println("          +----------------------------------------+");
                    String itemid = sc.next();
                    Inventory.removeInventory(itemid);
                default:
                    System.out.println("          => Invalid choice");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void manageShips() {
        System.out.println("          +-----------------------------------+");
        System.out.println("          | 1. Show ships                     |"  );
        System.out.println("          | 2. Add ship                       |");
        System.out.println("          +-----------------------------------+");
        int choose = sc.nextInt();
        switch (choose){
            case 1:
                List<String> ships = Ship.loadShips();
                if (ships != null && !ships.isEmpty()){
                    System.out.println("          +-----------------------------------+");
                    System.out.println("          |    Ships                         |");
                    System.out.println("          +-----------------------------------+");
                    int i =1;
                    for (String ship : ships){
                        String[] shipo = ship.split(",");
                        System.out.println("          "+i +". " +"Ship id : "+ shipo[0]);
                        System.out.println("               "+ "Company name: "+ shipo[1]);
                        System.out.println("               "+ "Route: "+ shipo[3]);
                        i++;
                    }
                    System.out.println("          +-----------------------------------+");
                }else{
                    System.out.println("          +-----------------------------------+");
                    System.out.println("          |          No ships found           |");
                    System.out.println("          +-----------------------------------+");
                }

                break;
            case 2:
                System.out.println("          +-----------------------------------+");
                sc.nextLine();
                System.out.print("          => Give Ships id : ");
                String id = sc.nextLine();
                System.out.print("          => Enter ship's company name : ");
                String company = sc.nextLine();
                System.out.print("          => Enter capacity : ");
                int capacity = sc.nextInt();
                sc.nextLine();
                System.out.print("          => Enter ship's route : ");
                String route = sc.nextLine();
                System.out.print("          => Enter departure date : (dd-mm-yyyy) ");
                String depDate = sc.nextLine();
                Ship newShip = new Ship(id , company , capacity , route , depDate);
                newShip.saveShip();
                System.out.println("          +-----------------------------------+");
                break;
            default:
                System.out.println("          => Invalid choice");
        }
    }
}