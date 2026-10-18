package main.java.edu.itmo.reactive.lab01.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.java.edu.itmo.reactive.lab01.model.Doctor;

public class DoctorGenerator {

    private final Random random = new Random();
    String[] firstNames = {"Dr. Jose", "Dra. Maria", "Dr. Ivan", "Dra. Ana", "Dr. Alvaro", "Dra. Carla", "Dr. Augusto", "Dra. Laura", "Dr. Carlos", "Dra. Antonella"};
    String[] lastNames = {"Messi", "Carrasquilla", "Cordoba", "Crespo", "Valverde", "Torres", "Alonso", "Perez", "Suarez", "Meazza"};
    String[] specialities = {"Cardiologia","Medico General","Traumatologia","Neumologia"};

    public Doctor createRandomDoctor(long id){
        
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String speciality = specialities[random.nextInt(specialities.length)];

        return new Doctor(id, firstName, lastName, speciality);
    }

    public List<Doctor> generate(int count) {
        List<Doctor> result = new ArrayList<>(count);
        for (long i = 1; i <= count; i++) {
            result.add(createRandomDoctor(i));
        }
        return result;
    }
    
}
