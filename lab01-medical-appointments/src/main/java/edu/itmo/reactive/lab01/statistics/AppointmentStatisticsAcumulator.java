package main.java.edu.itmo.reactive.lab01.statistics;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsAcumulator {

    private long count;
    private long completed;
    private long cancelled;
    private long scheduled;
    private long noShow;
    double totalPrice;
    double averagePrice;
    double minPrice;
    double maxPrice;
    long totalDuration;
    long averageDuration;
    long minDuration;
    long maxDuration;
    long firstVisitCount;
    long totalSymptoms;
    long averageSymptoms;
    long morningApppointments;
    long afternoomAppointments;

    
    public void add(MedicalAppointment appointment){

        count++;

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
