package Modelo;

public class Pintura {
    private int codPintura;
    private int anio;
    private int valor;
    private String titulo;
    private int codAutor;
    private String rutPrestamista;
    private int codSala;

    public Pintura(int codPintura, int anio, int valor, String titulo,
                   int codAutor, String rutPrestamista, int codSala) {
        this.codPintura = codPintura;
        this.anio = anio;
        this.valor = valor;
        this.titulo = titulo;
        this.codAutor = codAutor;
        this.rutPrestamista = rutPrestamista;
        this.codSala = codSala;
    }   
    public int getAnio() {
        return anio;
    }
    
    public int getCodAutor() {
        return codAutor;
    }
    
    public int getCodPintura() {
        return codPintura;
    }

    public int getCodSala() {
        return codSala;
    }

    public String getRutPrestamista() {
        return rutPrestamista;
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

    public void setCodSala(int codSala) {
        this.codSala = codSala;
    }

    public void setRutPrestamista(String rutPrestamista) {
        this.rutPrestamista = rutPrestamista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
