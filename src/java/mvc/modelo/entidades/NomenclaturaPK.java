/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ppetk
 */
@Embeddable
public class NomenclaturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_especie")
    private int idEspecie;
    @Basic(optional = false)
    @Column(name = "id_genero")
    private int idGenero;
    @Basic(optional = false)
    @Column(name = "id_clase")
    private int idClase;
    @Basic(optional = false)
    @Column(name = "id_filo")
    private int idFilo;
    @Basic(optional = false)
    @Column(name = "id_orden")
    private int idOrden;
    @Basic(optional = false)
    @Column(name = "id_familia")
    private int idFamilia;
    @Basic(optional = false)
    @Column(name = "id_dominio")
    private int idDominio;

    public NomenclaturaPK() {
    }

    public NomenclaturaPK(int idEspecie, int idGenero, int idClase, int idFilo, int idOrden, int idFamilia, int idDominio) {
        this.idEspecie = idEspecie;
        this.idGenero = idGenero;
        this.idClase = idClase;
        this.idFilo = idFilo;
        this.idOrden = idOrden;
        this.idFamilia = idFamilia;
        this.idDominio = idDominio;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdFilo() {
        return idFilo;
    }

    public void setIdFilo(int idFilo) {
        this.idFilo = idFilo;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public int getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(int idDominio) {
        this.idDominio = idDominio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEspecie;
        hash += (int) idGenero;
        hash += (int) idClase;
        hash += (int) idFilo;
        hash += (int) idOrden;
        hash += (int) idFamilia;
        hash += (int) idDominio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomenclaturaPK)) {
            return false;
        }
        NomenclaturaPK other = (NomenclaturaPK) object;
        if (this.idEspecie != other.idEspecie) {
            return false;
        }
        if (this.idGenero != other.idGenero) {
            return false;
        }
        if (this.idClase != other.idClase) {
            return false;
        }
        if (this.idFilo != other.idFilo) {
            return false;
        }
        if (this.idOrden != other.idOrden) {
            return false;
        }
        if (this.idFamilia != other.idFamilia) {
            return false;
        }
        if (this.idDominio != other.idDominio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.NomenclaturaPK[ idEspecie=" + idEspecie + ", idGenero=" + idGenero + ", idClase=" + idClase + ", idFilo=" + idFilo + ", idOrden=" + idOrden + ", idFamilia=" + idFamilia + ", idDominio=" + idDominio + " ]";
    }
    
}
