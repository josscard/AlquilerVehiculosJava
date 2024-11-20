package org.example.alquilervehiculos.models;

import lombok.Getter;

import java.util.Date;

@Getter
public class RentVehicle {
    private AbstractVehicle vehicle;
    private User userRegister;
    private Date rentDate;
    private Date devolutionDate;
    private Client client;

    public RentVehicle(AbstractVehicle vehicle, User userRegister, Date rentDate, Date devolutionDate, Client client) {
        this.vehicle = vehicle;
        this.userRegister = userRegister;
        this.rentDate = rentDate;
        this.devolutionDate = devolutionDate;
        this.client = client;
    }

    public void rentVehicle(AbstractVehicle vehicle, User userRegister) {
        this.vehicle = vehicle;
        this.userRegister = userRegister;

    }

    // Método para calcular las horas de alquiler
    public long getHourBusy() {
        long diferenciaMillis = rentDate.getTime() - devolutionDate.getTime();
        return diferenciaMillis / (1000 * 60 * 60);  // Convertir milisegundos a horas
    }
}
