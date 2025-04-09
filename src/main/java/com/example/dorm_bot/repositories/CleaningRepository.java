package com.example.dorm_bot.repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CleaningRepository {
    private final String bucket = "\uD83E\uDEA3";
    private final String sponge = "\uD83E\uDDFD";

    private final List<String> NAMES = new ArrayList<>(List.of("Pierpaolo", "Igor", "Kasra", "Arvin", "Andrea"));
    private static final List<int[]> PAIRS = List.of(
            new int[]{0, 1},
            new int[]{2, 3},
            new int[]{4, 0},
            new int[]{1, 2},
            new int[]{3, 4}
    );

    public String getCurrentWeekCleaners() {
        int weekNumber = (int) ChronoUnit.WEEKS.between(LocalDate.of(2024, 1, 1), LocalDate.now()) % PAIRS.size();
        int[] pair = PAIRS.get(weekNumber);
        return sponge + "Cleaning this week" + bucket +
                "\n" +
                "\n" + NAMES.get(pair[0]) + " - Monday" +
                "\n" +
                "\n" + NAMES.get(pair[1]) + " - Friday";
    }

    public List<String> getAllCleaners() {
        return NAMES;
    }

}
