package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;
import model.dao.*;

@WebServlet(name = "srvReceita", urlPatterns = {"/srvReceita"})
public class srvReceita extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            InterfaceDao<Receita> daoReceita = DaoFactory.novoReceitaDAO();
            InterfaceDao<Paciente> daoPaciente = DaoFactory.novoPacienteDAO();
            InterfaceDao<Medicamento> daoMedicamento = DaoFactory.novoMedicamentoDAO();

            if (acao == null || acao.equals("listar")) {
                List<Receita> lista = daoReceita.listar();

                // Força carregamento da lista de medicamentos (evita LazyInitializationException)
                for (Receita r : lista) {
                    if (r.getMedicamentos() != null) {
                        r.getMedicamentos().size();
                    }
                }

                request.setAttribute("receitas", lista);
                request.getRequestDispatcher("gerenciar_receita.jsp").forward(request, response);
            }

            if (acao.equals("novo")) {
                List<Paciente> pacientes = daoPaciente.listar();
                List<Medicamento> medicamentos = daoMedicamento.listar();

                request.setAttribute("pacientes", pacientes);
                request.setAttribute("medicamentos", medicamentos);
                request.setAttribute("acao", "salvar");
                request.getRequestDispatcher("adicionar_receita.jsp").forward(request, response);
            }

            if (acao.equals("excluir")) {
                long id = Long.parseLong(request.getParameter("id"));
                Receita r = daoReceita.pesquisarPorId(id);
                daoReceita.excluir(r);
                response.sendRedirect("srvReceita?acao=listar");
            }

            if (acao.equals("editar")) {
                long id = Long.parseLong(request.getParameter("id"));

                // Usa método com JOIN FETCH para evitar LazyInitializationException
                Receita r = ((ReceitaDaoJpa) daoReceita).buscarComMedicamentos(id);

                List<Paciente> pacientes = daoPaciente.listar();
                List<Medicamento> medicamentos = daoMedicamento.listar();

                request.setAttribute("receita", r);
                request.setAttribute("pacientes", pacientes);
                request.setAttribute("medicamentos", medicamentos);
                request.setAttribute("acao", "atualizar");
                request.setAttribute("id", r.getId());
                request.setAttribute("nomeMedico", r.getNomeMedico());

                request.getRequestDispatcher("adicionar_receita.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Erro ao processar ação: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        try {
            InterfaceDao<Receita> daoReceita = DaoFactory.novoReceitaDAO();
            InterfaceDao<Paciente> daoPaciente = DaoFactory.novoPacienteDAO();
            InterfaceDao<Medicamento> daoMedicamento = DaoFactory.novoMedicamentoDAO();

            // Dados do formulário
            long idPaciente = Long.parseLong(request.getParameter("paciente"));
            String[] idsMedicamentos = request.getParameterValues("medicamento");
            String nomeMedico = request.getParameter("nomeMedico");

            Paciente paciente = daoPaciente.pesquisarPorId(idPaciente);
            List<Medicamento> listaMedicamentos = new ArrayList<>();

            if (idsMedicamentos != null) {
                for (String idMed : idsMedicamentos) {
                    Medicamento m = daoMedicamento.pesquisarPorId(Long.parseLong(idMed));
                    listaMedicamentos.add(m);
                }
            }

            if (acao.equals("salvar")) {
                Receita nova = new Receita();
                nova.setPaciente(paciente);
                nova.setMedicamentos(listaMedicamentos);
                nova.setNomeMedico(nomeMedico);

                daoReceita.incluir(nova);
                response.sendRedirect("srvReceita?acao=listar");
            }

            if (acao.equals("atualizar")) {
                long id = Long.parseLong(request.getParameter("id"));
                Receita r = daoReceita.pesquisarPorId(id);
                r.setPaciente(paciente);
                r.setMedicamentos(listaMedicamentos);
                r.setNomeMedico(nomeMedico);

                daoReceita.editar(r);
                response.sendRedirect("srvReceita?acao=listar");
            }

        } catch (Exception e) {
            Logger.getLogger(srvReceita.class.getName()).log(Level.SEVERE, null, e);
            throw new ServletException("Erro ao salvar ou atualizar receita: " + e.getMessage(), e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador de receitas";
    }
}
