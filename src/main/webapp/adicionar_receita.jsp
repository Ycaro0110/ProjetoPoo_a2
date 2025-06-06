<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Paciente" %>
<%@ page import="model.Medicamento" %>
<%@ page import="model.Receita" %>
<!DOCTYPE html>
<html lang="pt-br">
    <%
        String acao = (String) request.getAttribute("acao");
        String id = request.getAttribute("id") != null ? request.getAttribute("id").toString() : "";
        String nomeMedico = request.getAttribute("nomeMedico") != null ? request.getAttribute("nomeMedico").toString() : "";

        List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
        List<Medicamento> medicamentos = (List<Medicamento>) request.getAttribute("medicamentos");
        Receita receita = (Receita) request.getAttribute("receita");
    %>

    <head>
        <title><%= "atualizar".equals(acao) ? "Editar Receita" : "Adicionar Receita"%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleadicionarmedicamento.css">
    </head>

    <body>
        <div class="conteudo">
            <div class="row">
                <div class="conteudoesquerda d-flex justify-content-center align-items-center">
                    <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
                </div>

                <div class="conteudodireita" style="padding: 100px;">
                    <h1 class="display-4 text-center" style="color: #4D3407; margin-bottom: 30px;">
                        <%= "atualizar".equals(acao) ? "Editar Receita" : "Adicionar Receita"%>
                    </h1>

                    <div class="mb-4 text-center">
                        <h4 style="color: #4D3407;">Informações da Receita</h4>
                    </div>

                    <form action="srvReceita" method="post">
                        <input type="hidden" name="acao" value="<%=acao%>">
                        <input type="hidden" name="id" value="<%=id%>">

                        <!-- Paciente -->
                        <div class="form-group">
                            <label for="paciente">Paciente</label>
                            <select name="paciente" class="form-control" required>
                                <option value="">Selecione o paciente</option>
                                <% if (pacientes != null) {
                                        for (Paciente p : pacientes) {
                                            boolean selecionado = receita != null && receita.getPaciente() != null && p.getId().equals(receita.getPaciente().getId());
                                %>
                                <option value="<%=p.getId()%>" <%=selecionado ? "selected" : ""%>><%=p.getNome()%></option>
                                <% }
                                    } %>
                            </select>
                        </div>

                        <!-- Campo para adicionar medicamento -->
                        <div class="form-group">
                            <label for="medicamento">Adicionar Medicamento</label>
                            <div class="d-flex">
                                <select id="selectMedicamento" class="form-control" style="flex:1; margin-right:10px;" multiple name="medicamentosID">
    <% if (medicamentos != null) {
        for (Medicamento m : medicamentos) {
            boolean selecionado = false;
            if (receita != null && receita.getMedicamentos() != null) {
                for (Medicamento medReceita : receita.getMedicamentos()) {
                    if (m.getId().equals(medReceita.getId())) {
                        selecionado = true;
                        break;
                    }
                }
            }
    %>
    <option value="<%=m.getId()%>" <%=selecionado ? "selected" : ""%>><%=m.getNome()%></option>
    <%  }
    } %>
</select>
                             
                            </div>
                        </div>               


                        <!-- Nome do médico -->
                        <div class="form-group">
                            <input type="text" class="form-control" name="nomeMedico" placeholder="Nome do Médico"
                                   value="<%=nomeMedico != null && !nomeMedico.isEmpty() ? nomeMedico : (receita != null ? receita.getNomeMedico() : "")%>" required>
                        </div>

                        <!-- Botões -->
                        <div class="d-flex justify-content-center gap-2 mt-4" style="gap:20px;">
                            <button type="submit" class="btn" style="background-color: #4d3407; border: none; color: white;">Salvar</button>
                            <a href="srvReceita?acao=listar">
                                <button type="button" class="btn" style="background-color: #ff0000; border: none; color: white;">Cancelar</button>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <footer>
            <div class="bg-dark text-light rodape text-center py-2">
                INSTITUTO FEDERAL FLUMINENSE
            </div>
        </footer>

    </body>
</html>
