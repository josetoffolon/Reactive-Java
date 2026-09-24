package main.java.edu.itmo.reactive.lab01.model;

public class Patient {

    private long id;
    private String firstName;
    private String lastName;
    private int age;

    public Patient(long id2, String firstName2, String lastName2,int age2) {
        this.id = id2;
        this.firstName = firstName2;
        this.lastName = lastName2;
        this.age = age2;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age +"]";
    }

}
