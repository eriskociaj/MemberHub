import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class EmployeeMember {
    private static int nextId = 1;

    protected int id;
    protected String name;
    protected String contactInformation;

    public EmployeeMember(String name, String contactInformation) {
        this.id = nextId++;
        this.name = name;
        this.contactInformation = contactInformation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public abstract void viewInformation();

    public abstract void updateDetails();

    public abstract void deleteEmployeeMember(int id);

    public abstract void logWorkTime(Scanner scanner);

    public abstract void generateReports();
}

class Employee extends EmployeeMember {
    public Employee(String name, String contactInformation) {
        super(name, contactInformation);
    }

    @Override
    public void viewInformation() {
        System.out.println("Viewing information for employee with ID " + getId());
    }

    @Override
    public void updateDetails() {
        System.out.println("Updating details for employee with ID " + getId());
    }

    @Override
    public void deleteEmployeeMember(int id) {
        System.out.println("Deleting employee with ID " + id);
    }

    @Override
    public void logWorkTime(Scanner scanner) {
        System.out.print("Enter work start time: ");
        String startTime = scanner.nextLine();

        System.out.print("Enter work end time: ");
        String endTime = scanner.nextLine();

        System.out.println("Work time logged for employee with ID " + getId() +
                " from " + startTime + " to " + endTime);
    }

    @Override
    public void generateReports() {
        System.out.println("Generating reports for employee with ID " + getId());
    }
}

class Admin extends EmployeeMember {
    public Admin(String name, String contactInformation) {
        super(name, contactInformation);
    }

    @Override
    public void viewInformation() {
        System.out.println("Viewing information for admin with ID " + getId());
    }

    @Override
    public void updateDetails() {
        System.out.println("Updating details for admin with ID " + getId());
    }

    @Override
    public void deleteEmployeeMember(int id) {
        System.out.println("Deleting employee/member with ID " + id);
    }

    @Override
    public void logWorkTime(Scanner scanner) {
        System.out.println("Admins do not log work time.");
    }

    @Override
    public void generateReports() {
        System.out.println("Generating reports for admin with ID " + getId());
    }

    public void configureSystemParameters() {
        System.out.println("Configuring system parameters...");
    }

    public void archiveInactiveEmployeeMember() {
        System.out.println("Archiving inactive employees/members...");
    }

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

    public void manageWorkTime() {
        System.out.println("Managing work time...");
    }

    public void manageSalaries() {
        System.out.println("Managing salaries...");
    }

    public void manageDepartments() {
        System.out.println("Managing departments...");
    }

    public void generateAdminReports() {
        System.out.println("Generating admin reports...");
    }
}

class AuthenticationAuthorization {
    private static Map<String, EmployeeMember> users = new HashMap<>();

    static {
        // Add an admin user at the start
        users.put("Eris", new Admin("Eris", "eris@email.com"));
    }

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

    public static void authorize(EmployeeMember user) {
        // Implement authorization logic
        System.out.println("Authorization successful");
    }

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
                // Show Admin menu
                displayAdminMenu();
            } else {
                // Show Employee/Member menu
                displayEmployeeMemberMenu();
            }

            System.out.print("Do you want to log in with another account? (1 for yes, 0 for no): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

        } while (choice == 1);

        System.out.println("Logging out...");
        scanner.close();
    }

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
            scanner.nextLine();  // Consume the newline character

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
            scanner.nextLine();  // Consume the newline character

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
