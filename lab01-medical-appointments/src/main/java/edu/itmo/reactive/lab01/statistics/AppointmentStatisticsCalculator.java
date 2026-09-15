 package main.java.edu.itmo.reactive.lab01.statistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsCalculator {

    public AppointmentStatistics calculateWithLoop(List<MedicalAppointment> appointments){

        long count = 0;
        long completed = 0;
        long cancelled = 0;
        long scheduled = 0;
        long noShow = 0;

        for(MedicalAppointment appointment: appointments){
            count++;
            switch (appointment.getStatus()) {
                case COMPLETED -> completed++;
                case CANCELLED -> cancelled++;
                case SCHEDULED -> scheduled++;
                case NO_SHOW -> noShow++;
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

    public AppointmentStatistics calculateWithStream(List<MedicalAppointment> appointments){

        long count = 0;
        long completed = 0;
        long cancelled = 0;
        long scheduled = 0;
        long noShow = 0;

        Map<AppointmentStatus, Long> statusCounts =
        appointments.stream().collect(
            Collectors.groupingBy(
                MedicalAppointment::getStatus, Collectors.counting()));
        
        count = appointments.size();
        completed = statusCounts.getOrDefault(AppointmentStatus.COMPLETED, 0L);
        cancelled = statusCounts.getOrDefault(AppointmentStatus.CANCELLED, 0L);
        scheduled = statusCounts.getOrDefault(AppointmentStatus.SCHEDULED, 0L);
        noShow = statusCounts.getOrDefault(AppointmentStatus.NO_SHOW, 0L);

        return new AppointmentStatistics(
            count,
            completed,
            cancelled,
            scheduled,
            noShow
        );
    }

    public AppointmentStatistics calculateWithCollector(List<MedicalAppointment> appointments){
        return appointments.stream().collect(new AppointmentStatisticsCollector());
    }
    
}