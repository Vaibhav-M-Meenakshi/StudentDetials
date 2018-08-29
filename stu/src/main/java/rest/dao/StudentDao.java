package rest.dao;

import org.springframework.stereotype.Repository;
import rest.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {

    public static final String DBName = "learnSpring";

    public static final String tablename = "Student";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String id = "id";

    public List<Student> getAllStudent() {
        try (Connection conn = getConnection()) {
            PreparedStatement getstatement = conn.prepareStatement("SELECT * FROM " + tablename);
            try (ResultSet resultSet = getstatement.executeQuery()) {
                List<Student> studentList = new ArrayList<>();
                while (resultSet.next()) {
                    studentList.add(new Student(resultSet.getString(firstName), resultSet.getString(lastName)));
                }
                return studentList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent(String fName,String lName){
        try(Connection conn = getConnection()) {
            PreparedStatement insertStudent = conn.prepareStatement("insert into " + tablename + " ("+ firstName + "," + lastName + ") values(?,?)");
            insertStudent.setString(1,fName);
            insertStudent.setString(2,lName);
            insertStudent.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        loadDriverClass();
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBName, "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDriverClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

