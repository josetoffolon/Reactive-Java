package main.java.edu.itmo.reactive.lab01.model;

public record AppointmentDetails(
    String room,
    int durationMinutes,
    boolean firstVisit
) {}
