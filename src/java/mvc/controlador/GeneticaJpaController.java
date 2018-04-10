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
import mvc.modelo.entidades.Especie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mvc.controlador.exceptions.IllegalOrphanException;
import mvc.controlador.exceptions.NonexistentEntityException;
import mvc.modelo.entidades.Genetica;

/**
 *
 * @author ppetk
 */
public class GeneticaJpaController implements Serializable {

    public GeneticaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genetica genetica) {
        if (genetica.getEspecieCollection() == null) {
            genetica.setEspecieCollection(new ArrayList<Especie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Especie> attachedEspecieCollection = new ArrayList<Especie>();
            for (Especie especieCollectionEspecieToAttach : genetica.getEspecieCollection()) {
                especieCollectionEspecieToAttach = em.getReference(especieCollectionEspecieToAttach.getClass(), especieCollectionEspecieToAttach.getIdEspecie());
                attachedEspecieCollection.add(especieCollectionEspecieToAttach);
            }
            genetica.setEspecieCollection(attachedEspecieCollection);
            em.persist(genetica);
            for (Especie especieCollectionEspecie : genetica.getEspecieCollection()) {
                Genetica oldIdSecuenciaOfEspecieCollectionEspecie = especieCollectionEspecie.getIdSecuencia();
                especieCollectionEspecie.setIdSecuencia(genetica);
                especieCollectionEspecie = em.merge(especieCollectionEspecie);
                if (oldIdSecuenciaOfEspecieCollectionEspecie != null) {
                    oldIdSecuenciaOfEspecieCollectionEspecie.getEspecieCollection().remove(especieCollectionEspecie);
                    oldIdSecuenciaOfEspecieCollectionEspecie = em.merge(oldIdSecuenciaOfEspecieCollectionEspecie);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genetica genetica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genetica persistentGenetica = em.find(Genetica.class, genetica.getIdSecuencia());
            Collection<Especie> especieCollectionOld = persistentGenetica.getEspecieCollection();
            Collection<Especie> especieCollectionNew = genetica.getEspecieCollection();
            List<String> illegalOrphanMessages = null;
            for (Especie especieCollectionOldEspecie : especieCollectionOld) {
                if (!especieCollectionNew.contains(especieCollectionOldEspecie)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Especie " + especieCollectionOldEspecie + " since its idSecuencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Especie> attachedEspecieCollectionNew = new ArrayList<Especie>();
            for (Especie especieCollectionNewEspecieToAttach : especieCollectionNew) {
                especieCollectionNewEspecieToAttach = em.getReference(especieCollectionNewEspecieToAttach.getClass(), especieCollectionNewEspecieToAttach.getIdEspecie());
                attachedEspecieCollectionNew.add(especieCollectionNewEspecieToAttach);
            }
            especieCollectionNew = attachedEspecieCollectionNew;
            genetica.setEspecieCollection(especieCollectionNew);
            genetica = em.merge(genetica);
            for (Especie especieCollectionNewEspecie : especieCollectionNew) {
                if (!especieCollectionOld.contains(especieCollectionNewEspecie)) {
                    Genetica oldIdSecuenciaOfEspecieCollectionNewEspecie = especieCollectionNewEspecie.getIdSecuencia();
                    especieCollectionNewEspecie.setIdSecuencia(genetica);
                    especieCollectionNewEspecie = em.merge(especieCollectionNewEspecie);
                    if (oldIdSecuenciaOfEspecieCollectionNewEspecie != null && !oldIdSecuenciaOfEspecieCollectionNewEspecie.equals(genetica)) {
                        oldIdSecuenciaOfEspecieCollectionNewEspecie.getEspecieCollection().remove(especieCollectionNewEspecie);
                        oldIdSecuenciaOfEspecieCollectionNewEspecie = em.merge(oldIdSecuenciaOfEspecieCollectionNewEspecie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = genetica.getIdSecuencia();
                if (findGenetica(id) == null) {
                    throw new NonexistentEntityException("The genetica with id " + id + " no longer exists.");
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
            Genetica genetica;
            try {
                genetica = em.getReference(Genetica.class, id);
                genetica.getIdSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genetica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Especie> especieCollectionOrphanCheck = genetica.getEspecieCollection();
            for (Especie especieCollectionOrphanCheckEspecie : especieCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genetica (" + genetica + ") cannot be destroyed since the Especie " + especieCollectionOrphanCheckEspecie + " in its especieCollection field has a non-nullable idSecuencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(genetica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genetica> findGeneticaEntities() {
        return findGeneticaEntities(true, -1, -1);
    }

    public List<Genetica> findGeneticaEntities(int maxResults, int firstResult) {
        return findGeneticaEntities(false, maxResults, firstResult);
    }

    private List<Genetica> findGeneticaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genetica.class));
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

    public Genetica findGenetica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genetica.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneticaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genetica> rt = cq.from(Genetica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
