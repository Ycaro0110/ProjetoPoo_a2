package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Receita;
import model.Medicamento;

public class ReceitaDaoJpa implements InterfaceDao<Receita> {

    @Override
    public void incluir(Receita entidade) throws Exception {
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
            Receita r = em.find(Receita.class, entidade.getId());
            if (r != null) {
                em.remove(r);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Receita pesquisarPorId(long id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.find(Receita.class, id);
        } finally {
            em.close();
        }
    }

    public Receita buscarComMedicamentos(long id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Receita r LEFT JOIN FETCH r.medicamentos WHERE r.id = :id", Receita.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Receita> listar() throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT r FROM Receita r "
                    + "JOIN FETCH r.paciente "
                    + "LEFT JOIN FETCH r.medicamentos", Receita.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Receita> filtrarPornome(String nome) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            Query query = em.createNamedQuery("Receita.filtrarPorNomeMedico");
            query.setParameter("nome", nome);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
