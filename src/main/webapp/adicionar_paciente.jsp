<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<%
    String acao = request.getParameter("acao");
    String id = request.getParameter("id");
    String nome = request.getParameter("nome");
    String cpf = request.getParameter("cpf");
    String endereco = request.getParameter("endereco");
    String cidade = request.getParameter("cidade");

    // Inicialização segura dos campos
    if (nome == null) nome = "";
    if (cpf == null) cpf = "";
    if (endereco == null) endereco = "";
    if (cidade == null) cidade = "";
    if (id == null) id = "";
    if (acao == null) acao = "inclusao"; // padrão
%>

<head>
    <title>Adicionar Paciente</title>
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
                    <%= (acao.equals("edicao")) ? "Editar Paciente" : "Adicionar Paciente" %>
                </h1>

                <div class="mb-4 text-center">
                    <h4 style="color: #4D3407;">Informações do paciente</h4>
                </div>

                <form action="srvPaciente" method="post">
                    <input type="hidden" name="acao" value="<%=acao%>">
                    <input type="hidden" name="id" value="<%=id%>">

                    <div class="form-group">
                        <input type="text" class="form-control" name="nome" placeholder="Nome completo"
                               value="<%=nome%>" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="cpf" placeholder="CPF"
                               value="<%=cpf%>" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="endereco" placeholder="Endereço"
                               value="<%=endereco%>" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="cidade" placeholder="Cidade"
                               value="<%=cidade%>" required>
                    </div>

                    <div class="d-flex justify-content-center gap-2 mt-4" style="gap:20px;">
                            <button type="submit" class="btn" style="background-color: #4d3407; border: none; color: white;">Salvar</button>
                                <a href="gerenciar_paciente.jsp">
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

