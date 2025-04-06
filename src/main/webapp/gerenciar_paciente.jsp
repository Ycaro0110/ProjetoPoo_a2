<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<%
    String listaHtml = (String) request.getAttribute("lista");
    if (listaHtml == null) listaHtml = "";

    String pesquisar = request.getParameter("nomePesquisa");
    if (pesquisar == null) pesquisar = "";
%>

<head>
    <title>Gerenciar Pacientes</title>
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

                <a href="adicionar_paciente.jsp?acao=inclusao">
                    <button id="btneditar" type="button" class="btn btn-primary">Adicionar Paciente</button>
                </a>
                <a href="index.jsp">
                    <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                </a>
            </div>

            <div class="conteudodireita"
                 style="padding: 100px; display: flex; flex-direction: column; align-items: center;">
                <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Gerenciar Pacientes</h1>

                <form action="srvPaciente?acao=pesquisarPorNome" method="post" class="form-row m-1">
                    <input type="text" name="nomePesquisa" class="form-control"
                           style="text-align: right; width: 500px;"
                           placeholder="Pesquisar por nome" value="<%=pesquisar%>">
                    <button id="idPesquisar" type="submit" class="btn ml-2" style="background-color: #4d3407; color: white; border: none;">
                        Pesquisar
                    </button>
                </form>

                <div class="table-responsive mt-3">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Endereço</th>
                                <th>Cidade</th>
                                <th>Ação</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%=listaHtml%>
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


