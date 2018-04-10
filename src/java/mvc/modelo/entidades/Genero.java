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
@Table(name = "genero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g")
    , @NamedQuery(name = "Genero.findByIdGenero", query = "SELECT g FROM Genero g WHERE g.idGenero = :idGenero")
    , @NamedQuery(name = "Genero.findByGeneroName", query = "SELECT g FROM Genero g WHERE g.generoName = :generoName")
    , @NamedQuery(name = "Genero.findByAutor", query = "SELECT g FROM Genero g WHERE g.autor = :autor")
    , @NamedQuery(name = "Genero.findByDescripcion", query = "SELECT g FROM Genero g WHERE g.descripcion = :descripcion")
    , @NamedQuery(name = "Genero.findByReferences", query = "SELECT g FROM Genero g WHERE g.references = :references")
    , @NamedQuery(name = "Genero.findByLastUpdate", query = "SELECT g FROM Genero g WHERE g.lastUpdate = :lastUpdate")})
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero")
    private Integer idGenero;
    @Basic(optional = false)
    @Column(name = "genero_name")
    private String generoName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genero")
    private Collection<Nomenclatura> nomenclaturaCollection;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;

    public Genero() {
    }

    public Genero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Genero(Integer idGenero, String generoName, String autor, String descripcion, String references, Date lastUpdate) {
        this.idGenero = idGenero;
        this.generoName = generoName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.references = references;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGeneroName() {
        return generoName;
    }

    public void setGeneroName(String generoName) {
        this.generoName = generoName;
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
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Genero[ idGenero=" + idGenero + " ]";
    }
    
}
