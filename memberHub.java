import java.util.ArrayList;

class Person {
    private int id;
    private String name;
    private String contactInformation;

    public Person(int id, String name, String contactInformation) {
        this.id = id;
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

    public void updateDetails(String newName, String newContactInformation) {
        this.name = newName;
        this.contactInformation = newContactInformation;
        System.out.println("Details updated for person with ID " + id);
    }

    public void deletePerson() {
        System.out.println("Person with ID " + id + " deleted.");
    }
}

class WorkTime {
    private int id;
    private int personId;
    private String date;
    private String inTime;
    private String outTime;
    private double overtime;

    public WorkTime(int id, int personId, String date, String inTime, String outTime, double overtime) {
        this.id = id;
        this.personId = personId;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
        this.overtime = overtime;
    }

    public void logWorkTime() {
        System.out.println("Work time logged for person with ID " + personId + " on " + date);
    }

    public void generateReport() {
        System.out.println("Report generated for work time on " + date);
    }
}
