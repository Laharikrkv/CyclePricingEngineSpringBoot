package com.example.cycle_price.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cycle_price.dto.CycleConfigurationPriceDto;
import com.example.cycle_price.dto.CycleRequest;
import com.example.cycle_price.service.ConfigurationValidation;
import com.example.cycle_price.service.PriceCalculationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cycles")
@RequiredArgsConstructor

public class PriceController {

    private final PriceCalculationService pricingService;
    private final ConfigurationValidation validationService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculatePrice(
            @RequestBody CycleRequest request
    ) {

        LocalDate preferredDate = LocalDate.parse(request.getPreferredDate());

        if (preferredDate.isBefore(LocalDate.of(2024, 5, 1))
                || preferredDate.isAfter(LocalDate.now())) {

            return ResponseEntity
                    .badRequest()
                    .body("Date should be between 2024-05-01 and today");
        }

        String validationResult
                = validationService.validateConfiguration(request.getParts());

        if (!validationResult.equals("VALID")) {

            return ResponseEntity
                    .badRequest()
                    .body(validationResult);
        }

        CycleConfigurationPriceDto response
                = pricingService.calculatePrice(
                        request.getParts(),
                        preferredDate
                );

        return ResponseEntity.ok(response);
    }

}
