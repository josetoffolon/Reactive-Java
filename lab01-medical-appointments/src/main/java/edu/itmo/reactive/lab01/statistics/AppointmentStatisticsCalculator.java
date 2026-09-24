 package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.LocalTime;
import java.util.DoubleSummaryStatistics;
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
        double minPrice = Double.POSITIVE_INFINITY;
        double maxPrice = Double.NEGATIVE_INFINITY;
        long totalDuration = 0;
        double averageDuration = 0.0;
        long minDuration = 0;
        long maxDuration = 0;
        long firstVisitCount = 0;
        long totalSymptoms = 0;
        double averageSymptoms = 0;
        long morningApppointments = 0;
        long afternoomAppointments = 0;

        for(MedicalAppointment appointment: appointments){
            count++;

            double price = appointment.getPrice();
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);

            totalSymptoms +=appointment.getSymptoms().size();

            if (appointment.getDetails().firstVisit()){
                firstVisitCount++;
            }

            int duration = appointment.getDetails().durationMinutes();
            totalDuration += duration;
            if (duration < minDuration) {
                minDuration = duration;
            }
            if (duration > maxDuration) {
                maxDuration = duration;
            }

            LocalTime appointmentTime = appointment.getAppointmentTime();
            if (appointmentTime.isBefore(LocalTime.NOON)){
                morningApppointments++;
            } else {
                afternoomAppointments++;
            }

            switch (appointment.getStatus()) {
                case COMPLETED -> completed++;
                case CANCELLED -> cancelled++;
                case SCHEDULED -> scheduled++;
                case NO_SHOW -> noShow++;
            }
        }

        averageSymptoms = (double) totalSymptoms /count;
        averageDuration = (double) totalDuration / count;
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
        
        long count = appointments.size();
        long completed = statusCounts.getOrDefault(AppointmentStatus.COMPLETED, 0L);
        long cancelled = statusCounts.getOrDefault(AppointmentStatus.CANCELLED, 0L);
        long scheduled = statusCounts.getOrDefault(AppointmentStatus.SCHEDULED, 0L);
        long noShow = statusCounts.getOrDefault(AppointmentStatus.NO_SHOW, 0L);

        DoubleSummaryStatistics priceStats =
            appointments.stream().collect(
                Collectors.summarizingDouble(
                    MedicalAppointment::getPrice));

        double averagePrice = priceStats.getAverage();
        double totalPrice = priceStats.getSum();
        double minPrice = priceStats.getMin();
        double maxPrice = priceStats.getMax();

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