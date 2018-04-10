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
@Table(name = "filo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filo.findAll", query = "SELECT f FROM Filo f")
    , @NamedQuery(name = "Filo.findByIdFilo", query = "SELECT f FROM Filo f WHERE f.idFilo = :idFilo")
    , @NamedQuery(name = "Filo.findByFiloName", query = "SELECT f FROM Filo f WHERE f.filoName = :filoName")
    , @NamedQuery(name = "Filo.findByAutor", query = "SELECT f FROM Filo f WHERE f.autor = :autor")
    , @NamedQuery(name = "Filo.findByDescripcion", query = "SELECT f FROM Filo f WHERE f.descripcion = :descripcion")
    , @NamedQuery(name = "Filo.findByReferences", query = "SELECT f FROM Filo f WHERE f.references = :references")
    , @NamedQuery(name = "Filo.findByLastUpdate", query = "SELECT f FROM Filo f WHERE f.lastUpdate = :lastUpdate")})
public class Filo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_filo")
    private Integer idFilo;
    @Basic(optional = false)
    @Column(name = "filo_name")
    private String filoName;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "references")
    private String references;
    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filo")
    private Collection<Nomenclatura> nomenclaturaCollection;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;

    public Filo() {
    }

    public Filo(Integer idFilo) {
        this.idFilo = idFilo;
    }

    public Filo(Integer idFilo, String filoName, String autor, String descripcion, String references, Date lastUpdate) {
        this.idFilo = idFilo;
        this.filoName = filoName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.references = references;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdFilo() {
        return idFilo;
    }

    public void setIdFilo(Integer idFilo) {
        this.idFilo = idFilo;
    }

    public String getFiloName() {
        return filoName;
    }

    public void setFiloName(String filoName) {
        this.filoName = filoName;
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
        hash += (idFilo != null ? idFilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filo)) {
            return false;
        }
        Filo other = (Filo) object;
        if ((this.idFilo == null && other.idFilo != null) || (this.idFilo != null && !this.idFilo.equals(other.idFilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Filo[ idFilo=" + idFilo + " ]";
    }
    
}
