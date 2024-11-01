package com.kusd.KUmap.domain.competitionRate.repository.converter;

import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionConverter implements AttributeConverter<List<Double>, String> {
    // List<Integer> -> String (예: [1, 2, 3] -> "1,2,3")
    @Override
    public String convertToDatabaseColumn(List<Double> integerList) {
        if (integerList == null || integerList.isEmpty()) {
            return "";
        }
        return integerList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    // String -> List<Integer> (예: "1,2,3" -> [1, 2, 3])
    @Override
    public List<Double> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(s.split(","))
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }
}
