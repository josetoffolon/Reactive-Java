package main.java.edu.itmo.reactive.lab01.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MedicalAppointment{

    public MedicalAppointment(long id2, String reason2, double price2, LocalDate date, LocalTime time,
            AppointmentStatus status2, AppointmentDetails details2, List<String> symptoms2, Patient patient2,
            Doctor doctor2) {
    }
    
    private long id;
    private String reason;
    private double price;
    private LocalDate appointmentDate;
    private LocalTime appointmenTime;
    private AppointmentStatus status;
    private AppointmentDetails details;
    private List<String> symptoms;
    private Patient patient;
    private Doctor doctor;

}