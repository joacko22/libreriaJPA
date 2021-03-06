/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.DAO;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

/**
 *
 * @author usuario
 */
public class LibroDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private EntityManager em = emf.createEntityManager();

    public Libro crearLibro(Libro lib) throws Exception {
        try {
            if (lib == null) {
                throw new Exception("no puede estar vacio");
            }
            em.getTransaction().begin();
            em.persist(lib);
            em.getTransaction().commit();
            return lib;
        } catch (Exception e) {
            throw new Exception("error al crear libro");
        }
    }

    public Libro buscarPorID(String id) {
        return em.find(Libro.class, id);
    }

    public Libro buscarPorTitulo(String titulo) {
        Libro lib = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", titulo).getSingleResult();
        return lib;
    }

    public List<Libro> buscarLibroAutor(String autor1){

            if(autor1 == null){
                System.out.println("Debe indicar datos");
            }
            List<Libro> libros =  em.createQuery("Select l from Libro l, Autor a where a.nombre = :autor and l.autor.nombre = :autor").setParameter("autor", autor1).getResultList();
         
            return libros;

}

    public void eliminarPorID(String id) throws Exception {
        Libro lib = buscarPorID(id);
        try {
            if (lib == null) {
                throw new Exception("no hay libro");
            }
            em.getTransaction().begin();
            em.remove(lib);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
