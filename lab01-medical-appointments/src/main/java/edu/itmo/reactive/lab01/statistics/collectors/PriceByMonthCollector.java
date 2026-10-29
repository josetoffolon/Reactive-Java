
package main.java.edu.itmo.reactive.lab01.statistics.collectors;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import main.java.edu.itmo.reactive.lab01.model.MedicalAppointment;

public class PriceByMonthCollector implements Collector<
        MedicalAppointment,
        Map<YearMonth, Double>,
        Map<YearMonth, Double>> {

    @Override
    public Supplier<Map<YearMonth, Double>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<YearMonth, Double>, MedicalAppointment> accumulator() {

        return (map, appointment) -> {
            YearMonth month =
                    YearMonth.from(
                            appointment.getAppointmentDate()
                    );

            map.merge(
                    month,
                    appointment.getPrice(),
                    Double::sum
            );
        };
    }

    @Override
    public BinaryOperator<Map<YearMonth, Double>> combiner() {
        return (left, right) -> {
            right.forEach((month, total) ->
                    left.merge(month, total, Double::sum));
            return left;
        };
    }

    @Override
    public Function<Map<YearMonth, Double>, Map<YearMonth, Double>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
