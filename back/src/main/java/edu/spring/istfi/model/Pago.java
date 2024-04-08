package edu.spring.istfi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate fecha;
    private double monto;
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;
    @OneToOne(mappedBy = "pago")
    private Venta venta;

    public Pago() {
    }

    public Pago(int id, LocalDate fecha, double monto, TipoPago tipoPago, Venta venta) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.venta = venta;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
