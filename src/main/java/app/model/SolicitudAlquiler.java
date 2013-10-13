/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.Date;

/**
 *
 * @author alumno
 */
public class SolicitudAlquiler {
    
    private long id;
    private Date horaInicio;
    private Date horaFin;
    private Date dia;
    private String servicios;
    private int estado;
    private Socio socio;
    private Campo campo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setIdSocio(Socio socio) {
        this.socio = socio;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setIdCampo(Campo campo) {
        this.campo = campo;
    }
    
    
    
    
}
