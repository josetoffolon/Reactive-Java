package main.java.edu.itmo.reactive.lab01.statistics.collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import main.java.edu.itmo.reactive.lab01.model.AppointmentStatus;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class StatusCollector implements Collector<
        MedicalAppointment,
        Map<AppointmentStatus, Long>,
        Map<AppointmentStatus, Long>> {

    @Override
    public Supplier<Map<AppointmentStatus, Long>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<AppointmentStatus, Long>,
                      MedicalAppointment> accumulator() {

        return (map, appointment) ->
                map.merge(
                        appointment.getStatus(),
                        1L,
                        Long::sum
                );
    }

    @Override
    public BinaryOperator<Map<AppointmentStatus, Long>> combiner() {
        return (left, right) -> {
            right.forEach((status, count) ->
                    left.merge(status, count, Long::sum));
            return left;
        };
    }

    @Override
    public Function<Map<AppointmentStatus, Long>,
                    Map<AppointmentStatus, Long>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
