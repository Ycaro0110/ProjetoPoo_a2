/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Medicamento;

/**
 *
 * @author Ycaro
 */
public class MedicamentoDaoJpa implements InterfaceDao<Medicamento> {

    @Override
    public void incluir(Medicamento entidade) throws Exception {

        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Override
    public void editar(Medicamento entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Medicamento entidade) throws Exception {

        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Medicamento m = em.find(Medicamento.class, entidade.getId());
            em.remove(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }

    }

    @Override
    public Medicamento pesquisarPorId(long id) throws Exception {
        Medicamento m = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            m = em.find(Medicamento.class, id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return m;
    }

    @Override
    public List<Medicamento> listar() throws Exception {

        List<Medicamento> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Medicamento m").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }

    @Override
    public List<Medicamento> filtrarPornome(String nome) throws Exception {
     
        EntityManager em = ConnFactory.getEntityManager();
        
        Query query = em.createNamedQuery("Medicamento.filtrarPornome");
        query.setParameter("nome", nome);
        List<Medicamento> resultado = query.getResultList();
        return resultado;


    }

}
