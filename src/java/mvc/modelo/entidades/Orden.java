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
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o")
    , @NamedQuery(name = "Orden.findByIdOrden", query = "SELECT o FROM Orden o WHERE o.idOrden = :idOrden")
    , @NamedQuery(name = "Orden.findByOrdenName", query = "SELECT o FROM Orden o WHERE o.ordenName = :ordenName")
    , @NamedQuery(name = "Orden.findByAutor", query = "SELECT o FROM Orden o WHERE o.autor = :autor")
    , @NamedQuery(name = "Orden.findByDescripcion", query = "SELECT o FROM Orden o WHERE o.descripcion = :descripcion")
    , @NamedQuery(name = "Orden.findByReferences", query = "SELECT o FROM Orden o WHERE o.references = :references")
    , @NamedQuery(name = "Orden.findByLastUpdate", query = "SELECT o FROM Orden o WHERE o.lastUpdate = :lastUpdate")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden")
    private Integer idOrden;
    @Basic(optional = false)
    @Column(name = "orden_name")
    private String ordenName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private Collection<Nomenclatura> nomenclaturaCollection;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;

    public Orden() {
    }

    public Orden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Orden(Integer idOrden, String ordenName, String autor, String descripcion, String references, Date lastUpdate) {
        this.idOrden = idOrden;
        this.ordenName = ordenName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.references = references;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public String getOrdenName() {
        return ordenName;
    }

    public void setOrdenName(String ordenName) {
        this.ordenName = ordenName;
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
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Orden[ idOrden=" + idOrden + " ]";
    }
    
}
