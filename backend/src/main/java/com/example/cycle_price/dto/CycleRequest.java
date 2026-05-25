package com.example.cycle_price.dto;

import java.util.List;

public class CycleRequest {

    private List<String> parts;

    private String preferredDate;

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    public String getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }
}
