package main.java.edu.itmo.reactive.lab01.statistics;

public record AppointmentStatistics(
    long count,
    long completed,
    long cancelled,
    long scheduled,
    long noShow
) {}
