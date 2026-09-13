package main.java.edu.itmo.reactive.lab01.generator;

import java.util.Random;

import main.java.edu.itmo.reactive.lab01.model.Doctor;

public class DoctorGenerator {

    private final Random random = new Random();
    String[] names = {"Dr. Jose","Dra. Maria","Dr. Ivan","Dra. Ana"};
    String[] specialities = {"Cardiologia","Medico General","Traumatologia","Neumologia"};

    public Doctor createRandomDoctor(long id){
        
        String name = names[random.nextInt(names.length)];
        String speciality = specialities[random.nextInt(specialities.length)];

        return new Doctor(id, name, speciality);
    }
    
}
