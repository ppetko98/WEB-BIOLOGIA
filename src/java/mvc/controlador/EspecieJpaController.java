/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mvc.modelo.entidades.Imagenes;
import mvc.modelo.entidades.Genetica;
import mvc.modelo.entidades.Nomenclatura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mvc.controlador.exceptions.IllegalOrphanException;
import mvc.controlador.exceptions.NonexistentEntityException;
import mvc.modelo.entidades.Especie;

/**
 *
 * @author ppetk
 */
public class EspecieJpaController implements Serializable {

    public EspecieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especie especie) {
        if (especie.getNomenclaturaCollection() == null) {
            especie.setNomenclaturaCollection(new ArrayList<Nomenclatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagenes imagen = especie.getImagen();
            if (imagen != null) {
                imagen = em.getReference(imagen.getClass(), imagen.getIdImagenes());
                especie.setImagen(imagen);
            }
            Genetica idSecuencia = especie.getIdSecuencia();
            if (idSecuencia != null) {
                idSecuencia = em.getReference(idSecuencia.getClass(), idSecuencia.getIdSecuencia());
                especie.setIdSecuencia(idSecuencia);
            }
            Collection<Nomenclatura> attachedNomenclaturaCollection = new ArrayList<Nomenclatura>();
            for (Nomenclatura nomenclaturaCollectionNomenclaturaToAttach : especie.getNomenclaturaCollection()) {
                nomenclaturaCollectionNomenclaturaToAttach = em.getReference(nomenclaturaCollectionNomenclaturaToAttach.getClass(), nomenclaturaCollectionNomenclaturaToAttach.getNomenclaturaPK());
                attachedNomenclaturaCollection.add(nomenclaturaCollectionNomenclaturaToAttach);
            }
            especie.setNomenclaturaCollection(attachedNomenclaturaCollection);
            em.persist(especie);
            if (imagen != null) {
                imagen.getEspecieCollection().add(especie);
                imagen = em.merge(imagen);
            }
            if (idSecuencia != null) {
                idSecuencia.getEspecieCollection().add(especie);
                idSecuencia = em.merge(idSecuencia);
            }
            for (Nomenclatura nomenclaturaCollectionNomenclatura : especie.getNomenclaturaCollection()) {
                Especie oldEspecieOfNomenclaturaCollectionNomenclatura = nomenclaturaCollectionNomenclatura.getEspecie();
                nomenclaturaCollectionNomenclatura.setEspecie(especie);
                nomenclaturaCollectionNomenclatura = em.merge(nomenclaturaCollectionNomenclatura);
                if (oldEspecieOfNomenclaturaCollectionNomenclatura != null) {
                    oldEspecieOfNomenclaturaCollectionNomenclatura.getNomenclaturaCollection().remove(nomenclaturaCollectionNomenclatura);
                    oldEspecieOfNomenclaturaCollectionNomenclatura = em.merge(oldEspecieOfNomenclaturaCollectionNomenclatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especie especie) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especie persistentEspecie = em.find(Especie.class, especie.getIdEspecie());
            Imagenes imagenOld = persistentEspecie.getImagen();
            Imagenes imagenNew = especie.getImagen();
            Genetica idSecuenciaOld = persistentEspecie.getIdSecuencia();
            Genetica idSecuenciaNew = especie.getIdSecuencia();
            Collection<Nomenclatura> nomenclaturaCollectionOld = persistentEspecie.getNomenclaturaCollection();
            Collection<Nomenclatura> nomenclaturaCollectionNew = especie.getNomenclaturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Nomenclatura nomenclaturaCollectionOldNomenclatura : nomenclaturaCollectionOld) {
                if (!nomenclaturaCollectionNew.contains(nomenclaturaCollectionOldNomenclatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nomenclatura " + nomenclaturaCollectionOldNomenclatura + " since its especie field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (imagenNew != null) {
                imagenNew = em.getReference(imagenNew.getClass(), imagenNew.getIdImagenes());
                especie.setImagen(imagenNew);
            }
            if (idSecuenciaNew != null) {
                idSecuenciaNew = em.getReference(idSecuenciaNew.getClass(), idSecuenciaNew.getIdSecuencia());
                especie.setIdSecuencia(idSecuenciaNew);
            }
            Collection<Nomenclatura> attachedNomenclaturaCollectionNew = new ArrayList<Nomenclatura>();
            for (Nomenclatura nomenclaturaCollectionNewNomenclaturaToAttach : nomenclaturaCollectionNew) {
                nomenclaturaCollectionNewNomenclaturaToAttach = em.getReference(nomenclaturaCollectionNewNomenclaturaToAttach.getClass(), nomenclaturaCollectionNewNomenclaturaToAttach.getNomenclaturaPK());
                attachedNomenclaturaCollectionNew.add(nomenclaturaCollectionNewNomenclaturaToAttach);
            }
            nomenclaturaCollectionNew = attachedNomenclaturaCollectionNew;
            especie.setNomenclaturaCollection(nomenclaturaCollectionNew);
            especie = em.merge(especie);
            if (imagenOld != null && !imagenOld.equals(imagenNew)) {
                imagenOld.getEspecieCollection().remove(especie);
                imagenOld = em.merge(imagenOld);
            }
            if (imagenNew != null && !imagenNew.equals(imagenOld)) {
                imagenNew.getEspecieCollection().add(especie);
                imagenNew = em.merge(imagenNew);
            }
            if (idSecuenciaOld != null && !idSecuenciaOld.equals(idSecuenciaNew)) {
                idSecuenciaOld.getEspecieCollection().remove(especie);
                idSecuenciaOld = em.merge(idSecuenciaOld);
            }
            if (idSecuenciaNew != null && !idSecuenciaNew.equals(idSecuenciaOld)) {
                idSecuenciaNew.getEspecieCollection().add(especie);
                idSecuenciaNew = em.merge(idSecuenciaNew);
            }
            for (Nomenclatura nomenclaturaCollectionNewNomenclatura : nomenclaturaCollectionNew) {
                if (!nomenclaturaCollectionOld.contains(nomenclaturaCollectionNewNomenclatura)) {
                    Especie oldEspecieOfNomenclaturaCollectionNewNomenclatura = nomenclaturaCollectionNewNomenclatura.getEspecie();
                    nomenclaturaCollectionNewNomenclatura.setEspecie(especie);
                    nomenclaturaCollectionNewNomenclatura = em.merge(nomenclaturaCollectionNewNomenclatura);
                    if (oldEspecieOfNomenclaturaCollectionNewNomenclatura != null && !oldEspecieOfNomenclaturaCollectionNewNomenclatura.equals(especie)) {
                        oldEspecieOfNomenclaturaCollectionNewNomenclatura.getNomenclaturaCollection().remove(nomenclaturaCollectionNewNomenclatura);
                        oldEspecieOfNomenclaturaCollectionNewNomenclatura = em.merge(oldEspecieOfNomenclaturaCollectionNewNomenclatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especie.getIdEspecie();
                if (findEspecie(id) == null) {
                    throw new NonexistentEntityException("The especie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especie especie;
            try {
                especie = em.getReference(Especie.class, id);
                especie.getIdEspecie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especie with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Nomenclatura> nomenclaturaCollectionOrphanCheck = especie.getNomenclaturaCollection();
            for (Nomenclatura nomenclaturaCollectionOrphanCheckNomenclatura : nomenclaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Especie (" + especie + ") cannot be destroyed since the Nomenclatura " + nomenclaturaCollectionOrphanCheckNomenclatura + " in its nomenclaturaCollection field has a non-nullable especie field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Imagenes imagen = especie.getImagen();
            if (imagen != null) {
                imagen.getEspecieCollection().remove(especie);
                imagen = em.merge(imagen);
            }
            Genetica idSecuencia = especie.getIdSecuencia();
            if (idSecuencia != null) {
                idSecuencia.getEspecieCollection().remove(especie);
                idSecuencia = em.merge(idSecuencia);
            }
            em.remove(especie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especie> findEspecieEntities() {
        return findEspecieEntities(true, -1, -1);
    }

    public List<Especie> findEspecieEntities(int maxResults, int firstResult) {
        return findEspecieEntities(false, maxResults, firstResult);
    }

    private List<Especie> findEspecieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especie.class));
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

    public Especie findEspecie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especie.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especie> rt = cq.from(Especie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
