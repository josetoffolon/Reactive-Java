 package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.LocalTime;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
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
        long minDuration = Long.MAX_VALUE;
        long maxDuration = Long.MIN_VALUE;
        long firstVisitCount = 0;
        long totalSymptoms = 0;
        double averageSymptoms = 0;
        long morningApppointments = 0;
        long afternoomAppointments = 0;

        for(MedicalAppointment appointment: appointments){
            count++;

            double price = appointment.getPrice();
            totalPrice += price;
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

        IntSummaryStatistics durationStatistics = 
            appointments.stream().collect(
                Collectors.summarizingInt(
                    appointment -> appointment.getDetails().durationMinutes()));
        
        long totalDuration = durationStatistics.getSum();
        double averageDuration = durationStatistics.getAverage();
        int minDuration = durationStatistics.getMin();
        int maxDuration = durationStatistics.getMax();

        long firstVisitCount = 
            appointments.stream().filter(
                appointment -> appointment.getDetails().firstVisit()).count();
        
        long totalSymptoms = 
            appointments.stream().mapToLong(
                appointment -> appointment.getSymptoms().size()).sum();

        double averageSymptoms = appointments.stream().mapToInt(
            appointment -> appointment.getSymptoms().size()).average().orElse(0.0);

        long morningApppointments = appointments.stream().filter(
            appointment -> appointment.getAppointmentTime().isBefore(LocalTime.NOON)).count();

        long afternoomAppointments = appointments.stream().filter(
            appointment -> !appointment.getAppointmentTime().isBefore(LocalTime.NOON)).count();


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