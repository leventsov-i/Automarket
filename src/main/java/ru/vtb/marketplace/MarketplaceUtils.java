package ru.vtb.marketplace;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.vtb.marketplace.pojo.CarBody;

/**
 * @author denis-panin
 */
public class MarketplaceUtils {
    private MarketplaceUtils() {
    }

    public static final Map<String, String> NAME_TO_ALIAS = Map.ofEntries(
            Map.entry("Mazda 6", "mazda6"),
            Map.entry("Mazda 3", ""),
            Map.entry("Cadillac ESCALADE", "escalade"),
            Map.entry("Jaguar F-PACE", "f-pace"),
            Map.entry("BMW 5", ""),
            Map.entry("KIA Sportage", "sportage"),
            Map.entry("Chevrolet Tahoe", "tahoe"),
            Map.entry("KIA K5", "k5"),
            Map.entry("Hyundai Genesis", ""),
            Map.entry("Toyota Camry", ""),
            Map.entry("Mercedes A", ""),
            Map.entry("Land Rover RANGE ROVER VELAR", "rangerovervelar"),
            Map.entry("BMW 3", "3_series"),
            Map.entry("KIA Optima", "new_optima"));

    private static final List<String> BODY_PRIORITY = List.of(
            "sedan", "coupe", "hatchback", "liftback", "crossover", "suv", "wagon", "van", "microbus", "pickup");

    private static final Map<String, Integer> BODY_PRIORITY_MAP = IntStream.range(0, BODY_PRIORITY.size())
            .boxed()
            .collect(Collectors.toMap(BODY_PRIORITY::get, Function.identity()));

    public static Comparator<CarBody> bodyPriorityComparator = (s1, s2) -> {
        int priority1 = BODY_PRIORITY_MAP.getOrDefault(s1.type, Integer.MAX_VALUE);
        int priority2 = BODY_PRIORITY_MAP.getOrDefault(s2.type, Integer.MAX_VALUE);
        return Integer.compare(priority1, priority2);
    };
}
