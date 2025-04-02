/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Medicamento;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;
import model.dao.MedicamentoDaoJpa;

/**
 *
 * @author Ycaro
 */
public class srvMedicamento extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        try {

            String acao = request.getParameter("acao");
            String id = request.getParameter("id");

            InterfaceDao dao = DaoFactory.novoMedicamentoDAO();
            Medicamento m = null;
            RequestDispatcher rd = null;

            String nome = request.getParameter("nomeMed");
            String nomeComercial = request.getParameter("nomeCom");
            String dose = request.getParameter("dose");

            String dt_val = request.getParameter("dt_val");
            Calendar dt_va_calendarl;
            SimpleDateFormat sdf;
            dt_va_calendarl = Calendar.getInstance();
            Float doseStr = 0.0f;

            if (dose != null) {
                doseStr = Float.parseFloat(dose.trim());
            }

            if (dt_val == null || dt_val.isEmpty()) {
                dt_val = "2000-01-01";
            }

            sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dt_va_calendarl.setTime(sdf.parse(dt_val));
            } catch (ParseException ex) {
                Logger.getLogger(srvMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            switch (acao) {
                case "inclusao":

                    m = new Medicamento(nome, nomeComercial, doseStr, dt_va_calendarl);

                    dao.incluir(m);

                    rd = request.getRequestDispatcher("medicamento.jsp");

                    rd.forward(request, response);

                    break;

                case "pre-edicao":

                    m = (Medicamento) dao.pesquisarPorId(Long.parseLong(id));
                    rd = request.getRequestDispatcher("adicionar_medicamento.jsp?acao=edicao"
                            + "&id=" + m.getId()
                            + "&nome=" + m.getNome()
                            + "&nomeCom=" + m.getNomeComercial()
                            + "&dose=" + m.getDose()
                            + "&dt_val=" + m.getDtValidade());

                    rd.forward(request, response);
                    break;

                case "edicao":

                    m = new Medicamento(nome, nomeComercial, doseStr, dt_va_calendarl);
                    m.setId(Long.parseLong(id));
                    try {
                        dao.editar(m);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    rd = request.getRequestDispatcher("gerenciar_medicamento.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;

                case "exclusao":

                    m = new Medicamento();
                    m.setId(Long.parseLong(id));
                    try {
                        dao.excluir(m);
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    rd = request.getRequestDispatcher("gerenciar_medicamento.jsp?lista=" + listagem());
                    rd.forward(request, response);

                    break;

                case "listagem":

                    rd = request.getRequestDispatcher("gerenciar_medicamento.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;

            }

        } catch (Exception e) {
            Logger.getLogger(srvMedicamento.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    private String listagem() {
        InterfaceDao dao = new MedicamentoDaoJpa();

        List<Medicamento> lista = null;

        try {
            lista = dao.listar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (lista == null || lista.isEmpty()) {
            return "<p>Nenhum medicamento encontrado.</p>"; // Retorna uma mensagem ou outra resposta adequada
        }

        String listaHTML = " ";

        for (Medicamento m : lista) {
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + m.getNome() + "</td>" 
                    + "<td>" + m.getNomeComercial() + "</td>"
                    + "<td>" + m.getDose() + "</td> "
                    + "<td>" + m.getDtValidade().getTime() + "</td>"
                    + "<td> <form action=srvMedicamento?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value="
                    + m.getId() + "><input type='submit' class=\"btn btn-primary\" value=editar>"
                    + "</form> </td>"
                    + "<form action=srvMedicamento?acao=exclusao method='POST'>"
                    + "<td><input type='hidden' name='id' value="
                    + m.getId() + "><input type='submit' class=\"btn btn-primary\" value=excluir></td>"
                    + "</form>"
                    + "</tr>";
        }

        return listaHTML;
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(srvMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(srvMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
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
