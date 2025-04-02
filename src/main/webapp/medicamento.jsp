<%-- 
    Document   : telaTeste
    Created on : 17 de mar. de 2025, 14:34:21
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
    </head>

    <body>


        <div class="conteudo">
            <div class="row">
                <div class="conteudoesquerda" style="display: flex; justify-content: center; align-items: center;">
                    <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
                </div>


                <div class="conteudodireita" style="padding: 100px;">
                    <h1 class="display-4" style="color: #4D3407;">Medicamentos</h1>

                    <div style="display: flex; flex-direction: column; gap: 10px;">

                        <a href="adicionar_medicamento.jsp?acao=inclusao">
                            <button id="btngeral" type="button" class="btn btn-primary">Adicionar Medicamento</button>
                        </a>                        
                     
                        <a href="srvMedicamento?acao=listagem">
                            <button id="btngeral" type="button" class="btn btn-primary">Gerenciar Medicamentos</button>
                        </a>                        

                    </div>
                    <a href="index.jsp">
                        <button id="btncancelar" type="button" class="btn btn-primary" style="float: right;">Cancelar</button>
                    </a>
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

