package main.java.edu.itmo.reactive.lab01.statistics;

public record AppointmentStatistics(
    long count,
    long completed,
    long cancelled,
    long scheduled,
    long noShow,
    double totalPrice,
    double averagePrice,
    double minPrice,
    double maxPrice,
    long totalDuration,
    long averageDuration,
    long minDuration,
    long maxDuration,
    long firstVisitCount,
    long totalSymptoms,
    long averageSymptoms,
    long morningApppointments,
    long afternoomAppointments
) {}
