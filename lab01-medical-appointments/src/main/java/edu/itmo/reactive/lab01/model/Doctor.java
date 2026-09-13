package main.java.edu.itmo.reactive.lab01.model;

public class Doctor {

    private long id;
    private String name;
    private String speciality;

    public Doctor(long id2, String name2, String speciality2) {
        this.id = id2;
        this.name = name2;
        this.speciality = speciality2;
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + ", speciality=" + speciality + "]";
    }
}
