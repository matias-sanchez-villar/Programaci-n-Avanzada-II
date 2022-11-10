package com.example.donapp.Entity;

import com.example.donapp.Enums.TipoSangre;

public class HistorialMedico extends EntidadBase{
    private TipoSangre tipoSangre;
    private String peso;
    private String altura;
    private boolean tatuajes;
    private boolean enferTranSexual;
    private boolean usoDrogras;
    private boolean usoMedicamentos;
    private boolean resMinimas;
    private boolean resMaximas;

    public HistorialMedico(){}

    public HistorialMedico(TipoSangre tipoSangre, String peso, String altura, boolean tatuajes, boolean enferTranSexual, boolean usoDrogras, boolean usoMedicamentos, boolean resMinimas, boolean resMaximas) {
        super();
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.altura = altura;
        this.tatuajes = tatuajes;
        this.enferTranSexual = enferTranSexual;
        this.usoDrogras = usoDrogras;
        this.usoMedicamentos = usoMedicamentos;
        this.resMinimas = resMinimas;
        this.resMaximas = resMaximas;
    }

    public HistorialMedico(int id, TipoSangre tipoSangre, String peso, String altura, boolean tatuajes, boolean enferTranSexual, boolean usoDrogras, boolean usoMedicamentos, boolean resMinimas, boolean resMaximas) {
        super(id);
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.altura = altura;
        this.tatuajes = tatuajes;
        this.enferTranSexual = enferTranSexual;
        this.usoDrogras = usoDrogras;
        this.usoMedicamentos = usoMedicamentos;
        this.resMinimas = resMinimas;
        this.resMaximas = resMaximas;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public boolean isTatuajes() {
        return tatuajes;
    }

    public void setTatuajes(boolean tatuajes) {
        this.tatuajes = tatuajes;
    }

    public boolean isEnferTranSexual() {
        return enferTranSexual;
    }

    public void setEnferTranSexual(boolean enferTranSexual) {
        this.enferTranSexual = enferTranSexual;
    }

    public boolean isUsoDrogras() {
        return usoDrogras;
    }

    public void setUsoDrogras(boolean usoDrogras) {
        this.usoDrogras = usoDrogras;
    }

    public boolean isUsoMedicamentos() {
        return usoMedicamentos;
    }

    public void setUsoMedicamentos(boolean usoMedicamentos) {
        this.usoMedicamentos = usoMedicamentos;
    }

    public boolean isResMinimas() {
        return resMinimas;
    }

    public void setResMinimas(boolean resMinimas) {
        this.resMinimas = resMinimas;
    }

    public boolean isResMaximas() {
        return resMaximas;
    }

    public void setResMaximas(boolean resMaximas) {
        this.resMaximas = resMaximas;
    }
}
