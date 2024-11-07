package com.tgp_ntt.clientaccountmanager;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Cliente {
    private String nombre;
    private String apellido;
    private String dni; // DNI debe ser único
    private String email;
    private List<CuentaBancaria> cuentas;

    public Cliente(String nombre, String apellido, String dni, String email) {
        if (nombre == null || apellido == null || dni == null || email == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
    public List<CuentaBancaria> getCuentas() { return cuentas; }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        this.email = email;
    }

    // Métodos adicionales
    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }
}
