/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ppetk
 */
@Entity
@Table(name = "especie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especie.findAll", query = "SELECT e FROM Especie e")
    , @NamedQuery(name = "Especie.findByIdEspecie", query = "SELECT e FROM Especie e WHERE e.idEspecie = :idEspecie")
    , @NamedQuery(name = "Especie.findByEspecieName", query = "SELECT e FROM Especie e WHERE e.especieName = :especieName")
    , @NamedQuery(name = "Especie.findByAutor", query = "SELECT e FROM Especie e WHERE e.autor = :autor")
    , @NamedQuery(name = "Especie.findByDescripcion", query = "SELECT e FROM Especie e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Especie.findByMetabolismo", query = "SELECT e FROM Especie e WHERE e.metabolismo = :metabolismo")
    , @NamedQuery(name = "Especie.findByEcologia", query = "SELECT e FROM Especie e WHERE e.ecologia = :ecologia")
    , @NamedQuery(name = "Especie.findByReferences", query = "SELECT e FROM Especie e WHERE e.references = :references")})
public class Especie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_especie")
    private Integer idEspecie;
    @Basic(optional = false)
    @Column(name = "especie_name")
    private String especieName;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "metabolismo")
    private String metabolismo;
    @Basic(optional = false)
    @Column(name = "ecologia")
    private String ecologia;
    @Basic(optional = false)
    @Column(name = "references")
    private String references;
    @JoinColumn(name = "imagen", referencedColumnName = "id_imagenes")
    @ManyToOne
    private Imagenes imagen;
    @JoinColumn(name = "id_secuencia", referencedColumnName = "id_secuencia")
    @ManyToOne(optional = false)
    private Genetica idSecuencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especie")
    private Collection<Nomenclatura> nomenclaturaCollection;

    public Especie() {
    }

    public Especie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public Especie(Integer idEspecie, String especieName, String autor, String descripcion, String metabolismo, String ecologia, String references) {
        this.idEspecie = idEspecie;
        this.especieName = especieName;
        this.autor = autor;
        this.descripcion = descripcion;
        this.metabolismo = metabolismo;
        this.ecologia = ecologia;
        this.references = references;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getEspecieName() {
        return especieName;
    }

    public void setEspecieName(String especieName) {
        this.especieName = especieName;
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

    public String getMetabolismo() {
        return metabolismo;
    }

    public void setMetabolismo(String metabolismo) {
        this.metabolismo = metabolismo;
    }

    public String getEcologia() {
        return ecologia;
    }

    public void setEcologia(String ecologia) {
        this.ecologia = ecologia;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Imagenes getImagen() {
        return imagen;
    }

    public void setImagen(Imagenes imagen) {
        this.imagen = imagen;
    }

    public Genetica getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(Genetica idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    @XmlTransient
    public Collection<Nomenclatura> getNomenclaturaCollection() {
        return nomenclaturaCollection;
    }

    public void setNomenclaturaCollection(Collection<Nomenclatura> nomenclaturaCollection) {
        this.nomenclaturaCollection = nomenclaturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecie != null ? idEspecie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.idEspecie == null && other.idEspecie != null) || (this.idEspecie != null && !this.idEspecie.equals(other.idEspecie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Especie[ idEspecie=" + idEspecie + " ]";
    }
    
}
