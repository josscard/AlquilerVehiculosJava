package org.example.alquilervehiculos.models;

public interface VehiculoAble {

    void rent();

    String showData();

    void returnVehicle(int kmFinal);

    double calculateRentalValue(int km);
}
