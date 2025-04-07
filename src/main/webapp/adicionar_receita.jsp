<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Paciente" %>
<%@ page import="model.Medicamento" %>
<!DOCTYPE html>
<html lang="pt-br">
    <%
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        String nomeMedico = request.getParameter("nomeMedico");

        if (id == null) {
            id = "";
        }
        if (nomeMedico == null) {
            nomeMedico = "";
        }

        List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
        List<Medicamento> medicamentos = (List<Medicamento>) request.getAttribute("medicamentos");
    %>

    <html lang="pt-br">

        <head>
            <title><%= (acao.equals("edicao")) ? "Editar Receita" : "Adicionar Receita"%></title>
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
                            <%= (acao.equals("edicao")) ? "Editar Receita" : "Adicionar Receita"%>
                        </h1>

                        <div class="mb-4 text-center">
                            <h4 style="color: #4D3407;">Informações da Receita</h4>
                        </div>

                        <form action="srvReceita?acao=salvar" method="post">
                            <input type="hidden" name="acao" value="<%=acao%>">
                            <input type="hidden" name="id" value="<%=id%>">

                            <!-- Paciente -->
                            <div class="form-group">
                                <label for="paciente">Paciente</label>
                                <select name="paciente" class="form-control" required>
                                    <option value="">Selecione o paciente</option>
                                    <% if (pacientes != null) {
                                            for (Paciente p : pacientes) {
                                    %>
                                    <option value="<%=p.getId()%>"><%=p.getNome()%></option>
                                    <% }
                                } %>
                                </select>
                            </div>

                            <!-- Medicamentos - campo de seleção + botão adicionar -->
                            <div class="form-group">
                                <label for="medicamento">Adicionar Medicamento</label>
                                <div class="d-flex">
                                    <select id="selectMedicamento" class="form-control" style="flex:1; margin-right:10px;">
                                        <option value="">Selecione um medicamento</option>
                                        <% if (medicamentos != null) {
                                                for (Medicamento m : medicamentos) {
                                        %>
                                        <option value="<%=m.getId()%>" data-nome="<%=m.getNome()%>"><%=m.getNome()%></option>
                                        <% }
                                    }%>
                                    </select>
                                    <button type="button" class="btn btn-success" onclick="adicionarMedicamento()">Adicionar</button>
                                </div>
                            </div>

                            <!-- Lista dinâmica dos medicamentos adicionados -->
                            <div class="form-group">
                                <label>Medicamentos adicionados</label>
                                <ul id="listaMedicamentos" class="list-group"></ul>
                            </div>

                            <!-- Nome do Médico -->
                            <div class="form-group">
                                <input type="text" class="form-control" name="nomeMedico" placeholder="Nome do Médico"
                                       value="<%=nomeMedico%>" required>
                            </div>

                            <!-- Botões -->
                            <div class="d-flex justify-content-center gap-2 mt-4" style="gap:20px;">
                                <button type="submit" class="btn" style="background-color: #4d3407; border: none; color: white;">Salvar</button>
                                <a href="gerenciar_receita.jsp">
                                    <button type="button" class="btn" style="background-color: #ff0000; border: none; color: white;">Cancelar</button>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Rodapé -->
            <footer>
                <div class="bg-dark text-light rodape text-center py-2">
                    INSTITUTO FEDERAL FLUMINENSE
                </div>
            </footer>

            <!-- Script JS -->
            <script>
                function adicionarMedicamento() {
                    const select = document.getElementById("selectMedicamento");
                    const id = select.value;
                    const nome = select.options[select.selectedIndex].getAttribute("data-nome");

                    if (!id || !nome)
                        return;

                    const lista = document.getElementById("listaMedicamentos");

                    // Evita adicionar duplicado
                    if ([...lista.children].some(li => li.dataset.id === id)) {
                        alert("Este medicamento já foi adicionado.");
                        return;
                    }

                    const li = document.createElement("li");
                    li.className = "list-group-item d-flex justify-content-between align-items-center";
                    li.dataset.id = id;
                    li.innerHTML = `
                        <span>${nome}</span>
                        <div>
                            <input type="hidden" name="medicamento" value="${id}">
                            <button type="button" class="btn btn-danger btn-sm ml-2" onclick="this.closest('li').remove()">Remover</button>
                        </div>
                    `;

                    lista.appendChild(li);
                }
            </script>
        </body>
    </html>

