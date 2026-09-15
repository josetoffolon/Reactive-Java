package main.java.edu.itmo.reactive.lab01;

import java.util.List;

import main.java.edu.itmo.reactive.lab01.generator.MedicalAppointmentGenerator;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;
import main.java.edu.itmo.reactive.lab01.statistics.AppointmentStatistics;
import main.java.edu.itmo.reactive.lab01.statistics.AppointmentStatisticsCalculator;

public class Main {

    public static void main(String[] args) {

        MedicalAppointmentGenerator generator = new MedicalAppointmentGenerator();
        List<MedicalAppointment> appointments = generator.generateAppointments(5000);

        AppointmentStatisticsCalculator calculator = new AppointmentStatisticsCalculator();

        long startTimeLoop = System.nanoTime();
        AppointmentStatistics loopStatistics = calculator.calculateWithLoop(appointments);
        long endTimeLoop = System.nanoTime();
        long elapsedTimeLoop = endTimeLoop - startTimeLoop;

        long startTimeStream = System.nanoTime();
        AppointmentStatistics streamStatistics = calculator.calculateWithStream(appointments);
        long endTimeStream = System.nanoTime();
        long elapsedTimeStream = endTimeStream - startTimeStream;

        long startTimeCustom = System.nanoTime();
        AppointmentStatistics customStatistics = calculator.calculateWithCollector(appointments);
        long endTimeCustom = System.nanoTime();
        long elapsedTimeCustom = endTimeCustom - startTimeCustom;

        System.out.println("Appointments Genereted: " + appointments.size());
/* 
        for(MedicalAppointment appointment: appointments){
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("ID: " + appointment.getId());
            System.out.println("Reason: " + appointment.getReason());
            System.out.println("Price: " + appointment.getPrice());
            System.out.println("Date: " + appointment.getAppointmentDate());
            System.out.println("Time: "+ appointment.getAppointmentTime());
            System.out.println("Status: " + appointment.getStatus());
            System.out.println("Details: " + appointment.getDetails());
            System.out.println("Symptoms: " + appointment.getSymptoms());
            System.out.println("Patient: " + appointment.getPatient());
            System.out.println("Doctor: " + appointment.getDoctor());
        }*/

        System.out.println("loopStatistics: " + loopStatistics);
        System.out.println("loopStatistics elapsedTime: " + elapsedTimeLoop);

        System.out.println("streamStatistics: " + streamStatistics);
        System.out.println("streamStatistics elapsedTimeStream: " + elapsedTimeStream);

        System.out.println("customStatistics: " + customStatistics);
        System.out.println("customStatistics elapsedTimeCustom: " + elapsedTimeCustom);
    }
}