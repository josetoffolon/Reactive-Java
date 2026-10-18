 package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.Doctor;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsCalculator {

    public AppointmentStatistics calculateWithLoop(List<MedicalAppointment> appointments){

        if (appointments == null || appointments.isEmpty()){
            return AppointmentStatistics.empty();
        }
 
        long totalCount = 0;
        Map<AppointmentStatus, Long> countByStatus = new HashMap<>();
        Map<Doctor, Long> countByDoctor = new HashMap<>();
        Map<String, Long> countBySymptoms = new HashMap<>();
        Map<YearMonth, Double> totalPriceByMonth = new HashMap<>();

        for(MedicalAppointment a : appointments){
            totalCount++;
            countByStatus.merge(a.getStatus(), 1L, Long::sum);

            for(String s: a.getSymptoms()){
                countBySymptoms.merge(s, 1L, Long::sum);
            }

            countByDoctor.merge(a.getDoctor(), 1L, Long::sum);

            YearMonth month = YearMonth.from(a.getAppointmentDate());
            totalPriceByMonth.merge(month, a.getPrice(), Double::sum);
        }

        return new AppointmentStatistics(
            totalCount,
            countByStatus,
            countByDoctor,
            countBySymptoms,
            totalPriceByMonth
        );
    }

    public AppointmentStatistics calculateWithStream(List<MedicalAppointment> appointments){

        if (appointments == null || appointments.isEmpty()){
            return AppointmentStatistics.empty();
        }
        
        long totalCount = appointments.size();

        Map<AppointmentStatus, Long> countByStatus = appointments.stream()
            .collect(Collectors.groupingBy(
                MedicalAppointment::getStatus,
                Collectors.counting()));

        Map<Doctor, Long> countByDoctor = appointments.stream()
            .collect(Collectors.groupingBy(
                MedicalAppointment::getDoctor,
                Collectors.counting()));

        Map<String, Long> countBySymptoms = appointments.stream()
            .flatMap(a -> a.getSymptoms().stream())
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()));
        
        Map<YearMonth, Double> totalPriceByMonth = appointments.stream()
            .collect(Collectors.groupingBy(
                a -> YearMonth.from(a.getAppointmentDate()),
                Collectors.summingDouble(MedicalAppointment::getPrice)));

        return new AppointmentStatistics(
            totalCount,
            countByStatus,
            countByDoctor,
            countBySymptoms,
            totalPriceByMonth
        );
    }

    public AppointmentStatistics calculateWithCollector(List<MedicalAppointment> appointments){
        if (appointments == null || appointments.isEmpty()){
            return AppointmentStatistics.empty();
        }
        return appointments.stream().collect(AppointmentStatisticsCollector.INSTANCE);
    }
    
}