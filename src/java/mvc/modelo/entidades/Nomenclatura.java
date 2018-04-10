/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ppetk
 */
@Entity
@Table(name = "nomenclatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomenclatura.findAll", query = "SELECT n FROM Nomenclatura n")
    , @NamedQuery(name = "Nomenclatura.findByIdEspecie", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idEspecie = :idEspecie")
    , @NamedQuery(name = "Nomenclatura.findByIdGenero", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idGenero = :idGenero")
    , @NamedQuery(name = "Nomenclatura.findByIdClase", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idClase = :idClase")
    , @NamedQuery(name = "Nomenclatura.findByIdFilo", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idFilo = :idFilo")
    , @NamedQuery(name = "Nomenclatura.findByIdOrden", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idOrden = :idOrden")
    , @NamedQuery(name = "Nomenclatura.findByIdFamilia", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idFamilia = :idFamilia")
    , @NamedQuery(name = "Nomenclatura.findByIdDominio", query = "SELECT n FROM Nomenclatura n WHERE n.nomenclaturaPK.idDominio = :idDominio")})
public class Nomenclatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NomenclaturaPK nomenclaturaPK;
    @JoinColumn(name = "id_especie", referencedColumnName = "id_especie", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Especie especie;
    @JoinColumn(name = "id_clase", referencedColumnName = "id_clase", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clase clase;
    @JoinColumn(name = "id_dominio", referencedColumnName = "id_dominio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dominio dominio;
    @JoinColumn(name = "id_familia", referencedColumnName = "id_familia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Familia familia;
    @JoinColumn(name = "id_filo", referencedColumnName = "id_filo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Filo filo;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genero genero;
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;

    public Nomenclatura() {
    }

    public Nomenclatura(NomenclaturaPK nomenclaturaPK) {
        this.nomenclaturaPK = nomenclaturaPK;
    }

    public Nomenclatura(int idEspecie, int idGenero, int idClase, int idFilo, int idOrden, int idFamilia, int idDominio) {
        this.nomenclaturaPK = new NomenclaturaPK(idEspecie, idGenero, idClase, idFilo, idOrden, idFamilia, idDominio);
    }

    public NomenclaturaPK getNomenclaturaPK() {
        return nomenclaturaPK;
    }

    public void setNomenclaturaPK(NomenclaturaPK nomenclaturaPK) {
        this.nomenclaturaPK = nomenclaturaPK;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Filo getFilo() {
        return filo;
    }

    public void setFilo(Filo filo) {
        this.filo = filo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomenclaturaPK != null ? nomenclaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomenclatura)) {
            return false;
        }
        Nomenclatura other = (Nomenclatura) object;
        if ((this.nomenclaturaPK == null && other.nomenclaturaPK != null) || (this.nomenclaturaPK != null && !this.nomenclaturaPK.equals(other.nomenclaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Nomenclatura[ nomenclaturaPK=" + nomenclaturaPK + " ]";
    }
    
}
