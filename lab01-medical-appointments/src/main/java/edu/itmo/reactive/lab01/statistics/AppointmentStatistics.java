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
    double averageDuration,
    long minDuration,
    long maxDuration,
    long firstVisitCount,
    long totalSymptoms,
    double averageSymptoms,
    long morningApppointments,
    long afternoomAppointments
) {}
