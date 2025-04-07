package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
                request.setAttribute("receitas", lista);
                request.getRequestDispatcher("gerenciar_receita.jsp").forward(request, response);
            }

            if (acao.equals("novo")) {
                List<Paciente> pacientes = daoPaciente.listar();
                List<Medicamento> medicamentos = daoMedicamento.listar();

                request.setAttribute("pacientes", pacientes);
                request.setAttribute("medicamentos", medicamentos);
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
                Receita r = daoReceita.pesquisarPorId(id);

                List<Paciente> pacientes = daoPaciente.listar();
                List<Medicamento> medicamentos = daoMedicamento.listar();

                request.setAttribute("receita", r);
                request.setAttribute("pacientes", pacientes);
                request.setAttribute("medicamentos", medicamentos);

                request.getRequestDispatcher("editar_receita.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
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

            if (acao.equals("salvar")) {
                System.out.println("teste");
                long idPaciente = Long.parseLong(request.getParameter("paciente"));
                String[] idsMedicamentos = request.getParameterValues("medicamento");
                String nomeMedico = request.getParameter("nomeMedico");

                Paciente paciente = daoPaciente.pesquisarPorId(idPaciente);
                List<Medicamento> listaMedicamentos = new ArrayList<>();

                for (String idMed : idsMedicamentos) {
                    Medicamento m = daoMedicamento.pesquisarPorId(Long.parseLong(idMed));
                    listaMedicamentos.add(m);
                }

                Receita nova = new Receita();
                nova.setPaciente(paciente);
                nova.setMedicamentos(listaMedicamentos);
                nova.setNomeMedico(nomeMedico);

                // LOG DE DEPURAÇÃO
                System.out.println("====== SALVANDO RECEITA ======");
                System.out.println("Médico: " + nova.getNomeMedico());
                System.out.println("Paciente: " + (paciente != null ? paciente.getNome() : "Paciente nulo"));
                System.out.println("Medicamentos:");
                for (Medicamento med : listaMedicamentos) {
                    System.out.println(" - " + med.getNome());
                }
                System.out.println("================================");

                daoReceita.incluir(nova);
                response.sendRedirect("srvReceita?acao=listar");
            }

            if (acao.equals("atualizar")) {
                long id = Long.parseLong(request.getParameter("id"));
                long idPaciente = Long.parseLong(request.getParameter("paciente"));
                String[] idsMedicamentos = request.getParameterValues("medicamento");
                String nomeMedico = request.getParameter("nomeMedico");

                Paciente paciente = daoPaciente.pesquisarPorId(idPaciente);
                List<Medicamento> listaMedicamentos = new ArrayList<>();

                for (String idMed : idsMedicamentos) {
                    Medicamento m = daoMedicamento.pesquisarPorId(Long.parseLong(idMed));
                    listaMedicamentos.add(m);
                }

                Receita r = daoReceita.pesquisarPorId(id);
                r.setPaciente(paciente);
                r.setMedicamentos(listaMedicamentos);
                r.setNomeMedico(nomeMedico);

                daoReceita.editar(r);
                response.sendRedirect("srvReceita?acao=listar");
            }

        } catch (Exception e) {
            e.printStackTrace(); // loga a stack trace completa
            throw new ServletException("Erro ao processar receita", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador de receitas";
    }
}
