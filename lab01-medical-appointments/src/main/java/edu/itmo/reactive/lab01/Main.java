package main.java.edu.itmo.reactive.lab01;

import java.util.List;

import main.java.edu.itmo.reactive.lab01.generator.MedicalAppointmentGenerator;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;
import main.java.edu.itmo.reactive.lab01.statistics.AppointmentStatistics;
import main.java.edu.itmo.reactive.lab01.statistics.AppointmentStatisticsCalculator;

public class Main {

    public static void main(String[] args) {

        int[] sizes = {5000, 50000, 250000};

        MedicalAppointmentGenerator generator = new MedicalAppointmentGenerator();

        for (int size: sizes){
            List<MedicalAppointment> appointments = generator.generateAppointments(size);
            AppointmentStatisticsCalculator calculator = new AppointmentStatisticsCalculator();

            long startTimeLoop = System.nanoTime();
            AppointmentStatistics loopStatistics = calculator.calculateWithLoop(appointments);
            long endTimeLoop = System.nanoTime();
            double elapsedTimeLoop = (endTimeLoop - startTimeLoop)/1000000.0;

            long startTimeStream = System.nanoTime();
            AppointmentStatistics streamStatistics = calculator.calculateWithStream(appointments);
            long endTimeStream = System.nanoTime();
            double elapsedTimeStream = (endTimeStream - startTimeStream)/1000000.0;

            long startTimeCustom = System.nanoTime();
            AppointmentStatistics customStatistics = calculator.calculateWithCollector(appointments);
            long endTimeCustom = System.nanoTime();
            double elapsedTimeCustom = (endTimeCustom - startTimeCustom)/1000000.0;

            /*if (!loopStatistics.equals(streamStatistics) || !loopStatistics.equals(customStatistics)) {
                System.err.println("¡MISMATCH entre implementaciones!");
            }

            if (!loopStatistics.equals(streamStatistics)) {
                System.out.println("MISMATCH loop vs stream");
                System.out.println("  totalCount:    " + loopStatistics.totalCount()
                                + " vs " + streamStatistics.totalCount());
                System.out.println("  countByStatus: " + loopStatistics.countByStatus().equals(streamStatistics.countByStatus()));
                System.out.println("  countBySymptom:" + loopStatistics.countBySymtoms().equals(streamStatistics.countBySymtoms()));
                System.out.println("  countByDoctor: " + loopStatistics.countByDoctor().equals(streamStatistics.countByDoctor())
                                + " (sizes " + loopStatistics.countByDoctor().size()
                                + " / " + streamStatistics.countByDoctor().size() + ")");
                System.out.println("  totalPriceByMonth: " + loopStatistics.totalPriceByMonth().equals(streamStatistics.totalPriceByMonth()));
            }*/

            System.out.println("\n=====================================================");
            System.out.println("Appointments Genereted: " + appointments.size());
            System.out.println("=====================================================");
            System.out.println("Loop:");
            //System.out.println("loopStatistics: " + loopStatistics);
            System.out.println("loopStatistics elapsedTime: " + elapsedTimeLoop +" ms");
            System.out.println("\n");

            System.out.println("Stream:");
            //System.out.println("streamStatistics: " + streamStatistics);
            System.out.println("streamStatistics elapsedTimeStream: " + elapsedTimeStream+" ms");
            System.out.println("\n");

            System.out.println("Custom:");
            //System.out.println("customStatistics: " + customStatistics);
            System.out.println("customStatistics elapsedTimeCustom: " + elapsedTimeCustom+" ms");
        }
        

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

    }
}