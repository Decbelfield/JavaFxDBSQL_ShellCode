/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafx_db_example.db;

import com.mycompany.javafx_db_example.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>This connects the program to the database as a way to store the entered information
 * in which it will save the Person in the database which will then be able to be accessed
 * later by the program for display <p/>
 * @author Declan Belfield
 */
public class ConnDbOps {


    final String MYSQL_SERVER_URL = "jdbc:mysql://csc311server2001.mariadb.database.azure.com/";
    final String DB_URL = "jdbc:mysql://csc311server2001.mariadb.database.azure.com/vbvbvbvb";
    final String USERNAME = "decbelfield@csc311server2001";
    final String PASSWORD = "D00d!e62";

    /**
     * This code connects the project to the database and then tests if the
     * user is a Registered user if not it will update the information
     * they have entered and Register them as a user.
     * @return hasRegisteredUsers
     */
    public  boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS vbvbvbvb");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "phone VARCHAR(200),"
                    + "address VARCHAR(200),"
                    + "password VARCHAR(200) NOT NULL,"
                    + "salary INT()"
                    + ")";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }

    /**
     * <p>This will search the database for the information tied to the entered
     * name in which it will pull up the name,email,phone,address,password,and salary<p/>
     * @param name
     */
    public  void queryUserByName(String name) {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int salary = resultSet.getInt("Salary");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address + ", Salary " + salary );
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This lists all Registered users that are within the database and
     * displays their information
     */
    public  void listAllUsers() {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int salary = resultSet.getInt("salary");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This allows for a new user to be Registered within the database and to have their
     * information that they entered to be saved
     * @param person
     */
    public void insertUser(Person person) {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO users (name, email, phone, address, password,salary) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setString(5, person.getPassword());
            preparedStatement.setInt(6, person.getSalary());

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(String name, String email, String phone, String address, String password, int salary) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE users SET email=?, phone=?, address=?, password=?, salary=? WHERE name=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, salary);
            preparedStatement.setString(6, name);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            }
            else
            {
                System.out.println("No user found");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
