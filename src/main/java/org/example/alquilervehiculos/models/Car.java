package org.example.alquilervehiculos.models;

import lombok.Getter;

@Getter
public class Car extends AbstractVehicle {

    private boolean extras;


    public Car(String typeVehicle, String tuition, int km, boolean status, double rentalValue, boolean extras) {
        super(typeVehicle, tuition, km, status, rentalValue);
        this.extras = extras;
        super.setTypeVehicle("Car"); //para obligar a que el tipo sea predeterminado
    }

    public Car(boolean extras) {
        this.extras = extras;
    }


    @Override
    public void rent() {

    }

    @Override
    public String showData() {
        return "";
    }

    @Override
    public void returnVehicle(int kmFinal) {

    }



    @Override
    public double calculateRentalValue(int km) {
        return 0;
    }
}
