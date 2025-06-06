<%-- 
    Document   : receita
    Created on : 19 de mar. de 2025
    Author     : Ycaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Farmácial</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styleeditarmedicamento.css">
</head>

<body>

    <div class="conteudo">
        <div class="row">
            <div class="conteudoesquerda" style="display: flex; justify-content: center; align-items: center;">
                <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
            </div>

            <div class="conteudodireita" style="padding: 100px;">
                <h1 class="display-4" style="color: #4D3407;">Receitas</h1>

                <div style="display: flex; flex-direction: column; gap: 10px;">

                    <!-- Botão para adicionar nova receita -->
                    <a href="srvReceita?acao=novo">
                        <button id="btngeral" type="button" class="btn btn-primary">Adicionar Receita</button>
                    </a>

                    <!-- Botão corrigido para listar receitas -->
                    <a href="srvReceita?acao=listar">
                        <button id="btngeral" type="button" class="btn btn-primary">Gerenciar Receitas</button>
                    </a>

                </div>

                <!-- Botão para voltar -->
                <a href="index.jsp">
                    <button id="btncancelar" type="button" class="btn btn-primary" style="float: right;">Cancelar</button>
                </a>
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


