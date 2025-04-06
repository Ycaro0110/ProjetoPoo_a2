<%-- 
    Document   : adicionar_medicamento
    Created on : 19 de mar. de 2025, 06:54:42
    Author     : Ycaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <%
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String nomeCom = request.getParameter("nomeCom");
        String dose = request.getParameter("dose");
        String dtVal = request.getParameter("dt_val");

        if (id == null) {

            nome = "";
            nomeCom = "";
            dose = "";
            dtVal = "";

        };
    %>

    <head>
        <title>Página principal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleadicionarmedicamento.css">
    </head>

    <body>

        <div class="conteudo">
            <div class="row">
                <div class="conteudoesquerda" style="display: flex; justify-content: center; align-items: center;">
                    <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
                </div>


                <div class="conteudodireita" style="padding: 100px;">
                    <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Adicionar/editar Medicamento</h1>
                    <div>
                        <h4 style="color: #4D3407; margin-bottom: 30px;">Informações do medicamento</h4>
                    </div>

                    <form action="srvMedicamento" method="post">

                        <div style="display: flex; flex-direction: column; gap: 10px; align-items: center;">
                            <input type="hidden" name="acao" value="<%=acao%>" />
                            <input type="hidden" name="id" value="<%=id%>">
                            <input type="text" class="form-control" name="nomeMed" placeholder="Nome do medicamento" style="color: #4D3407" value="<%=nome%>">
                            <input type="text" class="form-control" name="nomeCom" placeholder="Nome Comercial" style="color: #4D3407" value="<%=nomeCom%>">
                            <input type="text" class="form-control" name="dose" placeholder="Dose" style="color: #4D3407;" value="<%=dose%>">
                            <input type="date" class="form-control" name="dt_val"placeholder="Data de Validade" style="color: #4D3407;" value="<%=dtVal%>">
                        </div>

                        <div class="d-flex gap-2 justify-content-center">
                            <button id="btnsalvar" type="submit" class="btn btn-primary">Salvar</button>
                            <a href="medicamento.jsp">
                                <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                            </a>
                        </div>


                    </form>


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
