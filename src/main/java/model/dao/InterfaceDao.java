/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;

/**
 *
 * @author Ycaro
 * @param <T>
 */
public interface InterfaceDao<T> {
    
    public abstract void incluir(T entidade) throws Exception;

    public abstract void editar(T entidade) throws Exception;

    public abstract void excluir(T entidade) throws Exception;

    public abstract T pesquisarPorId(long id) throws Exception;

    public abstract List<T> listar() throws Exception;
    
}
