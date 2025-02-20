import java.util.*;

public class Customer extends User {

    Scanner sc = new Scanner(System.in);

    public Customer(String username, String password) {
        super(username, password, "Customer");
    }

    @Override
    public void view() {
        System.out.println("          +-----------------------------------+");
        System.out.println("          |        Customer Dashboard         |");
        System.out.println("          +-----------------------------------+");
        System.out.println("          |  1. Track Cargo                   |");
        System.out.println("          |  2. Add Cargo                     |");
        System.out.println("          |  3. Request Insurance             |");
        System.out.println("          +-----------------------------------+");

    }
    String user = sessionManager.getUsername();
    public void trackCargo() {
        try {
            List<String> cargoIds = Cargo.getCargoid(user);
            if (cargoIds != null && !cargoIds.isEmpty()) {
                int i = 1;
                for (String cargoId : cargoIds){
                    System.out.println("          +--------------------------------------+");
                    System.out.println("          " + i +". " + cargoId);
                    i++;
                }
                System.out.println("          +--------------------------------------+");
                System.out.println("          +--------------------------------------+");
                System.out.println("          |        Select cargo to  track        |");
                System.out.println("          |        (select serial number)        |");
                System.out.println("          +--------------------------------------+");
                try {
                    System.out.print("          ");
                    int id = sc.nextInt();
                    String trackid = cargoIds.get(id-1);
                    sc.nextLine();
                    String tracking = Tracking.loadTracking(trackid);
                    if (tracking != null){
                        String[] track = tracking.split(",");
                        System.out.println("          +--------------------------------------+");
                        System.out.println("          |     Tracking for given cargo id      |" );
                        System.out.println("          +--------------------------------------+");
                        System.out.println("          => Tracking id : " + track[0]);
                        System.out.println("          => Current Location : " + track[1]);
                        System.out.println("          => Estimated Delivery date : " + track[2]);
                    }
                    else {
                        System.out.println("No tracking found for Cargo id : " + trackid);
                    }
                } catch (Exception e) {
                    System.out.println("An error occured" + e.getMessage());
                }


            }
            else {
                System.out.println("---- You don't have any Cargo to show ----");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    public void addCargo(){
        System.out.println("          +--------------------------+");
        System.out.print("          => Enter ID :  ");
        String id = sc.next();
        sc.nextLine();
        System.out.print("          => Enter Category : ");
        String description = sc.nextLine();
        System.out.print("          => Enter weight : ");
        int weight = sc.nextInt();
        sc.nextLine();
        System.out.print("          => Enter destination : ");
        String destination = sc.nextLine();
        Cargo newCargo = new Cargo(user , id , description , weight , destination ,"Shipped");
        newCargo.saveCargo();
    }

    public void requestInsurance() {
        try {
            List<String> cargoIds = Cargo.getCargoid(user);
            if (cargoIds != null && !cargoIds.isEmpty()) {
                int i = 1;
                for (String cargoId : cargoIds){
                    System.out.println("          +--------------------------------------+");
                    System.out.println("          " + i +". " + cargoId);
                    i++;
                }
                System.out.println("          +--------------------------------------+");
                System.out.println("          +--------------------------------------+");
                System.out.println("          |   Select cargo to request insurance  |");
                System.out.println("          |        (select serial number)        |");
                System.out.println("          +--------------------------------------+");
                try{
                    System.out.print("          ");
                    int id = sc.nextInt();
                    String cargoid = cargoIds.get(id-1);
                    sc.nextLine();
                    String insurance = LuggageInsurance.loadInsurance(cargoid);
                    if (insurance != null){
                        String[] request = insurance.split(",");
                        System.out.println("          +--------------------------------------+");
                        System.out.println("          |  Insurance for given cargo id Exists |");
                        System.out.println("          +--------------------------------------+");
                        System.out.println("          => Insurance id : " + request[2]);
                        System.out.println("          => With Amount : " + request[3]);

                    }
                    else {
                        String insuranceid = "a" + String.valueOf(new Random().nextInt(9999)) + "z";
                        System.out.print("          Enter amount of cargo " + cargoid + " : ");
                        System.out.print("          ");
                        double amount = sc.nextDouble();
                        LuggageInsurance newInsurance = new LuggageInsurance(user , cargoid , insuranceid , amount);
                        newInsurance.save();
                    }
                }catch (Exception e){
                    System.out.println("Error : " + e.getMessage());
                }

            }
            else {
                System.out.println("          You don't have any Cargo to apply for insurance");
            }
        } catch(Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

}