package main.java.edu.itmo.reactive.lab01.generator;

import java.util.Random;

import main.java.edu.itmo.reactive.lab01.model.Patient;

public class PatientGenerator {
    private final Random random = new Random();
    String[] names = {"Ana", "Maria", "Jose", "Pedro"};

    public Patient createRandomPatients(long id) {

        String name = names[random.nextInt(names.length)];

        int age = random.nextInt(15,65+1);

        return new Patient(id, name, age);
    }
}
