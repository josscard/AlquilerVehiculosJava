package org.example.alquilervehiculos.models;

public class Van extends AbstractVehicle {

    private short capacity;

    public Van(String typeVehicle, String tuition, int km, boolean status, double rentalValue, short capacity, String color) {
        super(typeVehicle, tuition, km, status, rentalValue, color);
        this.capacity = capacity;
        super.setTypeVehicle("Van");
    }

    public Van(short capacity) {
        this.capacity = capacity;
    }

    @Override
    public void rent() {
        setStatus(true);
    }

    @Override
    public String showData() {
        return "Furgoneta de placas: "+getTuition()+", con capacidad de "+ capacity+" kg, KM: "+ getKm();
    }

    @Override
    public void returnVehicle(int kmFinal) {

    }

    @Override
    public void returnVehicle(int kmFinal, short numDays) {
        setStatus(false);
        setKm(kmFinal);
        numDays++;

    }

    @Override
    public double calculateRentalValue(int km) {
        return getRentalValue() * km + capacity * 0.1;
    }
}
