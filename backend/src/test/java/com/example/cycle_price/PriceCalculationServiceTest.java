package com.example.cycle_price;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cycle_price.dto.CycleConfigurationPriceDto;
import com.example.cycle_price.entity.Part;
import com.example.cycle_price.entity.Price;
import com.example.cycle_price.repository.PartRepository;
import com.example.cycle_price.service.PriceCalculationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PriceCalculationServiceTest {

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PriceCalculationService priceCalculationService;

    @Test
    public void shouldCalculatePriceForGivenValidDate() {
        LocalDate preferredDate = LocalDate.of(2025, 3, 15);

        Part frame = createPart(
                "Steel Frame",
                "FRAME",
                List.of(
                        createPrice("2024-05-01", "2024-10-01", "3500.00"),
                        createPrice("2024-10-01", "2025-02-01", "3800.00"),
                        createPrice("2025-02-01", null, "4000.00")
                )
        );

        when(partRepository.findByName("Steel Frame"))
                .thenReturn(Optional.of(frame));

        CycleConfigurationPriceDto result =
                priceCalculationService.calculatePrice(
                        List.of("Steel Frame"),
                        preferredDate
                );

        assertEquals(new BigDecimal("4000.00"), result.getFrame());
        assertEquals(new BigDecimal("4000.00"), result.getTotalPrice());
    }

    @Test
   public  void shouldThrowExceptionWhenPriceIsNotAvailableForPastDate() {
        LocalDate preferredDate = LocalDate.of(2023, 1, 1);

        Part frame = createPart(
                "Steel Frame",
                "FRAME",
                List.of(
                        createPrice("2024-05-01", "2024-10-01", "3500.00")
                )
        );

        when(partRepository.findByName("Steel Frame"))
                .thenReturn(Optional.of(frame));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> priceCalculationService.calculatePrice(
                        List.of("Steel Frame"),
                        preferredDate
                ));

        assertTrue(exception.getMessage().contains("Price not available"));
    }

    @Test
    public void shouldCalculateCorrectPriceOnBoundaryDate() {
        LocalDate preferredDate = LocalDate.of(2025, 2, 1);

        Part frame = createPart(
                "Steel Frame",
                "FRAME",
                List.of(
                        createPrice("2024-10-01", "2025-02-01", "3800.00"),
                        createPrice("2025-02-01", null, "4000.00")
                )
        );

        when(partRepository.findByName("Steel Frame"))
                .thenReturn(Optional.of(frame));

        CycleConfigurationPriceDto result =
                priceCalculationService.calculatePrice(
                        List.of("Steel Frame"),
                        preferredDate
                );

        assertEquals(new BigDecimal("3800.00"), result.getFrame());
    }

    private Part createPart(String name, String component, List<Price> prices) {
        Part part = new Part();
        part.setName(name);
        part.setComponent(component);
        part.setPriceHistory(prices);
        return part;
    }

    private Price createPrice(String validFrom, String validTill, String amount) {
        Price price = new Price();
        price.setValidFrom(LocalDate.parse(validFrom));
        price.setValidTill(validTill == null ? null : LocalDate.parse(validTill));
        price.setAmount(new BigDecimal(amount));
        return price;
    }
}