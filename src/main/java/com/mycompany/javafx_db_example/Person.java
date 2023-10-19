package com.mycompany.javafx_db_example;
/**
* <p>This class collects the information for the user that is entered in the field,
* this will collect the name,email,phone,address,password,and salary of the user and
* store this info in the database connected<p/>
 * */
public class Person {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private int salary;

    public Person() {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.password = "";
        this.salary = 0;

    }

    public Person(String name, String email, String phone, String address, String password, int salary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.salary = salary;
    }

    /**
     * returns the entered name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the entered email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the entered phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * gets the phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * returns the entered address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets the address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * returns the entered password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns the entered salary
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * gets the salary
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
