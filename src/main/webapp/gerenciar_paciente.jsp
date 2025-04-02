<%-- 
    Document   : editar_paciente
    Created on : 19 de mar. de 2025, 06:55:56
    Author     : Ycaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Página principal</title>
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

                <a href="editar_paciente_form.jsp">
                    <button id="btneditar" type="button" class="btn btn-primary">Editar Paciente</button>
                </a>
                <a href="paciente.jsp">
                    <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                </a>
            </div>

            <div class="conteudodireita"
                style="padding: 100px; display: flex; flex-direction: column; align-items: center;">
                <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Editar Paciente</h1>
                <input type="text" class="form-control" style="text-align: right; width: 500px;"
                    placeholder="Pesquisar">
            </div>

        </div>
    </div>

    <footer>
        <div class="bg-dark text-light rodape ">
            INSTITUTO FEDERAL FLUMINENSE
        </div>
    </footer>

</body>

</html>