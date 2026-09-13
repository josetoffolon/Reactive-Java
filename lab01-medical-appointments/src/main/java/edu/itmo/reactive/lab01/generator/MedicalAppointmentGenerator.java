package main.java.edu.itmo.reactive.lab01.generator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.java.edu.itmo.reactive.lab01.model.AppointmentDetails;
import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.Doctor;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;
import main.java.edu.itmo.reactive.lab01.model.Patient;

public class MedicalAppointmentGenerator {
    private final Random random = new Random();
    PatientGenerator patientGenerator = new PatientGenerator();
    DoctorGenerator doctorGenerator = new DoctorGenerator();

    public List<MedicalAppointment> generateAppointments(int count){
        List<MedicalAppointment> appointments = new ArrayList<>();
        for(long i=1; i<=count; i++){
            MedicalAppointment appointment = createRandomAppointment(i);
            appointments.add(appointment);
        }
        return appointments;
    }

    private MedicalAppointment createRandomAppointment(long id) {
        String[] reasons = {
            "General consultation",
            "Annual checkup",
            "Headache",
            "Follow-up",
            "Respiratory problem"
        };

        AppointmentStatus[] statuses = AppointmentStatus.values();
        AppointmentStatus status = statuses[random.nextInt(statuses.length)];
        
        String reason= reasons[random.nextInt(reasons.length)];
        double price = 20 + random.nextDouble() * 180;
        LocalDate date = LocalDate.now().plusDays(random.nextInt(365));
        LocalTime time = LocalTime.of(8 + random.nextInt(10),random.nextBoolean()? 0 : 30);
        AppointmentDetails details = new AppointmentDetails("Room " + (1 + random.nextInt(10)), 
                    getRandomDuration(), 
                    random.nextBoolean());
        
        List<String> symptoms = getRandomSymptoms();
        Patient patient = patientGenerator.createRandomPatients(id);
        Doctor doctor = doctorGenerator.createRandomDoctor(id);

        return new MedicalAppointment(
            id,
            reason,
            price,
            date,
            time,
            status,
            details,
            symptoms,
            patient,
            doctor);

    }

    private List<String> getRandomSymptoms() {
        String[] symptoms = {
            "Headache",
            "Fever",
            "Cough",
            "Fatigue",
            "Back Pain"
        };

        int quantity = 1 + random.nextInt(3);
        List<String> result = new ArrayList<>();

        for (int i = 0; i<quantity; i++){
            String symptom = symptoms[random.nextInt(symptoms.length)];
            if(!result.contains(symptom)){
                result.add(symptom);
            }
        }

        return result;
    }

    private int getRandomDuration() {
        int [] durations = {
            15,
            30,
            45,
            60
        };
        return durations[random.nextInt(durations.length)];
    }

}
