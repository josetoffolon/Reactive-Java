
package main.java.edu.itmo.reactive.lab01.statistics.collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class SymptomsCollector implements Collector<
        MedicalAppointment,
        Map<String, Long>,
        Map<String, Long>> {

    @Override
    public Supplier<Map<String, Long>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Long>,
                      MedicalAppointment> accumulator() {

        return (map, appointment) -> {
            for (String symptom : appointment.getSymptoms()) {
                map.merge(symptom, 1L, Long::sum);
            }
        };
    }

    @Override
    public BinaryOperator<Map<String, Long>> combiner() {
        return (left, right) -> {
            right.forEach((symptom, count) ->
                    left.merge(symptom, count, Long::sum));
            return left;
        };
    }

    @Override
    public Function<Map<String, Long>,
                    Map<String, Long>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
