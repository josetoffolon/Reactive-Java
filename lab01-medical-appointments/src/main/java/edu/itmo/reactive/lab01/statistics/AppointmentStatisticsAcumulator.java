package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.Doctor;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsAcumulator {

    private long totalCount;
    private final Map<AppointmentStatus, Long> countByStatus = new HashMap<>();
    private final Map<String, Long> countBySymptoms = new HashMap<>();
    private final Map<Doctor, Long> countByDoctor = new HashMap<>();
    private final Map<YearMonth, Double> totalPriceByMonth = new HashMap<>();

    
    public void add(MedicalAppointment appointment){

        totalCount++;

        countByStatus.merge(appointment.getStatus(), 1L, Long::sum);

        for (String symtom : appointment.getSymptoms()){
            countBySymptoms.merge(symtom, 1L, Long::sum);
        }

        countByDoctor.merge(appointment.getDoctor(), 1L, Long::sum);

        YearMonth month = YearMonth.from(appointment.getAppointmentDate());
        totalPriceByMonth.merge(month, appointment.getPrice(), Double::sum);

    }

    public AppointmentStatisticsAcumulator combine(AppointmentStatisticsAcumulator other){

        if (other.totalCount == 0) return this;

        this.totalCount += other.totalCount;

        other.countByStatus.forEach((k, v) -> this.countByStatus.merge(k, v, Long::sum));
        other.countByDoctor.forEach((k, v) -> this.countByDoctor.merge(k, v, Long::sum));
        other.countBySymptoms.forEach((k, v) -> this.countBySymptoms.merge(k, v, Long::sum));
        other.totalPriceByMonth.forEach((k, v) -> this.totalPriceByMonth.merge(k, v, Double::sum));

        return this;

    }

    public AppointmentStatistics tStatistics(){
        return  new AppointmentStatistics(
            totalCount,
            Map.copyOf(countByStatus),
            Map.copyOf(countByDoctor),
            Map.copyOf(countBySymptoms),
            Map.copyOf(totalPriceByMonth)
        );
    }
}
