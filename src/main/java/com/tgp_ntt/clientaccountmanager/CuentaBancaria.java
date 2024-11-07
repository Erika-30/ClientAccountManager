package com.tgp_ntt.clientaccountmanager;
public class CuentaBancaria {
    private String numeroCuenta; // Número de cuenta único
    private double saldo;
    private TipoCuenta tipoCuenta;

    public enum TipoCuenta {
        AHORROS, CORRIENTE
    }

    public CuentaBancaria(String numeroCuenta, TipoCuenta tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0; // Saldo inicializado en 0.0
        this.tipoCuenta = tipoCuenta;
    }

    // Getters y Setters
    public String getNumeroCuenta() { return numeroCuenta; }
    public double getSaldo() { return saldo; }
    public TipoCuenta getTipoCuenta() { return tipoCuenta; }

    // Métodos de negocio
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (tipoCuenta == TipoCuenta.AHORROS && saldo - monto < 0) {
            throw new IllegalArgumentException("Saldo insuficiente. No se permite saldo negativo en cuentas de ahorros.");
        }
        if (tipoCuenta == TipoCuenta.CORRIENTE && saldo - monto < -500) {
            throw new IllegalArgumentException("Límite de sobregiro excedido. Máximo permitido: -500.00.");
        }
        saldo -= monto;
    }

    public double consultarSaldo() {
        return saldo;
    }
}
