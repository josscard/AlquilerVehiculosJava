package org.example.alquilervehiculos.service;


import lombok.Getter;
import org.example.alquilervehiculos.models.TypeUsers;
import org.example.alquilervehiculos.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServices {


    private List<User> listUsers = new ArrayList<>();
    private List<TypeUsers> listTypeUsers = new ArrayList<>();

    public UserManagementServices() {
        // Inicializar algunos tipos de usuario

        TypeUsers admin = new TypeUsers("3", "Administrador");
        TypeUsers cliente = new TypeUsers("4", "Cliente");

        // Agregar usuarios iniciales
        listUsers.add(new User("123456789", "Juan Pérez", "juan", "password123",admin));
        listUsers.add(new User("987654321", "Ana López", "ana", "pass456", cliente));
    }

   //Registrar usuarios nuevos
    public void registerUser(User user) {
        listUsers.add(user);
    }

    public List<User> getUsers() {
        return listUsers;
    }

    public String validateUser(String login, String password) {
        for (User user : listUsers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user.getTypeUsers().getDescription();  // Retorna la descripción del tipo de usuario
            }
        }
        return null; // No se encontró ningún usuario que coincida
    }


}
