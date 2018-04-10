/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mvc.controlador.exceptions.NonexistentEntityException;
import mvc.controlador.exceptions.PreexistingEntityException;
import mvc.modelo.entidades.Especie;
import mvc.modelo.entidades.Clase;
import mvc.modelo.entidades.Dominio;
import mvc.modelo.entidades.Familia;
import mvc.modelo.entidades.Filo;
import mvc.modelo.entidades.Genero;
import mvc.modelo.entidades.Nomenclatura;
import mvc.modelo.entidades.NomenclaturaPK;
import mvc.modelo.entidades.Orden;

/**
 *
 * @author ppetk
 */
public class NomenclaturaJpaController implements Serializable {

    public NomenclaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nomenclatura nomenclatura) throws PreexistingEntityException, Exception {
        if (nomenclatura.getNomenclaturaPK() == null) {
            nomenclatura.setNomenclaturaPK(new NomenclaturaPK());
        }
        nomenclatura.getNomenclaturaPK().setIdEspecie(nomenclatura.getEspecie().getIdEspecie());
        nomenclatura.getNomenclaturaPK().setIdOrden(nomenclatura.getOrden().getIdOrden());
        nomenclatura.getNomenclaturaPK().setIdFamilia(nomenclatura.getFamilia().getIdFamilia());
        nomenclatura.getNomenclaturaPK().setIdGenero(nomenclatura.getGenero().getIdGenero());
        nomenclatura.getNomenclaturaPK().setIdClase(nomenclatura.getClase().getIdClase());
        nomenclatura.getNomenclaturaPK().setIdDominio(nomenclatura.getDominio().getIdDominio());
        nomenclatura.getNomenclaturaPK().setIdFilo(nomenclatura.getFilo().getIdFilo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especie especie = nomenclatura.getEspecie();
            if (especie != null) {
                especie = em.getReference(especie.getClass(), especie.getIdEspecie());
                nomenclatura.setEspecie(especie);
            }
            Clase clase = nomenclatura.getClase();
            if (clase != null) {
                clase = em.getReference(clase.getClass(), clase.getIdClase());
                nomenclatura.setClase(clase);
            }
            Dominio dominio = nomenclatura.getDominio();
            if (dominio != null) {
                dominio = em.getReference(dominio.getClass(), dominio.getIdDominio());
                nomenclatura.setDominio(dominio);
            }
            Familia familia = nomenclatura.getFamilia();
            if (familia != null) {
                familia = em.getReference(familia.getClass(), familia.getIdFamilia());
                nomenclatura.setFamilia(familia);
            }
            Filo filo = nomenclatura.getFilo();
            if (filo != null) {
                filo = em.getReference(filo.getClass(), filo.getIdFilo());
                nomenclatura.setFilo(filo);
            }
            Genero genero = nomenclatura.getGenero();
            if (genero != null) {
                genero = em.getReference(genero.getClass(), genero.getIdGenero());
                nomenclatura.setGenero(genero);
            }
            Orden orden = nomenclatura.getOrden();
            if (orden != null) {
                orden = em.getReference(orden.getClass(), orden.getIdOrden());
                nomenclatura.setOrden(orden);
            }
            em.persist(nomenclatura);
            if (especie != null) {
                especie.getNomenclaturaCollection().add(nomenclatura);
                especie = em.merge(especie);
            }
            if (clase != null) {
                clase.getNomenclaturaCollection().add(nomenclatura);
                clase = em.merge(clase);
            }
            if (dominio != null) {
                dominio.getNomenclaturaCollection().add(nomenclatura);
                dominio = em.merge(dominio);
            }
            if (familia != null) {
                familia.getNomenclaturaCollection().add(nomenclatura);
                familia = em.merge(familia);
            }
            if (filo != null) {
                filo.getNomenclaturaCollection().add(nomenclatura);
                filo = em.merge(filo);
            }
            if (genero != null) {
                genero.getNomenclaturaCollection().add(nomenclatura);
                genero = em.merge(genero);
            }
            if (orden != null) {
                orden.getNomenclaturaCollection().add(nomenclatura);
                orden = em.merge(orden);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNomenclatura(nomenclatura.getNomenclaturaPK()) != null) {
                throw new PreexistingEntityException("Nomenclatura " + nomenclatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nomenclatura nomenclatura) throws NonexistentEntityException, Exception {
        nomenclatura.getNomenclaturaPK().setIdEspecie(nomenclatura.getEspecie().getIdEspecie());
        nomenclatura.getNomenclaturaPK().setIdOrden(nomenclatura.getOrden().getIdOrden());
        nomenclatura.getNomenclaturaPK().setIdFamilia(nomenclatura.getFamilia().getIdFamilia());
        nomenclatura.getNomenclaturaPK().setIdGenero(nomenclatura.getGenero().getIdGenero());
        nomenclatura.getNomenclaturaPK().setIdClase(nomenclatura.getClase().getIdClase());
        nomenclatura.getNomenclaturaPK().setIdDominio(nomenclatura.getDominio().getIdDominio());
        nomenclatura.getNomenclaturaPK().setIdFilo(nomenclatura.getFilo().getIdFilo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nomenclatura persistentNomenclatura = em.find(Nomenclatura.class, nomenclatura.getNomenclaturaPK());
            Especie especieOld = persistentNomenclatura.getEspecie();
            Especie especieNew = nomenclatura.getEspecie();
            Clase claseOld = persistentNomenclatura.getClase();
            Clase claseNew = nomenclatura.getClase();
            Dominio dominioOld = persistentNomenclatura.getDominio();
            Dominio dominioNew = nomenclatura.getDominio();
            Familia familiaOld = persistentNomenclatura.getFamilia();
            Familia familiaNew = nomenclatura.getFamilia();
            Filo filoOld = persistentNomenclatura.getFilo();
            Filo filoNew = nomenclatura.getFilo();
            Genero generoOld = persistentNomenclatura.getGenero();
            Genero generoNew = nomenclatura.getGenero();
            Orden ordenOld = persistentNomenclatura.getOrden();
            Orden ordenNew = nomenclatura.getOrden();
            if (especieNew != null) {
                especieNew = em.getReference(especieNew.getClass(), especieNew.getIdEspecie());
                nomenclatura.setEspecie(especieNew);
            }
            if (claseNew != null) {
                claseNew = em.getReference(claseNew.getClass(), claseNew.getIdClase());
                nomenclatura.setClase(claseNew);
            }
            if (dominioNew != null) {
                dominioNew = em.getReference(dominioNew.getClass(), dominioNew.getIdDominio());
                nomenclatura.setDominio(dominioNew);
            }
            if (familiaNew != null) {
                familiaNew = em.getReference(familiaNew.getClass(), familiaNew.getIdFamilia());
                nomenclatura.setFamilia(familiaNew);
            }
            if (filoNew != null) {
                filoNew = em.getReference(filoNew.getClass(), filoNew.getIdFilo());
                nomenclatura.setFilo(filoNew);
            }
            if (generoNew != null) {
                generoNew = em.getReference(generoNew.getClass(), generoNew.getIdGenero());
                nomenclatura.setGenero(generoNew);
            }
            if (ordenNew != null) {
                ordenNew = em.getReference(ordenNew.getClass(), ordenNew.getIdOrden());
                nomenclatura.setOrden(ordenNew);
            }
            nomenclatura = em.merge(nomenclatura);
            if (especieOld != null && !especieOld.equals(especieNew)) {
                especieOld.getNomenclaturaCollection().remove(nomenclatura);
                especieOld = em.merge(especieOld);
            }
            if (especieNew != null && !especieNew.equals(especieOld)) {
                especieNew.getNomenclaturaCollection().add(nomenclatura);
                especieNew = em.merge(especieNew);
            }
            if (claseOld != null && !claseOld.equals(claseNew)) {
                claseOld.getNomenclaturaCollection().remove(nomenclatura);
                claseOld = em.merge(claseOld);
            }
            if (claseNew != null && !claseNew.equals(claseOld)) {
                claseNew.getNomenclaturaCollection().add(nomenclatura);
                claseNew = em.merge(claseNew);
            }
            if (dominioOld != null && !dominioOld.equals(dominioNew)) {
                dominioOld.getNomenclaturaCollection().remove(nomenclatura);
                dominioOld = em.merge(dominioOld);
            }
            if (dominioNew != null && !dominioNew.equals(dominioOld)) {
                dominioNew.getNomenclaturaCollection().add(nomenclatura);
                dominioNew = em.merge(dominioNew);
            }
            if (familiaOld != null && !familiaOld.equals(familiaNew)) {
                familiaOld.getNomenclaturaCollection().remove(nomenclatura);
                familiaOld = em.merge(familiaOld);
            }
            if (familiaNew != null && !familiaNew.equals(familiaOld)) {
                familiaNew.getNomenclaturaCollection().add(nomenclatura);
                familiaNew = em.merge(familiaNew);
            }
            if (filoOld != null && !filoOld.equals(filoNew)) {
                filoOld.getNomenclaturaCollection().remove(nomenclatura);
                filoOld = em.merge(filoOld);
            }
            if (filoNew != null && !filoNew.equals(filoOld)) {
                filoNew.getNomenclaturaCollection().add(nomenclatura);
                filoNew = em.merge(filoNew);
            }
            if (generoOld != null && !generoOld.equals(generoNew)) {
                generoOld.getNomenclaturaCollection().remove(nomenclatura);
                generoOld = em.merge(generoOld);
            }
            if (generoNew != null && !generoNew.equals(generoOld)) {
                generoNew.getNomenclaturaCollection().add(nomenclatura);
                generoNew = em.merge(generoNew);
            }
            if (ordenOld != null && !ordenOld.equals(ordenNew)) {
                ordenOld.getNomenclaturaCollection().remove(nomenclatura);
                ordenOld = em.merge(ordenOld);
            }
            if (ordenNew != null && !ordenNew.equals(ordenOld)) {
                ordenNew.getNomenclaturaCollection().add(nomenclatura);
                ordenNew = em.merge(ordenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NomenclaturaPK id = nomenclatura.getNomenclaturaPK();
                if (findNomenclatura(id) == null) {
                    throw new NonexistentEntityException("The nomenclatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NomenclaturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nomenclatura nomenclatura;
            try {
                nomenclatura = em.getReference(Nomenclatura.class, id);
                nomenclatura.getNomenclaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomenclatura with id " + id + " no longer exists.", enfe);
            }
            Especie especie = nomenclatura.getEspecie();
            if (especie != null) {
                especie.getNomenclaturaCollection().remove(nomenclatura);
                especie = em.merge(especie);
            }
            Clase clase = nomenclatura.getClase();
            if (clase != null) {
                clase.getNomenclaturaCollection().remove(nomenclatura);
                clase = em.merge(clase);
            }
            Dominio dominio = nomenclatura.getDominio();
            if (dominio != null) {
                dominio.getNomenclaturaCollection().remove(nomenclatura);
                dominio = em.merge(dominio);
            }
            Familia familia = nomenclatura.getFamilia();
            if (familia != null) {
                familia.getNomenclaturaCollection().remove(nomenclatura);
                familia = em.merge(familia);
            }
            Filo filo = nomenclatura.getFilo();
            if (filo != null) {
                filo.getNomenclaturaCollection().remove(nomenclatura);
                filo = em.merge(filo);
            }
            Genero genero = nomenclatura.getGenero();
            if (genero != null) {
                genero.getNomenclaturaCollection().remove(nomenclatura);
                genero = em.merge(genero);
            }
            Orden orden = nomenclatura.getOrden();
            if (orden != null) {
                orden.getNomenclaturaCollection().remove(nomenclatura);
                orden = em.merge(orden);
            }
            em.remove(nomenclatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nomenclatura> findNomenclaturaEntities() {
        return findNomenclaturaEntities(true, -1, -1);
    }

    public List<Nomenclatura> findNomenclaturaEntities(int maxResults, int firstResult) {
        return findNomenclaturaEntities(false, maxResults, firstResult);
    }

    private List<Nomenclatura> findNomenclaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nomenclatura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nomenclatura findNomenclatura(NomenclaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nomenclatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomenclaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nomenclatura> rt = cq.from(Nomenclatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
