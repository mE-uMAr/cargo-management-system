# Cargo Management System

This is a Java-based Cargo Management System designed to manage cargo, users, deliveries, inventory, and tracking information. The system supports different user roles such as Admin, Staff, and Customer, each with specific functionalities.

## Features

- **Admin Dashboard**:
  - Add User
  - Remove User
  - View Inventory

- **Staff Dashboard**:
  - Manage Cargo
  - Update Inventory
  - Manage Ships

- **Customer Dashboard**:
  - Track Cargo
  - Add Cargo
  - Request Insurance

## Project Structure

├── .gitignore  
├── .idea/  
│   ├── .gitignore  
│   ├── misc.xml  
│   ├── modules.xml  
│   └── workspace.xml  
├── Cargo management system.iml  
├── data/  
│   ├── cargo.txt  
│   ├── delivery.txt  
│   ├── inventory.txt  
│   ├── luggage_insurance.txt  
│   ├── ships.txt  
│   ├── tracking.txt  
│   └── users.txt  
├── src/  
|   ├── Admin.java  
|   ├── Cargo.java  
|   ├── Customer.java  
|   ├── Delivery.java  
|   ├── Inventory.java  
|   ├── LuggageInsurance.java  
|   ├── Main.java  
|   ├── sessionManager.java  
|   ├── Ship.java  
|   ├── Staff.java  
|   ├── Tracking.java  
|   └── User.java  
└── README.md  
 
## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor of your choice

### Running the Application

1. Clone the repository:
   ```sh
   git clone https://github.com/mE-uMAr/cargo-management-system.git
   ```

2. Navigate to the project directory:
    cd cargo-management-system3. Compile the Java files:
    ```sh
    javac -d bin src/*.java
    ```

4. Run the application:
    ```sh
    java -cp bin Main
    ```

## Contributing

1. Fork the repository.
2. Create your feature branch:
    ```sh
    git checkout -b feature/YourFeature
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/YourFeature
    ```
5. Open a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


## Contact

For any inquiries or issues, please contact:

- Name: Mehar
- Email: me.umar0027@gmail.com
- GitHub: [mE-uMAr](https://github.com/mE-uMAr)
