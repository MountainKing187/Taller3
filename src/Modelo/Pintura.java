package Modelo;

public class Pintura {
    private int codPintura;
    private int anio;
    private int valor;
    private String titulo;
    private String estado;
    private int codAutor;

    public int getAnio() {
        return anio;
    }

    public String getEstado() {
        return estado;
    }
    
    public int getCodAutor() {
        return codAutor;
    }
    
    public int getCodPintura() {
        return codPintura;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getValor() {
        return valor;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCodAutor(int codAutor) {
        this.codAutor = codAutor;
    }

    public void setCodPintura(int codPintura) {
        this.codPintura = codPintura;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
