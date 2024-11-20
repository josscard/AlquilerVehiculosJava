package org.example.alquilervehiculos.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractVehicle implements VehiculoAble{

    private String TypeVehicle;
    private String tuition; // Matricula
    private int km; //Kilometros
    private boolean status; //Estado
    private double rentalValue; //Valor alquiler


    public AbstractVehicle(String typeVehicle, String tuition, int km, boolean status, double rentalValue) {
        TypeVehicle = typeVehicle;
        this.tuition = tuition;
        this.km = km;
        this.status = status;
        this.rentalValue = rentalValue;
    }

    public AbstractVehicle() {
    }

    public abstract double calculateRentalValue(int km);


    public void returnVehicle(int kmFinal, short numDays) {

    }
}
