import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Abstrakt klass som representerar en anställd medlem
abstract class EmployeeMember {
    private static int nextId = 1;

    protected int id;
    protected String name;
    protected String contactInformation;

    // Konstruktor för att skapa en ny anställd medlem
    public EmployeeMember(String name, String contactInformation) {
        this.id = nextId++;
        this.name = name;
        this.contactInformation = contactInformation;
    }

    // Getter-metod för att hämta ID
    public int getId() {
        return id;
    }

    // Getter-metod för att hämta namn
    public String getName() {
        return name;
    }

    // Getter-metod för att hämta kontaktinformation
    public String getContactInformation() {
        return contactInformation;
    }

    // Abstrakta metoder för att visa information, uppdatera detaljer, ta bort anställd medlem,
    // logga arbetstid och generera rapporter
    public abstract void viewInformation();

    public abstract void updateDetails();

    public abstract void deleteEmployeeMember(int id);

    public abstract void logWorkTime(Scanner scanner);

    public abstract void generateReports();
}

// Underklass som representerar en anställd
class Employee extends EmployeeMember {
    // Konstruktor för att skapa en ny anställd
    public Employee(String name, String contactInformation) {
        super(name, contactInformation);
    }

    // Överskuggad metod för att visa information för en anställd
    @Override
    public void viewInformation() {
        System.out.println("Viewing information for employee with ID " + getId());
    }

    // Överskuggad metod för att uppdatera detaljer för en anställd
    @Override
    public void updateDetails() {
        System.out.println("Updating details for employee with ID " + getId());
    }

    // Överskuggad metod för att ta bort en anställd medlem
    @Override
    public void deleteEmployeeMember(int id) {
        System.out.println("Deleting employee with ID " + id);
    }

    // Överskuggad metod för att logga arbetstid för en anställd
    @Override
    public void logWorkTime(Scanner scanner) {
        System.out.print("Enter work start time: ");
        String startTime = scanner.nextLine();

        System.out.print("Enter work end time: ");
        String endTime = scanner.nextLine();

        System.out.println("Work time logged for employee with ID " + getId() +
                " from " + startTime + " to " + endTime);
    }

    // Överskuggad metod för att generera rapporter för en anställd
    @Override
    public void generateReports() {
        System.out.println("Generating reports for employee with ID " + getId());
    }
}

// Underklass som representerar en administratör
class Admin extends EmployeeMember {
    // Konstruktor för att skapa en ny administratör
    public Admin(String name, String contactInformation) {
        super(name, contactInformation);
    }

    // Överskuggad metod för att visa information för en administratör
    @Override
    public void viewInformation() {
        System.out.println("Viewing information for admin with ID " + getId());
    }

    // Överskuggad metod för att uppdatera detaljer för en administratör
    @Override
    public void updateDetails() {
        System.out.println("Updating details for admin with ID " + getId());
    }

    // Överskuggad metod för att ta bort en anställd medlem
    @Override
    public void deleteEmployeeMember(int id) {
        System.out.println("Deleting employee/member with ID " + id);
    }

    // Överskuggad metod för att generera rapporter för en administratör
    @Override
    public void logWorkTime(Scanner scanner) {
        System.out.println("Admins do not log work time.");
    }

     // Överskuggad metod för att generera rapporter för en administratör
    @Override
    public void generateReports() {
        System.out.println("Generating reports for admin with ID " + getId());
    }

    // Metod för att konfigurera systemparametrar
    public void configureSystemParameters() {
        System.out.println("Configuring system parameters...");
    }

    // Metod för att arkivera inaktiva anställda medlemmar
    public void archiveInactiveEmployeeMember() {
        System.out.println("Archiving inactive employees/members...");
    }

