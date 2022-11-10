package com.example.donapp.Entity;

import com.example.donapp.Enums.TipoSangre;

import java.util.Date;

public class HistorialMedico extends EntidadEstadoBase{
    private TipoSangre tipoSangre;
    private int peso;
    private double altura;
    private Date ultimaDonacion;
    private boolean tatuajes;
    private boolean vacunaAlergia;
    private boolean examenSangre;
    private boolean revisionMedica;
    private boolean tratamientoDental;
    private boolean endoscopia;
    boolean embarazo;
    boolean enfermedaCronica;
    boolean operacion;
    boolean viaje;
    boolean anemia;
    boolean accidenteVascular;
    boolean usaMedicamentos;
    boolean hepatitis;
    Usuario usuario;

    public HistorialMedico(){}

    public HistorialMedico(int id){
        super(id);
    }

    public HistorialMedico(TipoSangre tipoSangre,
                           int peso,
                           double altura,
                           boolean tatuajes,
                           boolean vacunaAlergia,
                           boolean examenSangre,
                           boolean revisionMedica,
                           boolean tratamientoDental,
                           boolean endoscopia,
                           boolean embarazo,
                           boolean enfermedaCronica,
                           boolean operacion,
                           boolean viaje,
                           boolean accidenteVascular,
                           boolean usaMedicamentos,
                           boolean hepatitis,
                           Usuario usuario){
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.altura = altura;
        this.tatuajes = tatuajes;
        this.vacunaAlergia = vacunaAlergia;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getPesoToString() {
        return String.valueOf(peso) + " kg";
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getAltura(){
        return altura;
    }

    public String getAlturaToString() {
        return String.valueOf(altura) + " mts";
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public boolean isTatuajes() {
        return tatuajes;
    }

    public void setTatuajes(boolean tatuajes) {
        this.tatuajes = tatuajes;
    }

    public boolean isVacunaAlergia() {
        return vacunaAlergia;
    }

    public void setVacunaAlergia(boolean vacunaAlergia) {
        this.vacunaAlergia = vacunaAlergia;
    }

    public boolean isExamenSangre() {
        return examenSangre;
    }

    public void setExamenSangre(boolean examenSangre) {
        this.examenSangre = examenSangre;
    }

    public boolean isRevisionMedica() {
        return revisionMedica;
    }

    public void setRevisionMedica(boolean revisionMedica) {
        this.revisionMedica = revisionMedica;
    }

    public boolean isTratamientoDental() {
        return tratamientoDental;
    }

    public void setTratamientoDental(boolean tratamientoDental) {
        this.tratamientoDental = tratamientoDental;
    }

    public boolean isEndoscopia() {
        return endoscopia;
    }

    public void setEndoscopia(boolean endoscopia) {
        this.endoscopia = endoscopia;
    }

    public boolean isEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(boolean embarazo) {
        this.embarazo = embarazo;
    }

    public boolean isEnfermedaCronica() {
        return enfermedaCronica;
    }

    public void setEnfermedaCronica(boolean enfermedaCronica) {
        this.enfermedaCronica = enfermedaCronica;
    }

    public boolean isOperacion() {
        return operacion;
    }

    public void setOperacion(boolean operacion) {
        this.operacion = operacion;
    }

    public boolean isViaje() {
        return viaje;
    }

    public void setViaje(boolean viaje) {
        this.viaje = viaje;
    }

    public boolean isAccidenteVascular() {
        return accidenteVascular;
    }

    public void setAccidenteVascular(boolean accidenteVascular) {
        this.accidenteVascular = accidenteVascular;
    }

    public boolean isUsaMedicamentos() {
        return usaMedicamentos;
    }

    public void setUsaMedicamentos(boolean usaMedicamentos) {
        this.usaMedicamentos = usaMedicamentos;
    }

    public boolean isHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getUltimaDonacion() {
        return ultimaDonacion;
    }

    public void setUltimaDonacion(Date ultimaDonacion) {
        this.ultimaDonacion = ultimaDonacion;
    }

    public boolean isAnemia() {
        return anemia;
    }

    public void setAnemia(boolean anemia) {
        this.anemia = anemia;
    }
}
