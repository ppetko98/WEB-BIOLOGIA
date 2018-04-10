/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ppetk
 */
@Entity
@Table(name = "imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagenes.findAll", query = "SELECT i FROM Imagenes i")
    , @NamedQuery(name = "Imagenes.findByIdImagenes", query = "SELECT i FROM Imagenes i WHERE i.idImagenes = :idImagenes")
    , @NamedQuery(name = "Imagenes.findByImagen", query = "SELECT i FROM Imagenes i WHERE i.imagen = :imagen")})
public class Imagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagenes")
    private Integer idImagenes;
    @Column(name = "imagen")
    private String imagen;
    @OneToMany(mappedBy = "imagen")
    private Collection<Especie> especieCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Dominio> dominioCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Genero> generoCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Filo> filoCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Orden> ordenCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Familia> familiaCollection;
    @OneToMany(mappedBy = "imagen")
    private Collection<Clase> claseCollection;

    public Imagenes() {
    }

    public Imagenes(Integer idImagenes) {
        this.idImagenes = idImagenes;
    }

    public Integer getIdImagenes() {
        return idImagenes;
    }

    public void setIdImagenes(Integer idImagenes) {
        this.idImagenes = idImagenes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public Collection<Especie> getEspecieCollection() {
        return especieCollection;
    }

    public void setEspecieCollection(Collection<Especie> especieCollection) {
        this.especieCollection = especieCollection;
    }

    @XmlTransient
    public Collection<Dominio> getDominioCollection() {
        return dominioCollection;
    }

    public void setDominioCollection(Collection<Dominio> dominioCollection) {
        this.dominioCollection = dominioCollection;
    }

    @XmlTransient
    public Collection<Genero> getGeneroCollection() {
        return generoCollection;
    }

    public void setGeneroCollection(Collection<Genero> generoCollection) {
        this.generoCollection = generoCollection;
    }

    @XmlTransient
    public Collection<Filo> getFiloCollection() {
        return filoCollection;
    }

    public void setFiloCollection(Collection<Filo> filoCollection) {
        this.filoCollection = filoCollection;
    }

    @XmlTransient
    public Collection<Orden> getOrdenCollection() {
        return ordenCollection;
    }

    public void setOrdenCollection(Collection<Orden> ordenCollection) {
        this.ordenCollection = ordenCollection;
    }

    @XmlTransient
    public Collection<Familia> getFamiliaCollection() {
        return familiaCollection;
    }

    public void setFamiliaCollection(Collection<Familia> familiaCollection) {
        this.familiaCollection = familiaCollection;
    }

    @XmlTransient
    public Collection<Clase> getClaseCollection() {
        return claseCollection;
    }

    public void setClaseCollection(Collection<Clase> claseCollection) {
        this.claseCollection = claseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagenes != null ? idImagenes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenes)) {
            return false;
        }
        Imagenes other = (Imagenes) object;
        if ((this.idImagenes == null && other.idImagenes != null) || (this.idImagenes != null && !this.idImagenes.equals(other.idImagenes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Imagenes[ idImagenes=" + idImagenes + " ]";
    }
    
}
