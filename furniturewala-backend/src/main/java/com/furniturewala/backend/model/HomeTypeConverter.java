package com.furniturewala.backend.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HomeTypeConverter implements AttributeConverter<Project.HomeType, String> {
    @Override
    public String convertToDatabaseColumn(Project.HomeType homeType) {
        if (homeType == null) return null;
        return switch (homeType) {
            case ONE_BHK -> "1_BHK";
            case TWO_BHK -> "2_BHK";
            case THREE_BHK -> "3_BHK";
            case FOUR_BHK -> "4_BHK";
            case OTHER -> "OTHER";
        };
    }

    @Override
    public Project.HomeType convertToEntityAttribute(String value) {
        if (value == null) return null;
        return switch (value) {
            case "1_BHK" -> Project.HomeType.ONE_BHK;
            case "2_BHK" -> Project.HomeType.TWO_BHK;
            case "3_BHK" -> Project.HomeType.THREE_BHK;
            case "4_BHK" -> Project.HomeType.FOUR_BHK;
            case "OTHER" -> Project.HomeType.OTHER;
            default -> throw new IllegalArgumentException("Unknown home_type value: " + value);
        };
    }
}
