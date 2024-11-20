package org.example.alquilervehiculos.service;

import org.example.alquilervehiculos.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleManagementService {

    private List<AbstractVehicle> listVehicles = new ArrayList<>();
    private List<RentVehicle> rentVehicles = new ArrayList<>();

    // Crear vehículos predeterminados
        public VehicleManagementService() {
            listVehicles.add(new Motorbike("Motorbike","MOTO123", 1000, true, 250000, true));
            listVehicles.add(new Car("Car","COCHE456", 20000, false, 362000, true));
            listVehicles.add(new Van("Van","FURG789", 5000, true, 50200, (short)200));
        }




    public void registerVehicle(AbstractVehicle vehicles) {
        listVehicles.add(vehicles);

    }

    public List<AbstractVehicle> getVehicles() {
        return listVehicles;
    }

    public List<AbstractVehicle> listVehiclesbyType(String typeVehicle) {
        List<AbstractVehicle> result = new ArrayList<>();
        for (AbstractVehicle vehicle : listVehicles) {
            if (vehicle.getTypeVehicle().equalsIgnoreCase(typeVehicle)) {
                result.add(vehicle);
            }
        }
        return result;
    }



    public AbstractVehicle findVehicleByTuition(String tuition) {
        for (AbstractVehicle vehicle : listVehicles) {
            if (vehicle.getTuition().equalsIgnoreCase(tuition)) {
                return vehicle;
            }
        }
        return null; // Si no se encuentra el vehículo
    }

    public List<AbstractVehicle> listVehiclesByType(String typeVehicle) {
        List<AbstractVehicle> result = new ArrayList<>();
        for (AbstractVehicle vehicle : listVehicles) {
            if (vehicle.getTypeVehicle().equalsIgnoreCase(typeVehicle)) {
                result.add(vehicle);
            }
        }
        return result;
}
    public String rentVehicle(String tuition) {
        AbstractVehicle vehicle = findVehicleByTuition(tuition);
        if (vehicle != null && !vehicle.isStatus()) { // Verifica si está disponible
            vehicle.setStatus(true); // Marca como alquilado
            return "El vehículo con matrícula " + tuition + " ha sido alquilado.";
        }

        return "El vehículo con matrícula " + tuition + " no está disponible o no existe.";
    }

    public String returnVehicle(String tuition, int kmFinal, short numDays) {
        AbstractVehicle vehicle = findVehicleByTuition(tuition);
        if (vehicle != null && vehicle.isStatus()) { // Verifica si está alquilado
            vehicle.setStatus(false); // Marca como disponible

            vehicle.setKm(kmFinal);  // Actualiza los kilómetros
            vehicle.returnVehicle(kmFinal,numDays);
            return "El vehículo con matrícula " + tuition + " ha sido devuelto y recorrió en " + numDays + " dias, "+kmFinal +" km";
        }
        return "El vehículo con matrícula " + tuition + " no está alquilado o no existe.";
    }

    //editar datos del vehiculo
    public String editVehicle(String tuition, String newTuition, Integer newKm, Double newValueRent, String newTypeVehicle) {
        AbstractVehicle vehicle = findVehicleByTuition(tuition);
        if (vehicle != null) {
            if (newTuition != null) vehicle.setTuition(newTuition);
            if (newKm != null && newKm >= 0) vehicle.setKm(newKm);
            if (newValueRent != null && newValueRent > 0) vehicle.setRentalValue(newValueRent);
            if (newTypeVehicle != null) vehicle.setTypeVehicle(newTypeVehicle);
            return "Los datos del vehículo con matrícula " + tuition + " ha sido actualizado.";
        }
        return "El vehículo con matrícula " + tuition + " no existe.";
    }

    // Eliminar vehiculo por matricula
    public String deleteVehicle(String tuition) {
        List<AbstractVehicle> newList = new ArrayList<>();
        boolean encontrado = false;

        for (AbstractVehicle vehicle : listVehicles) {
            if (!vehicle.getTuition().equalsIgnoreCase(tuition)) {
                newList.add(vehicle);
            } else {
                encontrado = true;
            }
        }

        if (encontrado) {
            listVehicles = newList; // Reasignar la lista sin el vehículo eliminado
            return "El vehículo con matrícula " + tuition + " ha sido eliminado.";
        }
        return "El vehículo con matrícula " + tuition + " no existe.";
    }


    // Método para generar el reporte del promedio de horas ocupadas entre dos fechas
    public double calculatePromediumHoursOccupated(Date rentDate, Date devolutionDate) {
        long totalHours = 0;
        int count = 0;

        for (RentVehicle reportDate : rentVehicles) {
            // Si el alquiler está dentro del rango de fechas proporcionado
            if (!(reportDate.getDevolutionDate().before(rentDate) || reportDate.getRentDate().after(devolutionDate))) {
                totalHours += reportDate.getHourBusy();
                count++;
            }
        }

        if (count == 0) {
            return 0; // Si no hay alquileres en el rango, retornar 0
        }

        return (double) totalHours / count; // Calcular promedio
    }

}
