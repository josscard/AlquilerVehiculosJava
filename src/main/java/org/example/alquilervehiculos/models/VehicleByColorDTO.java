package org.example.alquilervehiculos.models;

import lombok.Getter;

import java.util.List;

@Getter
public class VehicleByColorDTO {


        private String color;
        private List<AbstractVehicle> vehicle;
        private int cantity;

    public VehicleByColorDTO(String color, List<AbstractVehicle> vehicle) {
        this.color = color;
        this.vehicle = vehicle;
        this.cantity = vehicle.size();
    }

    // Getters
    public String getColor() {
        return color;
    }

    public List<AbstractVehicle> getVehicle() {
        return vehicle;
    }

    public int getCantity() {
        return cantity;
    }

    }

