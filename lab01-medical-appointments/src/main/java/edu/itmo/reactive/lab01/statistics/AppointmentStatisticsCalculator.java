 package main.java.edu.itmo.reactive.lab01.statistics;

import java.util.List;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsCalculator {

    public AppointmentStatistics calculatedWithLoop(List<MedicalAppointment> appointments){

        long count = 0;
        long completed = 0;
        long cancelled = 0;
        long scheduled = 0;
        long noShow = 0;

        for(MedicalAppointment appointment: appointments){
            count++;
            switch (appointment.getStatus()) {
                case COMPLETED: completed++;
                case CANCELLED: cancelled++;
                case SCHEDULED: scheduled++;
                case NO_SHOW: noShow++;
            }
        }

        return new AppointmentStatistics(
            count,
            completed,
            cancelled,
            scheduled,
            noShow
        );
    }
    
}