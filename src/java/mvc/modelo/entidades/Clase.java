/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ppetk
 */
@Entity
@Table(name = "clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c")
    , @NamedQuery(name = "Clase.findByIdClase", query = "SELECT c FROM Clase c WHERE c.idClase = :idClase")
    , @NamedQuery(name = "Clase.findByClaseName", query = "SELECT c FROM Clase c WHERE c.claseName = :claseName")
    , @NamedQuery(name = "Clase.findByAutor", query = "SELECT c FROM Clase c WHERE c.autor = :autor")
    , @NamedQuery(name = "Clase.findByDescripcion", query = "SELECT c FROM Clase c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Clase.findByReferences", query = "SELECT c FROM Clase c WHERE c.references = :references")
    , @NamedQuery(name = "Clase.findByLastUpdate", query = "SELECT c FROM Clase c WHERE c.lastUpdate = :lastUpdate")})
public class Clase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clase")
    private Integer idClase;
    @Basic(optional = false)
    @Column(name = "clase_name")
    private String claseName;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "References")
    private String references;
    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private Collection<Nomenclatura> nomenclaturaCollection;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;

    public Clase() {
    }

    public Clase(Integer idClase) {
        this.idClase = idClase;
    }

    public Clase(Integer idClase, String claseName, String autor, String descripcion, String references, Date lastUpdate) {
        this.idClase = idClase;
        this.claseName = claseName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.references = references;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getClaseName() {
        return claseName;
    }

    public void setClaseName(String claseName) {
        this.claseName = claseName;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @XmlTransient
    public Collection<Nomenclatura> getNomenclaturaCollection() {
        return nomenclaturaCollection;
    }

    public void setNomenclaturaCollection(Collection<Nomenclatura> nomenclaturaCollection) {
        this.nomenclaturaCollection = nomenclaturaCollection;
    }

    public Imagenes getImagen() {
        return imagen;
    }

    public void setImagen(Imagenes imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clase)) {
            return false;
        }
        Clase other = (Clase) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Clase[ idClase=" + idClase + " ]";
    }
    
}
