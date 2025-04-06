package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Paciente;

public class PacienteDaoJpa implements InterfaceDao<Paciente> {

    @Override
    public void incluir(Paciente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new Exception("Erro ao incluir paciente: " + e.getMessage(), e);
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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new Exception("Erro ao editar paciente: " + e.getMessage(), e);
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
            if (p != null) {
                em.remove(p);
            } else {
                throw new Exception("Paciente com ID " + entidade.getId() + " não encontrado para exclusão.");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new Exception("Erro ao excluir paciente: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Paciente pesquisarPorId(long id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Paciente p = em.find(Paciente.class, id);
            em.getTransaction().commit();
            if (p == null) {
                throw new Exception("Paciente com ID " + id + " não encontrado.");
            }
            return p;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new Exception("Erro ao pesquisar paciente por ID: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Paciente> listar() throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Paciente> lista = em.createQuery("FROM Paciente p", Paciente.class).getResultList();
            em.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new Exception("Erro ao listar pacientes: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Paciente> filtrarPornome(String nome) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))",
                Paciente.class
            ).setParameter("nome", nome).getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao filtrar pacientes por nome: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}

