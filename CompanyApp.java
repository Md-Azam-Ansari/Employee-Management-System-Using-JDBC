import java.util.List;
import java.util.Scanner;

public class CompanyApp {

    private Company com = new Company("JSpiders", "BTM");
    private Scanner sc = new Scanner(System.in);

    void MenuItems() {
        System.out.println("Welcome to " + com.getName() + " " + com.getAddress() + " Company");
        System.out.println("Options");
        System.out.println("--------------------------------------");
        System.out.println("Select 1 to Add Employee");
        System.out.println("Select 2 to Count Employee");
        System.out.println("Select 3 to Search Employee Based on ID");
        System.out.println("Select 4 to Search Employee Based on Designation");
        System.out.println("Select 5 to remove Employee Based on ID");
        System.out.println("Select 6 to Update Employee By ID");
        System.out.println("Select 7 to Show All Details");
        System.out.println("Select 8 to Employee Details Based on Salary Descending");
        System.out.println("Select 9 to Employee Details Based on Designation");
        System.out.println("Select 10 to Employee Details based on salary and name");
        System.out.println("Select 11 to Exit");
        System.out.println("----------------------------------------W");

    }

    public void launch() {

        com.connectToDatabase();

        while (true) {
            MenuItems();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee Name");
                    String name = sc.next();
                    System.out.println("Enter Employee Salary");
                    double salary = sc.nextDouble();
                    System.out.println("Enter Employee Designation");
                    String designation = sc.next();
                    System.out.println("Enter Employee Phone Num");
                    long phone = sc.nextLong();

                    com.addEmployee(name, salary, designation, phone);
                    System.out.println("Employee Added Successfully");
                    System.out.println();
                    break;

                case 2:
                    com.countEmp();
                    break;

                case 3:
                    System.out.println("Enter Employee ID");
                    int id = sc.nextInt();
                    com.searchEmpBasedOnId(id);
                    break;

                case 4:
                    System.out.println("Enter Employee Designation");
                    String designation1 = sc.next();
                    com.searchEmpBasedOnDesignation(designation1);
                    break;

                case 5:
                    System.out.println("Enter Employee ID");
                    int id1 = sc.nextInt();
                    com.removeEmpByEid(id1);
                    break;

                case 6:
                    System.out.println("Enter Employee ID");
                    int id2 = sc.nextInt();
                    System.out.println("Enter Employee name");
                    String name1 = sc.next();
                    System.out.println("Enter Employee Salary");
                    double salary1 = sc.nextDouble();
                    System.out.println("Enter Employee Designation");
                    String designation2 = sc.next();
                    System.out.println("Enter Employee Phone Number");
                    long phone1 = sc.nextLong();
                    com.updateEmpById(name1, salary1, designation2, phone1, id2);
                    break;

                case 7:
                    // com.allDetails();
                    List<Employee> employees = com.allDetails();
                    for (Employee employee : employees) {
                        System.out.println(employee.getId() + " | " + employee.getName() + " | " +
                                employee.getDesignation() + " | " + employee.getSalary() + " | " + employee.getPhone());
                    }
                    break;

                case 8:
                    com.getEmpBySalDesc();
                    break;

                case 9:
                    com.getEmpByDesignation();
                    break;

                case 10:
                    com.getEmpOrderBySalaryandEname();
                    com.disconnectFromDatabase();
                    break;

                case 11:
                    com.disconnectFromDatabase();
                    System.out.println("Good Bye!!");
                    System.exit(0);
                    break;

                default:
                    MenuItems();
            }
        }
    }
}
