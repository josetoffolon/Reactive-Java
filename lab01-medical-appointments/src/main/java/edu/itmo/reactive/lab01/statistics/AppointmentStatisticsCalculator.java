 package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.Duration;
import java.time.LocalTime;
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
        double totalPrice = 0.0;
        double averagePrice = 0.0;
        double minPrice = 0.0;
        double maxPrice = 0.0;
        Duration totalDuration = Duration.ZERO;
        Duration averageDuration = Duration.ZERO;
        LocalTime minDuration;
        LocalTime maxDuration;
        long firstVisitCount = 0;
        long totalSymptoms = 0;
        long averageSymptoms = 0;
        long morningApppointments = 0;
        long afternoomAppointments = 0;

        double minPriceContol = 0.0;
        double maxPriceContol = 0.0;

        for(MedicalAppointment appointment: appointments){
            count++;
            totalPrice = totalPrice + appointment.getPrice();
            minPriceContol = appointment.getPrice();
            maxPriceContol = appointment.getPrice();
            
            if (appointment.getPrice() < minPriceContol){
                minPrice = appointment.getPrice();
            }

            if (appointment.getPrice() > maxPriceContol){
                maxPrice = appointment.getPrice();
            }

            if (appointment.getDetails().firstVisit()){
                firstVisitCount++;
            }

           LocalTime times = appointment.getAppointmentTime();
           Duration acuTime = Duration.ofNanos(times.toNanoOfDay());
           totalDuration = totalDuration.plus(acuTime);

           averageDuration = totalDuration.dividedBy(count);

           if (times.isBefore(appointment.getAppointmentTime()) ){
                minDuration = appointment.getAppointmentTime();
           }

           if (times.isAfter(appointment.getAppointmentTime()) ){
                minDuration = appointment.getAppointmentTime();
           }


            switch (appointment.getStatus()) {
                case COMPLETED -> completed++;
                case CANCELLED -> cancelled++;
                case SCHEDULED -> scheduled++;
                case NO_SHOW -> noShow++;
            }
        }

        averagePrice = totalPrice/count;

        return new AppointmentStatistics(
            count,
            completed,
            cancelled,
            scheduled,
            noShow,
            totalPrice,
            averagePrice,
            minPrice,
            maxPrice,
            totalDuration,
            averageDuration,
            minDuration,
            maxDuration,
            firstVisitCount,
            totalSymptoms,
            averageSymptoms,
            morningApppointments,
            afternoomAppointments
        );
    }

    public AppointmentStatistics calculateWithStream(List<MedicalAppointment> appointments){

        long count = 0;
        long completed = 0;
        long cancelled = 0;
        long scheduled = 0;
        long noShow = 0;
        double totalPrice = 0.0;
        double averagePrice = 0.0;
        double minPrice = 0.0;
        double maxPrice = 0.0;
        long totalDuration = 0;
        long averageDuration = 0;
        long minDuration = 0;
        long maxDuration = 0;
        long firstVisitCount = 0;
        long totalSymptoms = 0;
        long averageSymptoms = 0;
        long morningApppointments = 0;
        long afternoomAppointments = 0;

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
            noShow,
            totalPrice,
            averagePrice,
            minPrice,
            maxPrice,
            totalDuration,
            averageDuration,
            minDuration,
            maxDuration,
            firstVisitCount,
            totalSymptoms,
            averageSymptoms,
            morningApppointments,
            afternoomAppointments
        );
    }

    public AppointmentStatistics calculateWithCollector(List<MedicalAppointment> appointments){
        return appointments.stream().collect(new AppointmentStatisticsCollector());
    }
    
}