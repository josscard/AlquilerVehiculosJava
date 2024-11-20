package org.example.alquilervehiculos.controller;

import org.example.alquilervehiculos.models.*;
import org.example.alquilervehiculos.service.UserManagementServices;
import org.example.alquilervehiculos.service.VehicleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/api")
public class RentVehiclesController {

    @Autowired
    private VehicleManagementService vehicleManagementService;

    @Autowired
    private UserManagementServices userManagementServices;

    // Listar todos los vehículos
    @GetMapping("/list-vehicles")
    public List<AbstractVehicle> listvehicles() {
        return vehicleManagementService.getVehicles();
    }

    // Registrar Motos
    @PostMapping("/register-vehicle-moto")
    public String registerVehicle(@RequestBody Motorbike moto) {
        vehicleManagementService.registerVehicle(moto);
        return "Vehículo tipo Moto registrado exitosamente";
    }

    // Registrar Carros
    @PostMapping("/register-vehicle-car")
    public String registerVehicle(@RequestBody Car car) {
        vehicleManagementService.registerVehicle(car);
        return "Vehículo tipo Carro registrado exitosamente";
    }

    // Registrar Furgoneta
    @PostMapping("/register-vehicle-van")
    public String registerVehicle(@RequestBody Van van) {
        vehicleManagementService.registerVehicle(van);
        return "Vehículo tipo furgoneta registrada exitosamente";
    }


    @GetMapping("/list-vehicle-by-type/{typeVehicle}")
    public List<AbstractVehicle> listVehiclesByType(@PathVariable String typeVehicle) {
        return vehicleManagementService.listVehiclesbyType(typeVehicle);
    }

    // Alquilar un vehículo
    @PostMapping("/rent/{tuition}")
    public String rentVehicle(@PathVariable String tuition, @RequestParam int kmFinal) {
        return vehicleManagementService.rentVehicle(tuition);
    }

    // Devolver un vehículo
    @PostMapping("/return-vehicle/{tuition}")
    public String returnVehicle(@PathVariable String tuition, @RequestParam int kmFinal , @RequestParam short numDays) {
        return vehicleManagementService.returnVehicle(tuition, kmFinal, numDays);
    }

    // Editar un vehículo específico
    @PutMapping("/edit-vehicle/{tuition}")
    public String editarVehiculo(
            @PathVariable String tuition,
            @RequestParam(required = false) String newTuition,
            @RequestParam(required = false) Integer newKm,
            @RequestParam(required = false) Double newValueRent,
            @RequestParam(required = false) String newTypeVehicle) {

        return vehicleManagementService.editVehicle(tuition, newTuition, newKm, newValueRent, newTypeVehicle);
    }

    @DeleteMapping("/delete-vehicle/{tuition}")
    public String eliminarVehiculo(@PathVariable String tuition) {
        return vehicleManagementService.deleteVehicle(tuition);
    }




    @PostMapping("/register-user")
    public String registerUser(@RequestBody User user) {
        userManagementServices.registerUser(user);
        return "Usuario registrado exitosamente";


    }

    @GetMapping("/list-users")
    public List<User> listUsers() {
        return userManagementServices.getUsers();
    }

    // Endpoint para validar el login
    @PostMapping("/login-user")
    public String login(@RequestParam String login, @RequestParam String password) {
        String tipoUsuarioDescripcion = userManagementServices.validateUser(login, password);
        if (tipoUsuarioDescripcion != null) {
            return "Login exitoso, Bienvenido : " + tipoUsuarioDescripcion+" "+ login;
        }
        return "Credenciales inválidas.";
    }


    // Método para convertir una cadena en un objeto Date
    private Date convertToDate(String date) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return formatoFecha.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Si la fecha no es válida, devuelve null
        }
    }

    // Endpoint para obtener el promedio de horas ocupadas entre dos fechas
    @GetMapping("/promedio-ocupation")
    public double getPromediumOccupation(
            @RequestParam String rentDate,
            @RequestParam String devolutionDate) {

        Date startDate = convertToDate(rentDate);
        Date endDate = convertToDate(devolutionDate);

        if (startDate == null || endDate == null) {
            return -1; // Indicar que hubo un error con las fechas
        }

        return vehicleManagementService.calculatePromediumHoursOccupated(startDate, endDate);
    }















}
