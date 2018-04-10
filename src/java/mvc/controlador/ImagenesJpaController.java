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
import mvc.controlador.exceptions.NonexistentEntityException;
import mvc.modelo.entidades.Dominio;
import mvc.modelo.entidades.Genero;
import mvc.modelo.entidades.Filo;
import mvc.modelo.entidades.Orden;
import mvc.modelo.entidades.Familia;
import mvc.modelo.entidades.Clase;
import mvc.modelo.entidades.Imagenes;

/**
 *
 * @author ppetk
 */
public class ImagenesJpaController implements Serializable {

    public ImagenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagenes imagenes) {
        if (imagenes.getEspecieCollection() == null) {
            imagenes.setEspecieCollection(new ArrayList<Especie>());
        }
        if (imagenes.getDominioCollection() == null) {
            imagenes.setDominioCollection(new ArrayList<Dominio>());
        }
        if (imagenes.getGeneroCollection() == null) {
            imagenes.setGeneroCollection(new ArrayList<Genero>());
        }
        if (imagenes.getFiloCollection() == null) {
            imagenes.setFiloCollection(new ArrayList<Filo>());
        }
        if (imagenes.getOrdenCollection() == null) {
            imagenes.setOrdenCollection(new ArrayList<Orden>());
        }
        if (imagenes.getFamiliaCollection() == null) {
            imagenes.setFamiliaCollection(new ArrayList<Familia>());
        }
        if (imagenes.getClaseCollection() == null) {
            imagenes.setClaseCollection(new ArrayList<Clase>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Especie> attachedEspecieCollection = new ArrayList<Especie>();
            for (Especie especieCollectionEspecieToAttach : imagenes.getEspecieCollection()) {
                especieCollectionEspecieToAttach = em.getReference(especieCollectionEspecieToAttach.getClass(), especieCollectionEspecieToAttach.getIdEspecie());
                attachedEspecieCollection.add(especieCollectionEspecieToAttach);
            }
            imagenes.setEspecieCollection(attachedEspecieCollection);
            Collection<Dominio> attachedDominioCollection = new ArrayList<Dominio>();
            for (Dominio dominioCollectionDominioToAttach : imagenes.getDominioCollection()) {
                dominioCollectionDominioToAttach = em.getReference(dominioCollectionDominioToAttach.getClass(), dominioCollectionDominioToAttach.getIdDominio());
                attachedDominioCollection.add(dominioCollectionDominioToAttach);
            }
            imagenes.setDominioCollection(attachedDominioCollection);
            Collection<Genero> attachedGeneroCollection = new ArrayList<Genero>();
            for (Genero generoCollectionGeneroToAttach : imagenes.getGeneroCollection()) {
                generoCollectionGeneroToAttach = em.getReference(generoCollectionGeneroToAttach.getClass(), generoCollectionGeneroToAttach.getIdGenero());
                attachedGeneroCollection.add(generoCollectionGeneroToAttach);
            }
            imagenes.setGeneroCollection(attachedGeneroCollection);
            Collection<Filo> attachedFiloCollection = new ArrayList<Filo>();
            for (Filo filoCollectionFiloToAttach : imagenes.getFiloCollection()) {
                filoCollectionFiloToAttach = em.getReference(filoCollectionFiloToAttach.getClass(), filoCollectionFiloToAttach.getIdFilo());
                attachedFiloCollection.add(filoCollectionFiloToAttach);
            }
            imagenes.setFiloCollection(attachedFiloCollection);
            Collection<Orden> attachedOrdenCollection = new ArrayList<Orden>();
            for (Orden ordenCollectionOrdenToAttach : imagenes.getOrdenCollection()) {
                ordenCollectionOrdenToAttach = em.getReference(ordenCollectionOrdenToAttach.getClass(), ordenCollectionOrdenToAttach.getIdOrden());
                attachedOrdenCollection.add(ordenCollectionOrdenToAttach);
            }
            imagenes.setOrdenCollection(attachedOrdenCollection);
            Collection<Familia> attachedFamiliaCollection = new ArrayList<Familia>();
            for (Familia familiaCollectionFamiliaToAttach : imagenes.getFamiliaCollection()) {
                familiaCollectionFamiliaToAttach = em.getReference(familiaCollectionFamiliaToAttach.getClass(), familiaCollectionFamiliaToAttach.getIdFamilia());
                attachedFamiliaCollection.add(familiaCollectionFamiliaToAttach);
            }
            imagenes.setFamiliaCollection(attachedFamiliaCollection);
            Collection<Clase> attachedClaseCollection = new ArrayList<Clase>();
            for (Clase claseCollectionClaseToAttach : imagenes.getClaseCollection()) {
                claseCollectionClaseToAttach = em.getReference(claseCollectionClaseToAttach.getClass(), claseCollectionClaseToAttach.getIdClase());
                attachedClaseCollection.add(claseCollectionClaseToAttach);
            }
            imagenes.setClaseCollection(attachedClaseCollection);
            em.persist(imagenes);
            for (Especie especieCollectionEspecie : imagenes.getEspecieCollection()) {
                Imagenes oldImagenOfEspecieCollectionEspecie = especieCollectionEspecie.getImagen();
                especieCollectionEspecie.setImagen(imagenes);
                especieCollectionEspecie = em.merge(especieCollectionEspecie);
                if (oldImagenOfEspecieCollectionEspecie != null) {
                    oldImagenOfEspecieCollectionEspecie.getEspecieCollection().remove(especieCollectionEspecie);
                    oldImagenOfEspecieCollectionEspecie = em.merge(oldImagenOfEspecieCollectionEspecie);
                }
            }
            for (Dominio dominioCollectionDominio : imagenes.getDominioCollection()) {
                Imagenes oldImagenOfDominioCollectionDominio = dominioCollectionDominio.getImagen();
                dominioCollectionDominio.setImagen(imagenes);
                dominioCollectionDominio = em.merge(dominioCollectionDominio);
                if (oldImagenOfDominioCollectionDominio != null) {
                    oldImagenOfDominioCollectionDominio.getDominioCollection().remove(dominioCollectionDominio);
                    oldImagenOfDominioCollectionDominio = em.merge(oldImagenOfDominioCollectionDominio);
                }
            }
            for (Genero generoCollectionGenero : imagenes.getGeneroCollection()) {
                Imagenes oldImagenOfGeneroCollectionGenero = generoCollectionGenero.getImagen();
                generoCollectionGenero.setImagen(imagenes);
                generoCollectionGenero = em.merge(generoCollectionGenero);
                if (oldImagenOfGeneroCollectionGenero != null) {
                    oldImagenOfGeneroCollectionGenero.getGeneroCollection().remove(generoCollectionGenero);
                    oldImagenOfGeneroCollectionGenero = em.merge(oldImagenOfGeneroCollectionGenero);
                }
            }
            for (Filo filoCollectionFilo : imagenes.getFiloCollection()) {
                Imagenes oldImagenOfFiloCollectionFilo = filoCollectionFilo.getImagen();
                filoCollectionFilo.setImagen(imagenes);
                filoCollectionFilo = em.merge(filoCollectionFilo);
                if (oldImagenOfFiloCollectionFilo != null) {
                    oldImagenOfFiloCollectionFilo.getFiloCollection().remove(filoCollectionFilo);
                    oldImagenOfFiloCollectionFilo = em.merge(oldImagenOfFiloCollectionFilo);
                }
            }
            for (Orden ordenCollectionOrden : imagenes.getOrdenCollection()) {
                Imagenes oldImagenOfOrdenCollectionOrden = ordenCollectionOrden.getImagen();
                ordenCollectionOrden.setImagen(imagenes);
                ordenCollectionOrden = em.merge(ordenCollectionOrden);
                if (oldImagenOfOrdenCollectionOrden != null) {
                    oldImagenOfOrdenCollectionOrden.getOrdenCollection().remove(ordenCollectionOrden);
                    oldImagenOfOrdenCollectionOrden = em.merge(oldImagenOfOrdenCollectionOrden);
                }
            }
            for (Familia familiaCollectionFamilia : imagenes.getFamiliaCollection()) {
                Imagenes oldImagenOfFamiliaCollectionFamilia = familiaCollectionFamilia.getImagen();
                familiaCollectionFamilia.setImagen(imagenes);
                familiaCollectionFamilia = em.merge(familiaCollectionFamilia);
                if (oldImagenOfFamiliaCollectionFamilia != null) {
                    oldImagenOfFamiliaCollectionFamilia.getFamiliaCollection().remove(familiaCollectionFamilia);
                    oldImagenOfFamiliaCollectionFamilia = em.merge(oldImagenOfFamiliaCollectionFamilia);
                }
            }
            for (Clase claseCollectionClase : imagenes.getClaseCollection()) {
                Imagenes oldImagenOfClaseCollectionClase = claseCollectionClase.getImagen();
                claseCollectionClase.setImagen(imagenes);
                claseCollectionClase = em.merge(claseCollectionClase);
                if (oldImagenOfClaseCollectionClase != null) {
                    oldImagenOfClaseCollectionClase.getClaseCollection().remove(claseCollectionClase);
                    oldImagenOfClaseCollectionClase = em.merge(oldImagenOfClaseCollectionClase);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagenes imagenes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagenes persistentImagenes = em.find(Imagenes.class, imagenes.getIdImagenes());
            Collection<Especie> especieCollectionOld = persistentImagenes.getEspecieCollection();
            Collection<Especie> especieCollectionNew = imagenes.getEspecieCollection();
            Collection<Dominio> dominioCollectionOld = persistentImagenes.getDominioCollection();
            Collection<Dominio> dominioCollectionNew = imagenes.getDominioCollection();
            Collection<Genero> generoCollectionOld = persistentImagenes.getGeneroCollection();
            Collection<Genero> generoCollectionNew = imagenes.getGeneroCollection();
            Collection<Filo> filoCollectionOld = persistentImagenes.getFiloCollection();
            Collection<Filo> filoCollectionNew = imagenes.getFiloCollection();
            Collection<Orden> ordenCollectionOld = persistentImagenes.getOrdenCollection();
            Collection<Orden> ordenCollectionNew = imagenes.getOrdenCollection();
            Collection<Familia> familiaCollectionOld = persistentImagenes.getFamiliaCollection();
            Collection<Familia> familiaCollectionNew = imagenes.getFamiliaCollection();
            Collection<Clase> claseCollectionOld = persistentImagenes.getClaseCollection();
            Collection<Clase> claseCollectionNew = imagenes.getClaseCollection();
            Collection<Especie> attachedEspecieCollectionNew = new ArrayList<Especie>();
            for (Especie especieCollectionNewEspecieToAttach : especieCollectionNew) {
                especieCollectionNewEspecieToAttach = em.getReference(especieCollectionNewEspecieToAttach.getClass(), especieCollectionNewEspecieToAttach.getIdEspecie());
                attachedEspecieCollectionNew.add(especieCollectionNewEspecieToAttach);
            }
            especieCollectionNew = attachedEspecieCollectionNew;
            imagenes.setEspecieCollection(especieCollectionNew);
            Collection<Dominio> attachedDominioCollectionNew = new ArrayList<Dominio>();
            for (Dominio dominioCollectionNewDominioToAttach : dominioCollectionNew) {
                dominioCollectionNewDominioToAttach = em.getReference(dominioCollectionNewDominioToAttach.getClass(), dominioCollectionNewDominioToAttach.getIdDominio());
                attachedDominioCollectionNew.add(dominioCollectionNewDominioToAttach);
            }
            dominioCollectionNew = attachedDominioCollectionNew;
            imagenes.setDominioCollection(dominioCollectionNew);
            Collection<Genero> attachedGeneroCollectionNew = new ArrayList<Genero>();
            for (Genero generoCollectionNewGeneroToAttach : generoCollectionNew) {
                generoCollectionNewGeneroToAttach = em.getReference(generoCollectionNewGeneroToAttach.getClass(), generoCollectionNewGeneroToAttach.getIdGenero());
                attachedGeneroCollectionNew.add(generoCollectionNewGeneroToAttach);
            }
            generoCollectionNew = attachedGeneroCollectionNew;
            imagenes.setGeneroCollection(generoCollectionNew);
            Collection<Filo> attachedFiloCollectionNew = new ArrayList<Filo>();
            for (Filo filoCollectionNewFiloToAttach : filoCollectionNew) {
                filoCollectionNewFiloToAttach = em.getReference(filoCollectionNewFiloToAttach.getClass(), filoCollectionNewFiloToAttach.getIdFilo());
                attachedFiloCollectionNew.add(filoCollectionNewFiloToAttach);
            }
            filoCollectionNew = attachedFiloCollectionNew;
            imagenes.setFiloCollection(filoCollectionNew);
            Collection<Orden> attachedOrdenCollectionNew = new ArrayList<Orden>();
            for (Orden ordenCollectionNewOrdenToAttach : ordenCollectionNew) {
                ordenCollectionNewOrdenToAttach = em.getReference(ordenCollectionNewOrdenToAttach.getClass(), ordenCollectionNewOrdenToAttach.getIdOrden());
                attachedOrdenCollectionNew.add(ordenCollectionNewOrdenToAttach);
            }
            ordenCollectionNew = attachedOrdenCollectionNew;
            imagenes.setOrdenCollection(ordenCollectionNew);
            Collection<Familia> attachedFamiliaCollectionNew = new ArrayList<Familia>();
            for (Familia familiaCollectionNewFamiliaToAttach : familiaCollectionNew) {
                familiaCollectionNewFamiliaToAttach = em.getReference(familiaCollectionNewFamiliaToAttach.getClass(), familiaCollectionNewFamiliaToAttach.getIdFamilia());
                attachedFamiliaCollectionNew.add(familiaCollectionNewFamiliaToAttach);
            }
            familiaCollectionNew = attachedFamiliaCollectionNew;
            imagenes.setFamiliaCollection(familiaCollectionNew);
            Collection<Clase> attachedClaseCollectionNew = new ArrayList<Clase>();
            for (Clase claseCollectionNewClaseToAttach : claseCollectionNew) {
                claseCollectionNewClaseToAttach = em.getReference(claseCollectionNewClaseToAttach.getClass(), claseCollectionNewClaseToAttach.getIdClase());
                attachedClaseCollectionNew.add(claseCollectionNewClaseToAttach);
            }
            claseCollectionNew = attachedClaseCollectionNew;
            imagenes.setClaseCollection(claseCollectionNew);
            imagenes = em.merge(imagenes);
            for (Especie especieCollectionOldEspecie : especieCollectionOld) {
                if (!especieCollectionNew.contains(especieCollectionOldEspecie)) {
                    especieCollectionOldEspecie.setImagen(null);
                    especieCollectionOldEspecie = em.merge(especieCollectionOldEspecie);
                }
            }
            for (Especie especieCollectionNewEspecie : especieCollectionNew) {
                if (!especieCollectionOld.contains(especieCollectionNewEspecie)) {
                    Imagenes oldImagenOfEspecieCollectionNewEspecie = especieCollectionNewEspecie.getImagen();
                    especieCollectionNewEspecie.setImagen(imagenes);
                    especieCollectionNewEspecie = em.merge(especieCollectionNewEspecie);
                    if (oldImagenOfEspecieCollectionNewEspecie != null && !oldImagenOfEspecieCollectionNewEspecie.equals(imagenes)) {
                        oldImagenOfEspecieCollectionNewEspecie.getEspecieCollection().remove(especieCollectionNewEspecie);
                        oldImagenOfEspecieCollectionNewEspecie = em.merge(oldImagenOfEspecieCollectionNewEspecie);
                    }
                }
            }
            for (Dominio dominioCollectionOldDominio : dominioCollectionOld) {
                if (!dominioCollectionNew.contains(dominioCollectionOldDominio)) {
                    dominioCollectionOldDominio.setImagen(null);
                    dominioCollectionOldDominio = em.merge(dominioCollectionOldDominio);
                }
            }
            for (Dominio dominioCollectionNewDominio : dominioCollectionNew) {
                if (!dominioCollectionOld.contains(dominioCollectionNewDominio)) {
                    Imagenes oldImagenOfDominioCollectionNewDominio = dominioCollectionNewDominio.getImagen();
                    dominioCollectionNewDominio.setImagen(imagenes);
                    dominioCollectionNewDominio = em.merge(dominioCollectionNewDominio);
                    if (oldImagenOfDominioCollectionNewDominio != null && !oldImagenOfDominioCollectionNewDominio.equals(imagenes)) {
                        oldImagenOfDominioCollectionNewDominio.getDominioCollection().remove(dominioCollectionNewDominio);
                        oldImagenOfDominioCollectionNewDominio = em.merge(oldImagenOfDominioCollectionNewDominio);
                    }
                }
            }
            for (Genero generoCollectionOldGenero : generoCollectionOld) {
                if (!generoCollectionNew.contains(generoCollectionOldGenero)) {
                    generoCollectionOldGenero.setImagen(null);
                    generoCollectionOldGenero = em.merge(generoCollectionOldGenero);
                }
            }
            for (Genero generoCollectionNewGenero : generoCollectionNew) {
                if (!generoCollectionOld.contains(generoCollectionNewGenero)) {
                    Imagenes oldImagenOfGeneroCollectionNewGenero = generoCollectionNewGenero.getImagen();
                    generoCollectionNewGenero.setImagen(imagenes);
                    generoCollectionNewGenero = em.merge(generoCollectionNewGenero);
                    if (oldImagenOfGeneroCollectionNewGenero != null && !oldImagenOfGeneroCollectionNewGenero.equals(imagenes)) {
                        oldImagenOfGeneroCollectionNewGenero.getGeneroCollection().remove(generoCollectionNewGenero);
                        oldImagenOfGeneroCollectionNewGenero = em.merge(oldImagenOfGeneroCollectionNewGenero);
                    }
                }
            }
            for (Filo filoCollectionOldFilo : filoCollectionOld) {
                if (!filoCollectionNew.contains(filoCollectionOldFilo)) {
                    filoCollectionOldFilo.setImagen(null);
                    filoCollectionOldFilo = em.merge(filoCollectionOldFilo);
                }
            }
            for (Filo filoCollectionNewFilo : filoCollectionNew) {
                if (!filoCollectionOld.contains(filoCollectionNewFilo)) {
                    Imagenes oldImagenOfFiloCollectionNewFilo = filoCollectionNewFilo.getImagen();
                    filoCollectionNewFilo.setImagen(imagenes);
                    filoCollectionNewFilo = em.merge(filoCollectionNewFilo);
                    if (oldImagenOfFiloCollectionNewFilo != null && !oldImagenOfFiloCollectionNewFilo.equals(imagenes)) {
                        oldImagenOfFiloCollectionNewFilo.getFiloCollection().remove(filoCollectionNewFilo);
                        oldImagenOfFiloCollectionNewFilo = em.merge(oldImagenOfFiloCollectionNewFilo);
                    }
                }
            }
            for (Orden ordenCollectionOldOrden : ordenCollectionOld) {
                if (!ordenCollectionNew.contains(ordenCollectionOldOrden)) {
                    ordenCollectionOldOrden.setImagen(null);
                    ordenCollectionOldOrden = em.merge(ordenCollectionOldOrden);
                }
            }
            for (Orden ordenCollectionNewOrden : ordenCollectionNew) {
                if (!ordenCollectionOld.contains(ordenCollectionNewOrden)) {
                    Imagenes oldImagenOfOrdenCollectionNewOrden = ordenCollectionNewOrden.getImagen();
                    ordenCollectionNewOrden.setImagen(imagenes);
                    ordenCollectionNewOrden = em.merge(ordenCollectionNewOrden);
                    if (oldImagenOfOrdenCollectionNewOrden != null && !oldImagenOfOrdenCollectionNewOrden.equals(imagenes)) {
                        oldImagenOfOrdenCollectionNewOrden.getOrdenCollection().remove(ordenCollectionNewOrden);
                        oldImagenOfOrdenCollectionNewOrden = em.merge(oldImagenOfOrdenCollectionNewOrden);
                    }
                }
            }
            for (Familia familiaCollectionOldFamilia : familiaCollectionOld) {
                if (!familiaCollectionNew.contains(familiaCollectionOldFamilia)) {
                    familiaCollectionOldFamilia.setImagen(null);
                    familiaCollectionOldFamilia = em.merge(familiaCollectionOldFamilia);
                }
            }
            for (Familia familiaCollectionNewFamilia : familiaCollectionNew) {
                if (!familiaCollectionOld.contains(familiaCollectionNewFamilia)) {
                    Imagenes oldImagenOfFamiliaCollectionNewFamilia = familiaCollectionNewFamilia.getImagen();
                    familiaCollectionNewFamilia.setImagen(imagenes);
                    familiaCollectionNewFamilia = em.merge(familiaCollectionNewFamilia);
                    if (oldImagenOfFamiliaCollectionNewFamilia != null && !oldImagenOfFamiliaCollectionNewFamilia.equals(imagenes)) {
                        oldImagenOfFamiliaCollectionNewFamilia.getFamiliaCollection().remove(familiaCollectionNewFamilia);
                        oldImagenOfFamiliaCollectionNewFamilia = em.merge(oldImagenOfFamiliaCollectionNewFamilia);
                    }
                }
            }
            for (Clase claseCollectionOldClase : claseCollectionOld) {
                if (!claseCollectionNew.contains(claseCollectionOldClase)) {
                    claseCollectionOldClase.setImagen(null);
                    claseCollectionOldClase = em.merge(claseCollectionOldClase);
                }
            }
            for (Clase claseCollectionNewClase : claseCollectionNew) {
                if (!claseCollectionOld.contains(claseCollectionNewClase)) {
                    Imagenes oldImagenOfClaseCollectionNewClase = claseCollectionNewClase.getImagen();
                    claseCollectionNewClase.setImagen(imagenes);
                    claseCollectionNewClase = em.merge(claseCollectionNewClase);
                    if (oldImagenOfClaseCollectionNewClase != null && !oldImagenOfClaseCollectionNewClase.equals(imagenes)) {
                        oldImagenOfClaseCollectionNewClase.getClaseCollection().remove(claseCollectionNewClase);
                        oldImagenOfClaseCollectionNewClase = em.merge(oldImagenOfClaseCollectionNewClase);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imagenes.getIdImagenes();
                if (findImagenes(id) == null) {
                    throw new NonexistentEntityException("The imagenes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagenes imagenes;
            try {
                imagenes = em.getReference(Imagenes.class, id);
                imagenes.getIdImagenes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenes with id " + id + " no longer exists.", enfe);
            }
            Collection<Especie> especieCollection = imagenes.getEspecieCollection();
            for (Especie especieCollectionEspecie : especieCollection) {
                especieCollectionEspecie.setImagen(null);
                especieCollectionEspecie = em.merge(especieCollectionEspecie);
            }
            Collection<Dominio> dominioCollection = imagenes.getDominioCollection();
            for (Dominio dominioCollectionDominio : dominioCollection) {
                dominioCollectionDominio.setImagen(null);
                dominioCollectionDominio = em.merge(dominioCollectionDominio);
            }
            Collection<Genero> generoCollection = imagenes.getGeneroCollection();
            for (Genero generoCollectionGenero : generoCollection) {
                generoCollectionGenero.setImagen(null);
                generoCollectionGenero = em.merge(generoCollectionGenero);
            }
            Collection<Filo> filoCollection = imagenes.getFiloCollection();
            for (Filo filoCollectionFilo : filoCollection) {
                filoCollectionFilo.setImagen(null);
                filoCollectionFilo = em.merge(filoCollectionFilo);
            }
            Collection<Orden> ordenCollection = imagenes.getOrdenCollection();
            for (Orden ordenCollectionOrden : ordenCollection) {
                ordenCollectionOrden.setImagen(null);
                ordenCollectionOrden = em.merge(ordenCollectionOrden);
            }
            Collection<Familia> familiaCollection = imagenes.getFamiliaCollection();
            for (Familia familiaCollectionFamilia : familiaCollection) {
                familiaCollectionFamilia.setImagen(null);
                familiaCollectionFamilia = em.merge(familiaCollectionFamilia);
            }
            Collection<Clase> claseCollection = imagenes.getClaseCollection();
            for (Clase claseCollectionClase : claseCollection) {
                claseCollectionClase.setImagen(null);
                claseCollectionClase = em.merge(claseCollectionClase);
            }
            em.remove(imagenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagenes> findImagenesEntities() {
        return findImagenesEntities(true, -1, -1);
    }

    public List<Imagenes> findImagenesEntities(int maxResults, int firstResult) {
        return findImagenesEntities(false, maxResults, firstResult);
    }

    private List<Imagenes> findImagenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imagenes.class));
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

    public Imagenes findImagenes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imagenes> rt = cq.from(Imagenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
