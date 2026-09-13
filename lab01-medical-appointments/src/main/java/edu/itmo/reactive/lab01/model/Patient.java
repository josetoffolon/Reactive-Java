package main.java.edu.itmo.reactive.lab01.model;

public class Patient {

    private long id;
    private String name;
    private int age;

    public Patient(long id2, String name2, int age2) {
        this.id = id2;
        this.name = name2;
        this.age = age2;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", age=" + age +"]";
    }

}
