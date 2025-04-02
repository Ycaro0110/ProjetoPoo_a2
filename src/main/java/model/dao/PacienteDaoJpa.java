/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Paciente;

/**
 *
 * @author Ycaro
 */
public class PacienteDaoJpa implements InterfaceDao<Paciente> {

    @Override
    public void incluir(Paciente entidade) throws Exception {
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
    public void editar(Paciente entidade) throws Exception {
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
    public void excluir(Paciente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Paciente p = em.find(Paciente.class, entidade.getId());
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
        }
    }

    @Override
    public Paciente pesquisarPorId(long id) throws Exception {
        Paciente p = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            p = em.find(Paciente.class, id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public List<Paciente> listar() throws Exception {

        List<Paciente> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Paciente p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;

    }

    @Override
    public List<Paciente> filtrarPornome(String nome) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        Query query = em.createNamedQuery("Paciente.filtrarPorNome");
        query.setParameter("nome", nome);
        List<Paciente> resultado = query.getResultList();
        return resultado;
    }

}
