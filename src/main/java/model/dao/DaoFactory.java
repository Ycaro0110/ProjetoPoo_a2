package model.dao;

public class DaoFactory {

    public static MedicamentoDaoJpa novoMedicamentoDAO() throws Exception {
        return new MedicamentoDaoJpa();
    }

    public static InterfaceDao novoPacienteDAO() {
        return new PacienteDaoJpa();
    }

    public static InterfaceDao novoReceitaDAO() {
        return new ReceitaDaoJpa();
    }
}


