/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Medicamento;
import model.Paciente;
import model.Receita;

/**
 *
 * @author Ycaro
 */
public class ReceitaDaoJpa implements InterfaceDao<Receita> {

    @Override
    public void incluir(Receita entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            for (Medicamento m : entidade.getMedicamentos()) {
                em.persist(m);
            }

            Paciente p = entidade.getPaciente();
            em.persist(p);

            String nomeMedico = entidade.getNomeMedico();

            em.persist(nomeMedico);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Receita entidade) throws Exception {
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
    public void excluir(Receita entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Receita r1 = em.find(Receita.class, entidade.getId());
            em.remove(r1);

        } catch (Exception e) {
            em.close();
        }

    }

    @Override
    public Receita pesquisarPorId(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Receita> listar() throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        List<Receita> lista = null;

        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Receita R").getResultList();
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return lista;
    }

    @Override
    public List<Receita> filtrarPornome(String nome) throws Exception {
      EntityManager em = ConnFactory.getEntityManager();
        Query query = em.createNamedQuery("Receita.filtrarPorNomeMedico");
        query.setParameter("nomeMedico", nome);
        List<Receita> resultado = query.getResultList();
        return resultado;
    }

}
