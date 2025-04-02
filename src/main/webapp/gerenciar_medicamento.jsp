<%-- 
    Document   : editar_medicamento
    Created on : 19 de mar. de 2025, 06:55:31
    Author     : Ycaro
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <%
        String listaHtml = request.getParameter("lista");
        String acao = request.getParameter("acao");
        String pesquisar = request.getParameter("nomePesquisa");
       String id = request.getParameter("id");
        
       pesquisar = "";
       

    %>

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

                    <a href="adicionar_medicamento.jsp">
                        <button id="btneditar" type="button" class="btn btn-primary">Editar Medicamento</button>
                    </a>
                    <a href="medicamento.jsp">
                        <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                    </a>
                </div>

                <div class="conteudodireita"
                     style="padding: 100px; display: flex; flex-direction: column; align-items: center;">
                    <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Gerenciar Medicamentos</h1>

                    <form action="srvMedicamento?acao=pesquisarPorNome" method="post"> 
                        <input type="hidden" name="acao" value="<%=acao%>" />
                        
                        <input type="text" class="form-control" style="text-align: right; width: 500px;"
                               placeholder="Pesquisar" name="nomePesquisa"> 

                        <button id="idPesquisar" type="submit" class="btn btn-primary">Pesquisar</button>
                    </form>


                    <div class="table-responsive mt-3">
                        <table class="table table-bordered table-hover  ">
                            <thead>
                                <tr>
                                    <th> Nome </th>
                                    <th> Nome Comercial </th>
                                    <th> Dose </th>
                                    <th> Data de validade </th>
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
            <div class="bg-dark text-light rodape ">
                INSTITUTO FEDERAL FLUMINENSE
            </div>
        </footer>

    </body>

</html>