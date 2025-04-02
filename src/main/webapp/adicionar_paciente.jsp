<%-- 
    Document   : adicionar_paciente
    Created on : 19 de mar. de 2025, 06:54:53
    Author     : Ycaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <%
        String acao = request.getParameter("acao");
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
                    <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Adicionar Paciente</h1>
                    <div>
                        <h4 style="color: #4D3407; margin-bottom: 30px;">Informações do Paciente</h4>
                    </div>

                    <form action="srvPaciente?acao=inclusao" method="post">
                        
                        <input   type="hidden" name="acao" value="<%=acao%>" />
                        
                        
                        <div style="display: flex; flex-direction: column; gap: 10px; align-items: center;">
                            <input type="text" class="form-control" name="nome" placeholder="Nome" style="color: #4D3407;">
                            <input type="text" class="form-control" name="cpf"placeholder="CPF" style="color: #4D3407;">
                            <input type="text" class="form-control" name="endereco" placeholder="Endereço" style="color: #4D3407;">
                            <input type="text" class="form-control" name="cidade" placeholder="Cidade" style="color: #4D3407;">
                        </div>

                        <div class="d-flex gap-2 justify-content-center">
                            <button id="btnsalvar" type="submit" class="btn btn-primary">Salvar</button>
                            <a href="paciente.jsp">
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
