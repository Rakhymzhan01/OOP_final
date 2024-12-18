package university.core;

public abstract class Employee extends User {
    public Employee(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public void sendMessage(Employee to, String message) {
        System.out.println("Message sent to " + to.getName() + ": " + message);
    }

    public void sendComplaint(Manager to, String complaint) {
        System.out.println("Complaint sent to Manager " + to.getName() + ": " + complaint);
    }
}
