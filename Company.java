import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    static Connection con = null;
    static PreparedStatement pst = null;
    static ResultSet res = null;

    static String path = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/Company";
    static String user = "root", password = "admin";

    public void execute() {
        try {
            Class.forName(path);
            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeexecute() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void addEmployee(int id, String name, double salary, String designation, long phone) {
        execute();
        try {
            pst = con.prepareStatement("insert into employee values(?,?,?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setDouble(3, salary);
            pst.setString(4, designation);
            pst.setLong(5, phone);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeexecute();
    }

    public void countEmp() {
        try {
            pst = con.prepareStatement("select count(*) from employee");
            res = pst.executeQuery();

            while (res.next()) {
                System.out.println(res.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeexecute();

    }

    public void searchEmpBasedOnSal(double salary) {
        try {
            pst = con.prepareStatement("select * from employee where salary=?");
            pst.setDouble(0, salary);
            res = pst.executeQuery();

            if (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));
            } else {
                System.err.println("No employee found");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeexecute();
    }

    public void searchEmpBasedOnId(int id) {
        try {
            pst = con.prepareStatement("select * from employee where id=?");
            pst.setInt(1, id);
            res = pst.executeQuery();

            if (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));
            } else {
                System.err.println("No employee found");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void searchEmpBasedOnDesignation(String designation) {
        try {
            pst = con.prepareStatement("select * from employee where designation=?");
            pst.setString(1, designation);
            res = pst.executeQuery();

            if (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));
            } else {
                System.err.println("No employee found");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void removeEmpByEid(int eid) {
        execute();
        try {
            pst = con.prepareStatement("delete from employee where id=?");
            pst.setInt(1, eid);
            pst.executeUpdate();
            System.out.println("Record Deleted");

        } catch (SQLException e) {

            e.printStackTrace();
        }
        closeexecute();

    }

    public void updateEmpById(String name, double salary, String designation, long phone, int id) {
        try {
            pst = con.prepareStatement("update employee set name=?, salary=?, designation=?, phone=? where id=? ");
            pst.setString(1, name);
            pst.setDouble(2, salary);
            pst.setString(3, designation);
            pst.setLong(4, phone);
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Updated Record Successfully");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void allDetails() {
        execute();
        try {
            pst = con.prepareStatement("select * from employee");
            res = pst.executeQuery();

            while (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeexecute();
    }

    public void getEmpBySalDesc() {
        try {
            pst = con.prepareStatement("select * from employee order by salary desc");
            res = pst.executeQuery();

            while (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getEmpByDesignation() {
        try {
            pst = con.prepareStatement("select * from employee order by designation");
            res = pst.executeQuery();

            while (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getEmpOrderBySalaryandEname() {
        try {
            pst = con.prepareStatement("select * from employee order by salary,name");
            res = pst.executeQuery();
            while (res.next()) {
                System.out.println(
                        "EmpId" + "\t" + "Name" + "\t" + "Salary" + "\t" + "Designation" + "\t" + "PhoneNumber");
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
                        + res.getString(4) + "\t" + res.getLong(5));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // void getEmpBySalDesc();
    // void getEmpByDesignation();
    // void getEmpOrderByAgeAndEname(); sort based on age is same based on name
    // Implementation user define exception

}
