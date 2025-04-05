<%-- 
    Document   : index
    Created on : 20 de mar. de 2025, 13:34:32
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
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>


        <div class="conteudo" >
            <div >
                <div class="conteudoesquerda">
                    <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
                    <div id="botoes">

                        <a href="medicamento.jsp">                            
                            <button id="btngeral" type="submit" class="btn btn-primary" name="pMedicamento" >Medicamento</button>
                        </a>

                        <a href="paciente.jsp">                           
                            <button id="btngeral" type="submit" class="btn btn-primary"  name="pPaciente" >Paciente</button>
                        </a>

                        <a href="receita.jsp">                            
                            <button id="btngeral" type="submit" class="btn btn-primary"  name="pReceita">Receita</button>
                        </a>
                    </div>
                </div>

                <div class="conteudodireita" style="display: flex; flex-direction: column; align-items: center" >
                    <div>
                        <input type="texto" class="form-control" style="text-align: right" placeholder="Pesquisar">
                    </div>              

                    <div>


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