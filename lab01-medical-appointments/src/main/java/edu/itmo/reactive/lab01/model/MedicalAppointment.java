package main.java.edu.itmo.reactive.lab01.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MedicalAppointment{

    public MedicalAppointment(long id2, String reason2, double price2, LocalDate date, LocalTime time,
            AppointmentStatus status2, AppointmentDetails details2, List<String> symptoms2, Patient patient2,
            Doctor doctor2) {
                this.id = id2;
                this.reason = reason2;
                this.price = price2;
                this.appointmentDate = date;
                this.appointmenTime = time;
                this.status = status2;
                this.details = details2;
                this.symptoms = symptoms2;
                this.patient = patient2;
                this.doctor = doctor2;
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

    public long getId() {
        return id;
    }
    public String getReason() {
        return reason;
    }
    public double getPrice() {
        return price;
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    public LocalTime getAppointmenTime() {
        return appointmenTime;
    }
    public AppointmentStatus getStatus() {
        return status;
    }
    public AppointmentDetails getDetails() {
        return details;
    }
    public List<String> getSymptoms() {
        return symptoms;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }

}