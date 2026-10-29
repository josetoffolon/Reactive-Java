 package main.java.edu.itmo.reactive.lab01.statistics;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.Doctor;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;
import main.java.edu.itmo.reactive.lab01.statistics.collectors.DoctorCollector;
import main.java.edu.itmo.reactive.lab01.statistics.collectors.PriceByMonthCollector;
import main.java.edu.itmo.reactive.lab01.statistics.collectors.StatusCollector;
import main.java.edu.itmo.reactive.lab01.statistics.collectors.SymptomsCollector;

public class AppointmentStatisticsCalculator {

    public Map<AppointmentStatus, Long> countByStatusLoop(List<MedicalAppointment> appointments){
        Map<AppointmentStatus, Long> result = new HashMap<>();
        for(MedicalAppointment appointment : appointments){
            result.merge(appointment.getStatus(), 1L, Long::sum);
        }

        return result;
    }

    public Map<AppointmentStatus, Long> countByStatusStream(List<MedicalAppointment> appointments){
        return appointments.stream().
            collect(
                Collectors.groupingBy(
                    MedicalAppointment::getStatus,
                    Collectors.counting()));
    }

    public Map<AppointmentStatus, Long> countByStatusCustom(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(new StatusCollector());
    }





    public Map<Doctor, Long> countByDoctorLoop(List<MedicalAppointment> appointments){
        Map<Doctor, Long> result = new HashMap<>();
        for(MedicalAppointment appointment: appointments){
            result.merge(appointment.getDoctor(), 1L, Long::sum);
        }
        return result;
    }

    public Map<Doctor, Long> countByDoctorStream(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(
                Collectors.groupingBy(
                    MedicalAppointment::getDoctor,Collectors.counting()));
    }

    public Map<Doctor, Long> countByDoctorCustom(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(new DoctorCollector());
    }



    public Map<String, Long> countBySymptomsLoop(List<MedicalAppointment> appointments){
        Map<String, Long> result = new HashMap<>();
        for(MedicalAppointment appointment : appointments){
            for(String symptom : appointment.getSymptoms()){
                result.merge(symptom, 1L, Long::sum);
            }
        }
        return result;
    }

    public Map<String, Long> countBySymptomsStream(List<MedicalAppointment> appointments){
        return appointments.stream()
            .flatMap(a -> a.getSymptoms().stream())
                .collect(Collectors
                    .groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String, Long> countBySymptomsCustom(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(new SymptomsCollector());
    }




    public Map<YearMonth, Double> totalPriceByMonthLoop(List<MedicalAppointment> appointments){
        Map<YearMonth, Double> result = new HashMap<>();
        for (MedicalAppointment a : appointments) {
            YearMonth month = YearMonth.from(a.getAppointmentDate());
            result.merge(month, a.getPrice(), Double::sum);
        }
        return result;
    }

    public Map<YearMonth, Double> totalPriceByMonthStream(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(Collectors.groupingBy(
                a -> YearMonth.from(a.getAppointmentDate()),
                    Collectors.summingDouble(
                        MedicalAppointment::getPrice)));
    }

    public Map<YearMonth, Double> totalPriceByMonthCustom(List<MedicalAppointment> appointments){
        return appointments.stream()
            .collect(new PriceByMonthCollector());
    }


    public AppointmentStatistics calculateWithLoop(List<MedicalAppointment> appointments){

        if (appointments == null || appointments.isEmpty()) {
            return AppointmentStatistics.empty();
        }

        return new AppointmentStatistics(
            appointments.size(),
            countByStatusLoop(appointments),
            countByDoctorLoop(appointments),
            countBySymptomsLoop(appointments),
            totalPriceByMonthLoop(appointments)
        );
    }

    public AppointmentStatistics calculateWithStream(List<MedicalAppointment> appointments){

        if (appointments == null || appointments.isEmpty()) {
            return AppointmentStatistics.empty();
        }

        return new AppointmentStatistics(
            appointments.size(),
            countByStatusStream(appointments),
            countByDoctorStream(appointments),
            countBySymptomsStream(appointments),
            totalPriceByMonthStream(appointments)
        );
    }

    public AppointmentStatistics calculateWithCollector(List<MedicalAppointment> appointments){

        if (appointments == null || appointments.isEmpty()){
            return AppointmentStatistics.empty();
        }

        return new AppointmentStatistics(
            appointments.size(),
            countByStatusCustom(appointments),
            countByDoctorCustom(appointments),
            countBySymptomsCustom(appointments),
            totalPriceByMonthCustom(appointments)
        );
    }
    
}