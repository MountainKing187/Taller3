package Modelo;

import java.time.LocalDateTime;

public class Venta {
    private int codVenta, codPintura, montoTotal;
    private LocalDateTime fechaVenta;
    private String rutCliente;

    public int getCodPintura() { return codPintura; }
    public int getCodVenta() { return codVenta; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public int getMontoTotal() { return montoTotal; }
    public String getRutCliente() { return rutCliente; }

    public void setCodPintura(int codPintura) { this.codPintura = codPintura; }
    public void setCodVenta(int codVenta) { this.codVenta = codVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) {this.fechaVenta = fechaVenta; }
    public void setMontoTotal(int montoTotal) { this.montoTotal = montoTotal; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }
}
