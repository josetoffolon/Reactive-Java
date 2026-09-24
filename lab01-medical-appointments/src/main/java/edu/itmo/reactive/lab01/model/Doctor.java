package main.java.edu.itmo.reactive.lab01.model;

public class Doctor {

    private long id;
    private String firstName;
    private String lastName;
    private String speciality;

    public Doctor(long id2, String firstName2, String lastName2, String speciality2) {
        this.id = id2;
        this.firstName = firstName2;
        this.lastName = lastName2;
        this.speciality = speciality2;
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", speciality=" + speciality + "]";
    }
}
