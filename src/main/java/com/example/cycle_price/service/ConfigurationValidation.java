package com.example.cycle_price.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class ConfigurationValidation {
    public String validateConfiguration(List<String> parts) {

//Check if the number of parts is exactly 13        
    final int REQUIRED_PART_COUNT = 13;
    if (parts.size() != REQUIRED_PART_COUNT) {

        return "Invalid number of parts selected. Expected "
                + REQUIRED_PART_COUNT
                + " parts but found "
                + parts.size();
    }

//Map to count the occurrences of each sub-type in the configuration
    Map<String, Integer> subTypeCount = new HashMap<>();

    subTypeCount.put("Frame", 0);
    subTypeCount.put("Handlebar", 0);
    subTypeCount.put("Brakes", 0);
    subTypeCount.put("Saddle", 0);
    subTypeCount.put("Seat Post", 0);
    subTypeCount.put("Rim", 0);
    subTypeCount.put("Tyre", 0);
    subTypeCount.put("Tube", 0);
    subTypeCount.put("Spokes", 0);
    subTypeCount.put("Chain", 0);
    subTypeCount.put("Gear", 0);
    subTypeCount.put("Pedals", 0);
    subTypeCount.put("Crank Set", 0);

 // Iterate through the input parts and count the occurrences of each sub-type   
    for (String partName : parts) {

        for (String key : subTypeCount.keySet()) {

            if (partName.contains(key)) {

                subTypeCount.put(key, subTypeCount.get(key) + 1);

                break;
            }
        }
        
    }

// Validate that each sub-type is selected exactly once
 for (Map.Entry<String, Integer> entry : subTypeCount.entrySet()) {

        String key = entry.getKey();
        Integer count = entry.getValue();

        if (count == 0) {
            return key + " is missing in the configuration";
        }

        if (count > 1) {
            return "Multiple " + key + " options selected";
        }
    }

    return "VALID";

}
}
