/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author Ycaro
 */
public class DaoFactory {
    
    public static MedicamentoDaoJpa novoMedicamentoDAO() throws Exception {
        return new MedicamentoDaoJpa();
    }
    
    public static PacienteDaoJpa novoPacienteDaoJpa() throws Exception{
        return new PacienteDaoJpa();
    }
    
    
}
