import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company {

    String name;
    String address;

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    private static Connection connection;

    public void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/Company";
            String user = "root", password = "admin";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromDatabase() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * to generate random number for employee id
     * 
     * @return empid
     */
    private static int generateRandomEmpId() {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000);
    }

    /**
     * to add employees to the database
     * 
     * @param empid2
     * 
     * @param name
     * @param salary
     * @param designation
     * @param phone
     */
    public void addEmployee(String name, double salary, String designation, long phone) {
        int empid = generateRandomEmpId();
        String insertQuery = "insert into employee values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, empid);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setString(4, designation);
            preparedStatement.setLong(5, phone);

            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Employee added successfully");
            } else {
                System.err.println("Failed to add employee!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * to count the number of employees present in the database
     */
    public void countEmp() {
        String countQuery = "select count(*) from employee";
        try {
            PreparedStatement preparedStatemnt = connection.prepareStatement(countQuery);
            ResultSet resultSet = preparedStatemnt.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    /**
     * to search employee based on salary
     * 
     * @param salary
     */
    public void searchEmpBasedOnSal(double salary) {
        String salQuery = "select * from employee where salary=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(salQuery);
            preparedStatement.setDouble(1, salary);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * to search employee based on ID
     * 
     * @param id
     */
    public void searchEmpBasedOnId(int id) {
        String idQuery = "select * from employee where empid=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(idQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));
            } else {
                System.err.println("No employee found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * to search employee based on Designation
     * 
     * @param designation
     */
    public void searchEmpBasedOnDesignation(String designation) {
        String desgQuery = "select * from employee where designation=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(desgQuery);
            preparedStatement.setString(1, designation);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));
            } else {
                System.err.println("No employee found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * to remove employee by employee id
     * 
     * @param id
     */
    public void removeEmpByEid(int id) {
        String deleteQuery = "delete from employee where empid=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Record Deleted");

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    /**
     * to update employee details based on id
     * 
     * @param name
     * @param salary
     * @param designation
     * @param phone
     * @param id
     */
    public void updateEmpById(String name, double salary, String designation, long phone, int id) {
        String updateQuery = "update employee set name=?, salary=?, designation=?, phone=? where empid=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, salary);
            preparedStatement.setString(3, designation);
            preparedStatement.setLong(4, phone);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated Record Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * to display all details
     */
    public List<Employee> allDetails() {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM employee";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int empid = resultSet.getInt("empid");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String designation = resultSet.getString("designation");
                long phone = resultSet.getLong("phone");

                employees.add(new Employee(empid, name, salary, designation, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * to get details of employee based on salary (descending order)
     */
    public void getEmpBySalDesc() {
        String query = "select * from employee order by salary desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * to search employee based on Designation
     */
    public void getEmpByDesignation() {
        String query = "select * from employee order by designation";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * to get employee name and salary (ascending order);
     */
    public void getEmpOrderBySalaryandEname() {
        String query = "select * from employee order by salary,name";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4) + "\t" + resultSet.getLong(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
