package com.example.cycle_price;

import org.junit.jupiter.api.Test;

import com.example.cycle_price.service.ConfigurationValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationValidationTest {

    private final ConfigurationValidation configurationValidation =
            new ConfigurationValidation();

    @Test
    void shouldReturnValidWhenAllRequiredPartsArePresent() {
        List<String> parts = List.of(
                "Steel Frame",
                "Standard Handlebar",
                "Disc Brakes",
                "Basic Saddle",
                "Standard Seat Post",
                "Alloy Rim",
                "Road Tyre",
                "Standard Tube",
                "Steel Spokes",
                "Single Speed Chain",
                "Single Gear",
                "Standard Pedals",
                "Single Crank Set"
        );

        String result = configurationValidation.validateConfiguration(parts);

        assertEquals("VALID", result);
    }

    @Test
    void shouldReturnErrorWhenPartCountIsInvalid() {
        List<String> parts = List.of(
                "Steel Frame",
                "Standard Handlebar",
                "Disc Brakes"
        );

        String result = configurationValidation.validateConfiguration(parts);

        assertEquals(
                "Invalid number of parts selected. Expected 13 parts but found 3",
                result
        );
    }

    @Test
    void shouldReturnErrorWhenRequiredSubtypeIsMissing() {
        List<String> parts = List.of(
                "Steel Frame",
                "Standard Handlebar",
                "Disc Brakes",
                "Basic Saddle",
                "Standard Seat Post",
                // Rim missing
                "Road Tyre",
                "Standard Tube",
                "Steel Spokes",
                "Single Speed Chain",
                "Single Gear",
                "Standard Pedals",
                "Single Crank Set",
                "Extra Part"
        );

        String result = configurationValidation.validateConfiguration(parts);

        assertEquals("Rim is missing in the configuration", result);
    }

    @Test
    void shouldReturnErrorWhenDuplicateSubtypeIsSelected() {
        List<String> parts = List.of(
                "Steel Frame",
                "Aluminium Frame",
                "Standard Handlebar",
                "Disc Brakes",
                "Basic Saddle",
                "Standard Seat Post",
                "Alloy Rim",
                "Road Tyre",
                "Standard Tube",
                "Steel Spokes",
                "Single Speed Chain",
                "Single Gear",
                "Standard Pedals"
        );

        String result = configurationValidation.validateConfiguration(parts);

        assertEquals("Multiple Frame options selected", result);
    }
}