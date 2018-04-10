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
import javax.persistence.Lob;
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
@Table(name = "genetica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genetica.findAll", query = "SELECT g FROM Genetica g")
    , @NamedQuery(name = "Genetica.findByIdSecuencia", query = "SELECT g FROM Genetica g WHERE g.idSecuencia = :idSecuencia")
    , @NamedQuery(name = "Genetica.findByEsGenomicoPlasmido", query = "SELECT g FROM Genetica g WHERE g.esGenomicoPlasmido = :esGenomicoPlasmido")
    , @NamedQuery(name = "Genetica.findByLongitud", query = "SELECT g FROM Genetica g WHERE g.longitud = :longitud")
    , @NamedQuery(name = "Genetica.findByTopologia", query = "SELECT g FROM Genetica g WHERE g.topologia = :topologia")
    , @NamedQuery(name = "Genetica.findByLastUpdate", query = "SELECT g FROM Genetica g WHERE g.lastUpdate = :lastUpdate")})
public class Genetica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_secuencia")
    private Integer idSecuencia;
    @Basic(optional = false)
    @Column(name = "es_genomico_plasmido")
    private boolean esGenomicoPlasmido;
    @Lob
    @Column(name = "fasta")
    private byte[] fasta;
    @Basic(optional = false)
    @Column(name = "longitud")
    private int longitud;
    @Basic(optional = false)
    @Column(name = "topologia")
    private String topologia;
    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSecuencia")
    private Collection<Especie> especieCollection;

    public Genetica() {
    }

    public Genetica(Integer idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public Genetica(Integer idSecuencia, boolean esGenomicoPlasmido, int longitud, String topologia, Date lastUpdate) {
        this.idSecuencia = idSecuencia;
        this.esGenomicoPlasmido = esGenomicoPlasmido;
        this.longitud = longitud;
        this.topologia = topologia;
        this.lastUpdate = lastUpdate;
    }

    public Integer getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(Integer idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public boolean getEsGenomicoPlasmido() {
        return esGenomicoPlasmido;
    }

    public void setEsGenomicoPlasmido(boolean esGenomicoPlasmido) {
        this.esGenomicoPlasmido = esGenomicoPlasmido;
    }

    public byte[] getFasta() {
        return fasta;
    }

    public void setFasta(byte[] fasta) {
        this.fasta = fasta;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getTopologia() {
        return topologia;
    }

    public void setTopologia(String topologia) {
        this.topologia = topologia;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @XmlTransient
    public Collection<Especie> getEspecieCollection() {
        return especieCollection;
    }

    public void setEspecieCollection(Collection<Especie> especieCollection) {
        this.especieCollection = especieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSecuencia != null ? idSecuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genetica)) {
            return false;
        }
        Genetica other = (Genetica) object;
        if ((this.idSecuencia == null && other.idSecuencia != null) || (this.idSecuencia != null && !this.idSecuencia.equals(other.idSecuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.modelo.entidades.Genetica[ idSecuencia=" + idSecuencia + " ]";
    }
    
}
