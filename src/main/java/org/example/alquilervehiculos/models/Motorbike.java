package org.example.alquilervehiculos.models;

public class Motorbike extends AbstractVehicle {

    private boolean helmet; //casco

    public Motorbike(String typeVehicle, String tuition, int km, boolean status, double rentalValue, boolean helmet) {
        super(typeVehicle, tuition, km, status, rentalValue);
        this.helmet = helmet;
        super.setTypeVehicle("Motorbike");
    }

    public Motorbike(boolean helmet) {
        this.helmet = helmet;
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
    public void returnVehicle(int kmFinal, short numDays) {

    }

    @Override
    public double calculateRentalValue(int km) {
        return 0;
    }
}
