package main.java.edu.itmo.reactive.lab01.statistics;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class AppointmentStatisticsCollector implements Collector<
                    MedicalAppointment, 
                    AppointmentStatisticsAcumulator, 
                    AppointmentStatistics>{

    @Override
    public Supplier<AppointmentStatisticsAcumulator> supplier() {
        return AppointmentStatisticsAcumulator::new;
    }

    @Override
    public BiConsumer<AppointmentStatisticsAcumulator, MedicalAppointment> accumulator() {
        return AppointmentStatisticsAcumulator::add;
    }

    @Override
    public BinaryOperator<AppointmentStatisticsAcumulator> combiner() {
        return AppointmentStatisticsAcumulator::combine;
    }

    @Override
    public Function<AppointmentStatisticsAcumulator, AppointmentStatistics> finisher() {
        return AppointmentStatisticsAcumulator::tStatistics;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
    
}
