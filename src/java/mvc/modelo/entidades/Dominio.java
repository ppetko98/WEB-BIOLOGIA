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
@Table(name = "dominio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dominio.findAll", query = "SELECT d FROM Dominio d")
    , @NamedQuery(name = "Dominio.findByIdDominio", query = "SELECT d FROM Dominio d WHERE d.idDominio = :idDominio")
    , @NamedQuery(name = "Dominio.findByDominioName", query = "SELECT d FROM Dominio d WHERE d.dominioName = :dominioName")
    , @NamedQuery(name = "Dominio.findByAutor", query = "SELECT d FROM Dominio d WHERE d.autor = :autor")
    , @NamedQuery(name = "Dominio.findByDescripcion", query = "SELECT d FROM Dominio d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Dominio.findByReferences", query = "SELECT d FROM Dominio d WHERE d.references = :references")
    , @NamedQuery(name = "Dominio.findByLastUpdate", query = "SELECT d FROM Dominio d WHERE d.lastUpdate = :lastUpdate")})
public class Dominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dominio")
    private Integer idDominio;
    @Basic(optional = false)
    @Column(name = "dominio_name")
    private String dominioName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dominio")
    private Collection<Nomenclatura> nomenclaturaCollection;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;

    public Dominio() {
    }

    public Dominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public Dominio(Integer idDominio, String dominioName, String autor, String descripcion, String references, Date lastUpdate) {
        this.idDominio = idDominio;
        this.dominioName = dominioName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.references = references;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Integer idDominio) {
        this.idDominio = idDominio;
    }

    public String getDominioName() {
        return dominioName;
    }

    public void setDominioName(String dominioName) {
        this.dominioName = dominioName;
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
        hash += (idDominio != null ? idDominio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dominio)) {
            return false;
        }
        Dominio other = (Dominio) object;
        if ((this.idDominio == null && other.idDominio != null) || (this.idDominio != null && !this.idDominio.equals(other.idDominio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Dominio[ idDominio=" + idDominio + " ]";
    }
    
}
