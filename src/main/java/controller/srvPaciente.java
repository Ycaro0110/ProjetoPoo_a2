/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Paciente;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;

/**
 *
 * @author Ycaro
 */
public class srvPaciente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String id = request.getParameter("id");
            String acao = request.getParameter("acao");
            InterfaceDao dao = DaoFactory.novoPacienteDaoJpa();
            Paciente p1 = null;
            RequestDispatcher rd = null;

            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String endereco = request.getParameter("endereco");
            String cidade = request.getParameter("cidade");

            switch (acao) {
                case "inclusao":
                    p1 = new Paciente(nome, cpf, endereco, cidade);

                    dao.incluir(p1);

                    rd = request.getRequestDispatcher("paciente.jsp");

                    rd.forward(request, response);

                    break;
                case "pre-edicao":

                    p1 = (Paciente) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("editar_medicamento_form.jsp?acao=edicao"
                            + "&=id" + p1.getId()
                            + "&=nome" + p1.getNome()
                            + "&=cpf" + p1.getCpf()
                            + "&=endereco" + p1.getEndereco()
                            + "&=cidade" + p1.getCidade());

                    rd.forward(request, response);

                    break;

                case "edicao":

                    p1 = new Paciente();
                    p1.setId(Long.MIN_VALUE);

                    break;
                case "exclusao":

                    break;
                case "listagem":

                    break;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
