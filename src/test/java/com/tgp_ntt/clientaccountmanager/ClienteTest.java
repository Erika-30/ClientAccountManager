package com.tgp_ntt.clientaccountmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testRegistrarClienteConDatosValidos() {
        Cliente cliente = new Cliente("John", "Doe", "12345678", "johndoe@example.com");
        assertEquals("John", cliente.getNombre());
        assertEquals("Doe", cliente.getApellido());
        assertEquals("12345678", cliente.getDni());
        assertEquals("johndoe@example.com", cliente.getEmail());
    }

    @Test
    public void testRegistrarClienteConEmailInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("John", "Doe", "12345678", "invalid-email");
        });
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    public void testAgregarCuenta() {
        Cliente cliente = new Cliente("Jane", "Doe", "87654321", "janedoe@example.com");
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        cliente.agregarCuenta(cuenta);
        assertEquals(1, cliente.getCuentas().size());
        assertEquals(cuenta, cliente.getCuentas().get(0));
    }
}