    // Metod för att hantera anställda medlemmar
    public void manageEmployeeMembers(Map<String, EmployeeMember> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Managing employee members...");
        for (EmployeeMember user : users.values()) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName());
        }

        System.out.print("Enter the ID to delete: ");
        int idToDelete = scanner.nextInt();
        deleteEmployeeMember(idToDelete);
    }

    // Metod för att hantera arbetstid
    public void manageWorkTime() {
        System.out.println("Managing work time...");
    }

    // Metod för att hantera löner
    public void manageSalaries() {
        System.out.println("Managing salaries...");
    }

    // Metod för att hantera avdelningar
    public void manageDepartments() {
        System.out.println("Managing departments...");
    }

    // Metod för att generera admin-rapporter
    public void generateAdminReports() {
        System.out.println("Generating admin reports...");
    }
}

// Klass för autentisering och auktorisering
class AuthenticationAuthorization {
    private static Map<String, EmployeeMember> users = new HashMap<>();

    static {
        // Lägg till en administratörsanvändare i början
        users.put("Eris", new Admin("Eris", "eris@email.com"));
    }

     // Metod för att autentisera användare
    public static EmployeeMember authenticate(String username, String password) {
        EmployeeMember user = users.get(username);

        if (user != null) {
            System.out.println("User authenticated successfully");
            return user;
        } else {
            System.out.println("Creating a new employee: " + username);
            Employee newEmployee = new Employee(username, username + "@email.com");
            users.put(username, newEmployee);
            return newEmployee;
        }
    }

    // Metod för att auktorisera användare
    public static void authorize(EmployeeMember user) {

        System.out.println("Authorization successful");
    }

    // Metod för att hämta användare
    public static Map<String, EmployeeMember> getUsers() {
        return users;
    }
}

public class memberHub {
    private static EmployeeMember currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            currentUser = AuthenticationAuthorization.authenticate(username, password);
            AuthenticationAuthorization.authorize(currentUser);

            System.out.println("Welcome to the Member Hub, " + currentUser.getName() + "!");

             if (currentUser instanceof Admin) {
                // Visa Admin-menyn
                displayAdminMenu();
            } else {
                // Visa Anställd/Medlems-menyn
                displayEmployeeMemberMenu();
            }

            System.out.print("Do you want to log in with another account? (1 for yes, 0 for no): ");
            choice = scanner.nextInt();
            scanner.nextLine();

        } while (choice == 1);

        System.out.println("Logging out...");
        scanner.close();
    }

    // Metod för att visa Admin-menyn
    private static void displayAdminMenu() {
        Admin admin = (Admin) currentUser;

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Information");
            System.out.println("2. Update Details");
            System.out.println("3. Delete Employee/Member");
            System.out.println("4. Manage Employee/Members");
            System.out.println("5. Manage Work Time");
            System.out.println("6. Manage Salaries");
            System.out.println("7. Manage Departments");
            System.out.println("8. Generate Admin Reports");
            System.out.println("9. System Configuration");
            System.out.println("10. Archiving");
            System.out.println("11. Log out");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewInformation();
                    break;
                case 2:
                    admin.updateDetails();
                    break;
                case 3:
                    System.out.print("Enter the ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    admin.deleteEmployeeMember(idToDelete);
                    break;
                case 4:
                    admin.manageEmployeeMembers(AuthenticationAuthorization.getUsers());
                    break;
                case 5:
                    admin.manageWorkTime();
                    break;
                case 6:
                    admin.manageSalaries();
                    break;
                case 7:
                    admin.manageDepartments();
                    break;
                case 8:
                    admin.generateAdminReports();
                    break;
                case 9:
                    admin.configureSystemParameters();
                    break;
                case 10:
                    admin.archiveInactiveEmployeeMember();
                    break;
                case 11:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Metod för att visa Anställd/Medlems-menyn
    private static void displayEmployeeMemberMenu() {
        while (true) {
            System.out.println("\nEmployee/Member Menu:");
            System.out.println("1. View Information");
            System.out.println("2. Update Details");
            System.out.println("3. Log Work Time");
            System.out.println("4. Generate Reports");
            System.out.println("5. Log out");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    currentUser.viewInformation();
                    break;
                case 2:
                    currentUser.updateDetails();
                    break;
                case 3:
                    currentUser.logWorkTime(scanner);
                    break;
                case 4:
                    currentUser.generateReports();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
