package main.java.edu.itmo.reactive.lab01.generator;

import java.util.Random;

import main.java.edu.itmo.reactive.lab01.model.Patient;

public class PatientGenerator {

    private final Random random = new Random();
    String[] firstNames = {"Ana", "Maria", "Jose", "Pedro", "Angel", "Karla", "David", "Sonia", "Vladimir", "Zoya"};
    String[] lastNames = {"Toffolon", "Perez", "Rodriguez", "Sanchez", "Cano", "Chimaev", "Tesla", "Baldor", "Melendez", "Barragan"};

    public Patient createRandomPatients(long id) {

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        int age = random.nextInt(15,65+1);

        return new Patient(id, firstName, lastName, age);
    }
}
