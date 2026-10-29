
package main.java.edu.itmo.reactive.lab01.statistics.collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import main.java.edu.itmo.reactive.lab01.model.Doctor;
import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class DoctorCollector implements Collector<
        MedicalAppointment,
        Map<Doctor, Long>,
        Map<Doctor, Long>> {

    @Override
    public Supplier<Map<Doctor, Long>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<Doctor, Long>,
                      MedicalAppointment> accumulator() {

        return (map, appointment) ->
                map.merge(
                        appointment.getDoctor(),
                        1L,
                        Long::sum
                );
    }

    @Override
    public BinaryOperator<Map<Doctor, Long>> combiner() {
        return (left, right) -> {
            right.forEach((doctor, count) ->
                    left.merge(doctor, count, Long::sum));
            return left;
        };
    }

    @Override
    public Function<Map<Doctor, Long>,
                    Map<Doctor, Long>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}

