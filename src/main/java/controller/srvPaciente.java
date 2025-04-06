package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Paciente;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;
import model.dao.PacienteDaoJpa;

public class srvPaciente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");
            if (acao == null) {
                acao = "listagem";
            }

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String endereco = request.getParameter("endereco");
            String cidade = request.getParameter("cidade");
            String pesquisar = request.getParameter("nomePesquisa");

            InterfaceDao dao = DaoFactory.novoPacienteDAO();
            Paciente p;
            RequestDispatcher rd;

            switch (acao) {
                case "inclusao":
                    p = new Paciente(nome, cpf, endereco, cidade);
                    dao.incluir(p);
                    request.setAttribute("lista", listagem());
                    rd = request.getRequestDispatcher("gerenciar_paciente.jsp");
                    rd.forward(request, response);
                    break;

                case "pre-edicao":
                    if (id == null || id.isEmpty()) {
                        throw new IllegalArgumentException("ID inválido para edição.");
                    }
                    p = (Paciente) dao.pesquisarPorId(Long.parseLong(id));
                    rd = request.getRequestDispatcher("adicionar_paciente.jsp?acao=edicao"
                            + "&id=" + p.getId()
                            + "&nome=" + p.getNome()
                            + "&cpf=" + p.getCpf()
                            + "&endereco=" + p.getEndereco()
                            + "&cidade=" + p.getCidade());
                    rd.forward(request, response);
                    break;

                case "edicao":
                    if (id == null || id.isEmpty()) {
                        throw new IllegalArgumentException("ID inválido para edição.");
                    }
                    p = new Paciente(nome, cpf, endereco, cidade);
                    p.setId(Long.parseLong(id));
                    dao.editar(p);
                    request.setAttribute("lista", listagem());
                    rd = request.getRequestDispatcher("gerenciar_paciente.jsp");
                    rd.forward(request, response);
                    break;

                case "exclusao":
                    if (id == null || id.isEmpty()) {
                        throw new IllegalArgumentException("ID inválido para exclusão.");
                    }
                    p = new Paciente();
                    p.setId(Long.parseLong(id));
                    dao.excluir(p);
                    request.setAttribute("lista", listagem());
                    rd = request.getRequestDispatcher("gerenciar_paciente.jsp");
                    rd.forward(request, response);
                    break;

                case "listagem":
                    request.setAttribute("lista", listagem());
                    rd = request.getRequestDispatcher("gerenciar_paciente.jsp");
                    rd.forward(request, response);
                    break;

                case "pesquisarPorNome":
                    request.setAttribute("lista", listagemFiltrada(pesquisar));
                    rd = request.getRequestDispatcher("gerenciar_paciente.jsp");
                    rd.forward(request, response);
                    break;

                default:
                    response.getWriter().println("<p>Ação inválida.</p>");
                    break;
            }

        } catch (Exception e) {
            Logger.getLogger(srvPaciente.class.getName()).log(Level.SEVERE, null, e);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Erro ao processar a solicitação</h2>");
            response.getWriter().println("<pre>");
            e.printStackTrace(response.getWriter());
            response.getWriter().println("</pre>");
        }
    }

    private String listagem() {
        InterfaceDao dao = new PacienteDaoJpa();
        List<Paciente> lista;

        try {
            lista = dao.listar();
        } catch (Exception e) {
            return "<p>Erro ao listar pacientes.</p>";
        }

        if (lista == null || lista.isEmpty()) {
            return "<tr><td colspan='6'>Nenhum paciente encontrado.</td></tr>";
        }

        StringBuilder html = new StringBuilder();

        for (Paciente p : lista) {
            html.append("<tr>")
                .append("<td>").append(p.getNome()).append("</td>")
                .append("<td>").append(p.getCpf()).append("</td>")
                .append("<td>").append(p.getEndereco()).append("</td>")
                .append("<td>").append(p.getCidade()).append("</td>")
                .append("<td>")
                .append("<form action='srvPaciente?acao=pre-edicao' method='POST'>")
                .append("<input type='hidden' name='id' value='").append(p.getId()).append("'>")
                .append("<input type='submit' style='background-color: #4d3407; color: white; border: none; padding: 6px 12px; border-radius: 5px;' value='Editar'>")
                .append("</form>")
                .append("</td>")
                .append("<td>")
                .append("<form action='srvPaciente?acao=exclusao' method='POST'>")
                .append("<input type='hidden' name='id' value='").append(p.getId()).append("'>")
                .append("<input type='submit' class='btn btn-danger' value='Excluir'>")
                .append("</form>")
                .append("</td>")
                .append("</tr>");
        }

        return html.toString();
    }

    private String listagemFiltrada(String nome) {
        InterfaceDao dao = new PacienteDaoJpa();
        List<Paciente> lista;

        try {
            lista = dao.filtrarPornome(nome);
        } catch (Exception e) {
            return "<tr><td colspan='6'>Erro ao pesquisar pacientes.</td></tr>";
        }

        if (lista == null || lista.isEmpty()) {
            return "<tr><td colspan='6'>Nenhum paciente encontrado com esse nome.</td></tr>";
        }

        StringBuilder html = new StringBuilder();

        for (Paciente p : lista) {
            html.append("<tr>")
                .append("<td>").append(p.getNome()).append("</td>")
                .append("<td>").append(p.getCpf()).append("</td>")
                .append("<td>").append(p.getEndereco()).append("</td>")
                .append("<td>").append(p.getCidade()).append("</td>")
                .append("<td>")
                .append("<form action='srvPaciente?acao=pre-edicao' method='POST'>")
                .append("<input type='hidden' name='id' value='").append(p.getId()).append("'>")
                .append("<input type='submit' style='background-color: #4d3407; color: white; border: none; padding: 6px 12px; border-radius: 5px;' value='Editar'>")
                .append("</form>")
                .append("</td>")
                .append("<td>")
                .append("<form action='srvPaciente?acao=exclusao' method='POST'>")
                .append("<input type='hidden' name='id' value='").append(p.getId()).append("'>")
                .append("<input type='submit' class='btn btn-danger' value='Excluir'>")
                .append("</form>")
                .append("</td>")
                .append("</tr>");
        }

        return html.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(srvPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(srvPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet responsável pelo gerenciamento de pacientes";
    }
}

