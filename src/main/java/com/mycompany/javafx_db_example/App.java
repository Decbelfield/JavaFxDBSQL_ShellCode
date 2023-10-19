package com.mycompany.javafx_db_example;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.scene.paint.Color;

/**
 *
 *
 *
 * @author Declan Belfield
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.nextLine().charAt(0);

            switch (input) {
                case 'g':
                     launch(args); //GUI
                    break;

                case 'c':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;

                case 'i':
                    Person p;
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scan.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scan.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scan.nextLine();
                    System.out.println("Enter Salary");
                    int salary = scan.nextInt();
                    p = new Person(name,email,phone,address,password,salary);
                    cdbop.insertUser(p); //Your insertUser method
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName); //Your queryUserByName method
                    break;
                case 'b':
                    System.out.print("Enter the name of the user to update: ");
                    String userNameUpdated = scan.nextLine();
                    System.out.print("Enter new Email: ");
                    String newEmail = scan.nextLine();
                    System.out.print("Enter new Phone: ");
                    String newPhone = scan.nextLine();
                    System.out.print("Enter new Address: ");
                    String newAddress = scan.nextLine();
                    System.out.print("Enter new Password: ");
                    String newPassword = scan.nextLine();
                    System.out.print("Enter new Salary: ");
                    int newSalary = scan.nextInt();
                    Person updatedP = new Person(userNameUpdated, newEmail, newPhone, newAddress, newPassword, newSalary);

                    // Call the updateUser method to update the user's information
                    cdbop.updateUser(userNameUpdated, newEmail, newPhone, newAddress, newPassword, newSalary);
                    break;

            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();

       
    }

}
