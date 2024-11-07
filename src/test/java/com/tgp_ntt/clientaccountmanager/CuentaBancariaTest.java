import com.tgp_ntt.clientaccountmanager.CuentaBancaria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaBancariaTest {

    @Test
    public void testCrearCuentaConSaldoInicial() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        assertEquals(0.0, cuenta.getSaldo());
        assertEquals("1234567890", cuenta.getNumeroCuenta());
        assertEquals(CuentaBancaria.TipoCuenta.AHORROS, cuenta.getTipoCuenta());
    }

    @Test
    public void testDepositarMontoValido() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        cuenta.depositar(500);
        assertEquals(500, cuenta.getSaldo());
    }

    @Test
    public void testDepositarMontoInvalido() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuenta.depositar(-100);
        });
        assertEquals("El monto a depositar debe ser positivo.", exception.getMessage());
    }

    @Test
    public void testRetirarMontoValidoEnCuentaAhorros() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        cuenta.depositar(300);
        cuenta.retirar(100);
        assertEquals(200, cuenta.getSaldo());
    }

    @Test
    public void testRetiroInvalidoEnCuentaAhorrosPorSaldoInsuficiente() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        cuenta.depositar(100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuenta.retirar(200);
        });
        assertEquals("Saldo insuficiente. No se permite saldo negativo en cuentas de ahorros.", exception.getMessage());
    }

    @Test
    public void testRetiroEnCuentaCorrienteConSobregiroPermitido() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.CORRIENTE);
        cuenta.depositar(100);
        cuenta.retirar(600); // Esto debería dejar el saldo en -500
        assertEquals(-500, cuenta.getSaldo());
    }

    @Test
    public void testRetiroEnCuentaCorrienteConSobregiroExcedido() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.CORRIENTE);
        cuenta.depositar(100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuenta.retirar(700); // Excede el límite de sobregiro
        });
        assertEquals("Límite de sobregiro excedido. Máximo permitido: -500.00.", exception.getMessage());
    }

    @Test
    public void testConsultarSaldo() {
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", CuentaBancaria.TipoCuenta.AHORROS);
        cuenta.depositar(250);
        assertEquals(250, cuenta.consultarSaldo());
    }
}
