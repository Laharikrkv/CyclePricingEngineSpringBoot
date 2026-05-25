package com.example.cycle_price.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cycle_price.dto.CycleConfigurationPriceDto;
import com.example.cycle_price.entity.Part;
import com.example.cycle_price.entity.Price;
import com.example.cycle_price.repository.PartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceCalculationService {

    @Autowired
    private final PartRepository partRepository;

    public CycleConfigurationPriceDto calculatePrice(List<String> parts, LocalDate preferredDate) {

        CycleConfigurationPriceDto configurationPrice = new CycleConfigurationPriceDto();

        for (String partName : parts) {

            Part part = partRepository.findByName(partName)
                    .orElseThrow(() -> new RuntimeException("Invalid part: " + partName));

            Price validPrice;
            validPrice = part.getPriceHistory()
                    .stream()
                    .filter(price -> !preferredDate.isBefore(price.getValidFrom()))
                    .filter(price
                            -> price.getValidTill() == null
                    || !preferredDate.isAfter(price.getValidTill())
                    )
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                    "Price not available for part: " + partName + " on " + preferredDate
            ));

            BigDecimal price = validPrice.getAmount();

            switch (part.getComponent()) {
                case "FRAME" ->
                    configurationPrice.setFrame(
                            configurationPrice.getFrame().add(price)
                    );

                case "HANDLE_BAR_AND_BRAKES" ->
                    configurationPrice.setHandleBarAndBrakes(
                            configurationPrice.getHandleBarAndBrakes().add(price)
                    );

                case "SEATING" ->
                    configurationPrice.setSeating(
                            configurationPrice.getSeating().add(price)
                    );

                case "WHEELS" ->
                    configurationPrice.setWheels(
                            configurationPrice.getWheels().add(price)
                    );

                case "CHAIN_ASSEMBLY" ->
                    configurationPrice.setChainAssembly(
                            configurationPrice.getChainAssembly().add(price)
                    );
            }
        }

        BigDecimal total = configurationPrice.getFrame()
                .add(configurationPrice.getHandleBarAndBrakes())
                .add(configurationPrice.getSeating())
                .add(configurationPrice.getWheels())
                .add(configurationPrice.getChainAssembly());

        configurationPrice.setTotalPrice(total);

        return configurationPrice;
    }

}
