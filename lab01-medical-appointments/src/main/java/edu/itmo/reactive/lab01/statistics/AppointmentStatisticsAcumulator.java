package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.LocalTime;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsAcumulator {

    private long count;
    private long completed;
    private long cancelled;
    private long scheduled;
    private long noShow;
    private double totalPrice;
    private double averagePrice;
    private double minPrice = Double.POSITIVE_INFINITY;
    private double maxPrice = Double.NEGATIVE_INFINITY;
    private long totalDuration;
    private double averageDuration;
    private int minDuration = Integer.MAX_VALUE;
    private int maxDuration = Integer.MIN_VALUE;
    private long firstVisitCount;
    private long totalSymptoms;
    private double averageSymptoms;
    private long morningApppointments;
    private long afternoomAppointments;

    
    public void add(MedicalAppointment appointment){

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

        averageSymptoms = (double) totalSymptoms /count;
        averageDuration = (double) totalDuration / count;
        averagePrice = totalPrice/count;

        switch (appointment.getStatus()){
            case COMPLETED -> completed++;
            case CANCELLED -> cancelled++;
            case SCHEDULED -> scheduled++;
            case NO_SHOW -> noShow++;
        }

    }

    public AppointmentStatisticsAcumulator combine(AppointmentStatisticsAcumulator other){
        this.count += other.count;
        this.completed += other.completed;
        this.cancelled += other.cancelled;
        this.scheduled += other.scheduled;
        this.noShow += other.noShow;
        this.totalPrice += other.totalPrice;
        this.maxPrice += Math.max(this.maxPrice, other.maxPrice);
        this.minPrice += Math.min(this.minPrice, other.minPrice);
        this.totalDuration += other.totalDuration;
        this.minDuration += Math.min(this.minDuration, other.minDuration);
        this.maxDuration += Math.max(this.maxDuration, other.maxDuration);
        this.firstVisitCount += other.firstVisitCount;
        this.totalSymptoms += other.totalSymptoms;
        this.morningApppointments += other.morningApppointments;
        this.afternoomAppointments += other.afternoomAppointments;
        this.averageDuration += other.averageDuration;
        this.averagePrice += other.averagePrice;
        this.averageSymptoms += averageSymptoms;
        return this;
    }

    public AppointmentStatistics tStatistics(){
        return  new AppointmentStatistics(
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
}
