package com.cevs.rentall.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TruckDto extends VehicleDto {
    @NotNull
    private float truckHeight;
    private boolean trailer;
    @NotNull
    private float trailerLength;
    @NotNull
    private float trailerWidth;
    @NotNull
    private float trailerHeight;
    @NotNull
    private int freightSpace;

    public float getTruckHeight() {
        return truckHeight;
    }

    public void setTruckHeight(float truckHeight) {
        this.truckHeight = truckHeight;
    }

    public boolean isTrailer() {
        return trailer;
    }

    public void setTrailer(boolean trailer) {
        this.trailer = trailer;
    }

    public float getTrailerLength() {
        return trailerLength;
    }

    public void setTrailerLength(float trailerLength) {
        this.trailerLength = trailerLength;
    }

    public float getTrailerWidth() {
        return trailerWidth;
    }

    public void setTrailerWidth(float trailerWidth) {
        this.trailerWidth = trailerWidth;
    }

    public float getTrailerHeight() {
        return trailerHeight;
    }

    public void setTrailerHeight(float trailerHeight) {
        this.trailerHeight = trailerHeight;
    }

    public int getFreightSpace() {
        return freightSpace;
    }

    public void setFreightSpace(int freightSpace) {
        this.freightSpace = freightSpace;
    }
}
