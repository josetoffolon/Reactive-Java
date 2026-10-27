package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.YearMonth;
import java.util.Map;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.Doctor;

public record AppointmentStatistics(
    long totalCount,
    Map<AppointmentStatus, Long> countByStatus,
    Map<Doctor, Long> countByDoctor,
    Map<String, Long> countBySymtoms,
    Map<YearMonth, Double> totalPriceByMonth
) {
    public AppointmentStatistics(long totalCount, 
        Map<AppointmentStatus,Long> countByStatus,
        Map<Doctor,Long> countByDoctor,
        Map<String,Long> countBySymtoms,
        Map<YearMonth,Double> totalPriceByMonth) {
                this.totalCount = totalCount;
                this.countByStatus = countByStatus;
                this.countByDoctor = countByDoctor;
                this.countBySymtoms = countBySymtoms;
                this.totalPriceByMonth = totalPriceByMonth;
	}

	public static AppointmentStatistics empty() {
        return new AppointmentStatistics(
            0L, Map.of(), Map.of(), Map.of(), Map.of()
        );
    }

}
