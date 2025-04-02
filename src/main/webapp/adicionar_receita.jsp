<%-- 
    Document   : adicionar_receita
    Created on : 19 de mar. de 2025, 06:55:08
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
        <link rel="stylesheet" href="css/styleadicionarmedicamento.css">
    </head>

    <body>


        <div class="conteudo">
            <div class="row">
                <div class="conteudoesquerda" style="display: flex; justify-content: center; align-items: center;">
                    <img src="imagens/medamor.png" width="300px" class="img-fluid" alt="Imagem responsiva">
                </div>


                <div class="conteudodireita" style="padding: 100px;">
                    <h1 class="display-4" style="color: #4D3407; margin-bottom: 30px;">Adicionar Receita</h1>
                    <div>
                        <h4 style="color: #4D3407; margin-bottom: 30px;">Informações da Receita</h4>
                    </div>

                    <div style="display: flex; flex-direction: column; gap: 10px; align-items: center;">

                        <form action="action" method="post">

                            <label for="paciente"> Escolha um Paciente</label>

                            <select name="paciente" id="inPaciente"> 

                                <option value="1">p1</option>
                                <option value="2">p2</option>
                                <option value="3">p3</option>
                                <option value="4">p4</option>

                            </select> 

                            <br>

                            <label for="medicamento"> Escolha um Medicamento</label>

                            <select name="medicamento" id="inMedicamento"  > 

                                <option value="1">m1</option>
                                <option value="2">m2</option>
                                <option value="3">m3</option>
                                <option value="4">m4</option>


                            </select>                 

                            <input type="text" class="form-control" placeholder="Nome do médico" style="color: #4D3407;">

                            <div class="d-flex gap-2 justify-content-center">
                                <button id="btnsalvar" type="submit" class="btn btn-primary">Salvar</button>

                                <a href="receita.jsp">
                                    <button id="btncancelar" type="button" class="btn btn-secondary">Cancelar</button>
                                </a>
                            </div>

                        </form>



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