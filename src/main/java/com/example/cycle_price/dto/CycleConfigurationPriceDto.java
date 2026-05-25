package com.example.cycle_price.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleConfigurationPriceDto {

    private BigDecimal frame = BigDecimal.ZERO;

    private BigDecimal handleBarAndBrakes = BigDecimal.ZERO;

    private BigDecimal seating = BigDecimal.ZERO;

    private BigDecimal wheels = BigDecimal.ZERO;

    private BigDecimal chainAssembly = BigDecimal.ZERO;

    private BigDecimal totalPrice = BigDecimal.ZERO;
}
