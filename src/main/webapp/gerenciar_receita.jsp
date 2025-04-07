<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Receita"%>
<%@page import="model.Medicamento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<!DOCTYPE html>
<html lang="pt-br">
<%
    List<Receita> receitas = (List<Receita>) request.getAttribute("receitas");
    String pesquisar = request.getParameter("nomePesquisa");
    if (pesquisar == null) pesquisar = "";
%>

<head>
    <title>Gerenciar Receitas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styleeditarmedicamento.css">
</head>

<body>
    <div class="conteudo">
        <div class="row">
            <div class="conteudoesquerda"
                 style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
                <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva"
                     style="margin-bottom: 200px;">

                <a href="srvReceita?acao=novo">
                    <button id="btneditar" type="button" class="btn btn-primary">Adicionar Receita</button>
                </a>
                <a href="index.jsp">
                    <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                </a>
            </div>

            <div class="conteudodireita"
                 style="padding: 100px; display: flex; flex-direction: column; align-items: center;">
                <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Gerenciar Receitas</h1>

                <form action="srvReceita?acao=pesquisarPorNome" method="post" class="form-row m-1">
                    <input type="text" name="nomePesquisa" class="form-control"
                           style="text-align: right; width: 500px;"
                           placeholder="Pesquisar por nome do médico" value="<%=pesquisar%>">
                    <button id="idPesquisar" type="submit" class="btn ml-2" style="background-color: #4d3407; color: white; border: none;">
                        Pesquisar
                    </button>
                </form>

                <div class="table-responsive mt-3">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Médico</th>
                                <th>Paciente</th>
                                <th>Medicamentos</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (receitas != null && !receitas.isEmpty()) {
                                for (Receita r : receitas) {
                            %>
                                <tr>
                                    <td><%= r.getNomeMedico() %></td>
                                    <td><%= r.getPaciente().getNome() %></td>
                                    <td>
                                        <ul style="padding-left: 20px;">
                                            <% for (Medicamento m : r.getMedicamentos()) { %>
                                                <li><%= m.getNome() %></li>
                                            <% } %>
                                        </ul>
                                    </td>
                                    <td>
                                        <a href="srvReceita?acao=editar&id=<%= r.getId() %>" class="btn btn-sm" style="background-color: #4d3407; color: white;">Editar</a>
                                           <a href="srvReceita?acao=excluir&id=<%= r.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                                    </td>
                                </tr>
                            <% 
                                }
                            } else { 
                            %>
                                <tr>
                                    <td colspan="4" class="text-center">Nenhuma receita encontrada.</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <div class="bg-dark text-light rodape">
            INSTITUTO FEDERAL FLUMINENSE
        </div>
    </footer>
</body>
</html>