<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="<c:url value='/resources/favicon.png'/>" />
    <title>Desafio Conductor</title>
    <link rel="stylesheet" href="<c:url	value='/resources/bootstrap/css/bootstrap.min.css'/>">
  </head>
  <body>
    <div style="margin-top: 10px">
      <img src="<c:url value='/resources/logotipo.png'/>" />
    </div>
    <div class="container" style="margin-top: 20px">
      <div class="row" style="position: relative">
        <div class="col-sm-12 col-md-12 col-lg-12">
          <p style="text-align: center">Olá senhores! Esta pagina lhes dará três funções que podem ser realizadas de acordo
            com a documentação recebida.
          <ul>
            <li>Gerar Dados: Gera uma massa de dados para a base de dados.</li>
            <li>Gerar Arquivo: Gera um .txt de transações de acordo com a data enviada ao servidor.</li>
            <li>Ver JavaDoc: Abre a documentação JavaDoc da Aplicação.</li>
          </ul>
          </p>
        </div>
        <div class="col-sm-2 col-md-2 col-lg-2">
          <c:url var="postFirstUrl" value="/generateData"/>
          <form:form target="_blank" class="form-horizontal" commandName="transacao" method="POST" action="${postFirstUrl}">
            <div class="form-group" style="margin-top:53%;">
              <div class="col-sm-12 col-md-12 col-lg-12">
                <input class="btn btn-primary" type="submit" value="Gerar Dados">
              </div>
            </div>
          </form:form>
        </div>
        <div class="col-sm-8 col-md-8 col-lg-8">
          <c:url var="postSecondUrl" value="/generateFile"/>
          <form:form class="form-horizontal" commandName="transacao" method="POST" action="${postSecondUrl}">
            <div class="form-group">
              <div class="col-sm-8 col-md-12 col-lg-12">
                <label class="control-label" for="data">Data:</label>
                <form:input class="form-control" path="data" type="text" id="data" name="data" />
                <form:errors path="data" />
              </div>
            </div>
            <h5 style="text-align: center">${successFile}</h5>
            <div class="form-group">
              <div class="col-sm-12 col-md-12 col-lg-12" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="Gerar Arquivo">
              </div>
            </div>
          </form:form>
        </div>
        <div class="col-sm-2 col-md-2 col-lg-2">
          <div class="form-group" style="margin-top:53%;">
            <a type="button" class="btn btn-primary" href="resources/apidocs/" target="_blank" >Ver JavaDoc</a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
