package com.mycompany.javafx_db_example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DB_GUI_Controller implements Initializable {

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "2666 street", "CPIS", "CS", 100),
                    new Person("Jacob2", "Smith1", "546 street", "CPIS1", "CS", 120)
            );

    @FXML
    private TextField name, email, phone, address, password, salary;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_name, tv_email, tv_phone, tv_address, tv_password, tv_salary;
    @FXML
    private Button addBtn;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private ImageView img_view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize TableView columns
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tv_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tv_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tv_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tv_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Set data to the TableView
        tv.setItems(data);
    }

    @FXML
    protected void addNewRecord() {
        // Parse salary to an integer
        int salaryValue = Integer.parseInt(salary.getText());

        // Create a new Person object and add it to the data list
        data.add(new Person(name.getText(), email.getText(), phone.getText(), address.getText(), password.getText(), salaryValue));

        // Clear the form
        clearForm();
    }

    @FXML
    protected void clearForm() {
        name.clear();
        email.clear();
        phone.clear();
        address.clear();
        password.clear();
        salary.clear();
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    @FXML
    protected void editRecord() {
        // Get the selected Person
        Person p = tv.getSelectionModel().getSelectedItem();

        // Check if a selection is made
        if (p != null) {
            // Update the selected Person with the form data
            p.setName(name.getText());
            p.setEmail(email.getText());
            p.setPhone(phone.getText());
            p.setAddress(address.getText());
            p.setPassword(password.getText());
            p.setSalary(Integer.parseInt(salary.getText()));

            // Refresh the TableView
            tv.refresh();

            // Clear the form
            clearForm();
        }
    }

    @FXML
    protected void deleteRecord() {
        // Get the selected Person
        Person p = tv.getSelectionModel().getSelectedItem();

        // Check if a selection is made
        if (p != null) {
            // Remove the selected Person from the data list
            data.remove(p);

            // Clear the form
            clearForm();
        }
    }

    @FXML
    protected void showImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"));
        File file = fileChooser.showOpenDialog(img_view.getScene().getWindow());

        if (file != null) {
            img_view.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        // Get the selected Person
        Person p = tv.getSelectionModel().getSelectedItem();

        // Populate the form with the selected Person's data
        name.setText(p.getName());
        email.setText(p.getEmail());
        phone.setText(p.getPhone());
        address.setText(p.getAddress());
        password.setText(p.getPassword());
        salary.setText(String.valueOf(p.getSalary()));
    }
}
