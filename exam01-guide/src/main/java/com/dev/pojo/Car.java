package com.dev.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class Car {

    private String name;

    @NotNull(message = "车主不能为空")
    public String getRentalStation() {
        return name;
    }

    public void drive(@Max(75) int speedInMph) {

    }
}
