<%--
    Document   : agregarCliente
    Created on : 23-09-2018, 20:48:44
    Author     : Q w e r t y
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="jspf/header.jspf"%>
<form class="form-group mt-4" action="AgregarClientesController.do" method="post">
    <div class="row">
      <div class="col-lg-6 col-sm-12 col-md-6 mx-auto">
            <c:if test="${not empty requestScope.errorRut}">
                 <div class = "alert alert-danger">
                     ${requestScope.errorRut}
                 </div>
            </c:if>
            <c:if test="${not empty requestScope.NoValidoNDS}">
                 <div class = "alert alert-danger">
                     ${requestScope.NoValidoNDS}
                 </div>
            </c:if>
            <c:if test="${not empty requestScope.errorVacio}">
                 <div class = "alert alert-danger">
                     ${requestScope.errorVacio}
                 </div>
            </c:if>
            <c:if test="${not empty requestScope.mensaje}">
                <div class = "alert alert-info">
                    ${requestScope.mensaje}
                </div>
            </c:if>
        <div class="card">
          <div class="card-header">
            <span>Ingresar cliente</span>
          </div>
          <div class="card-body">
            <div class="form-group">
              <label for="rut">Rut <small>(Sin puntos y con guión)</small></label>
              <input class="form-control" type="text" name="rut" id="rut">
            </div>
            <div class="form-group">
                <label for="numeroDeSerie">Numero de serie <small>(Sin puntos)</small></label>
              <input class="form-control" type="text" name="numeroDeSerie" id="numeroDeSerie">
            </div>
            <div class="form-group">
              <label for="nombre">Nombre</label>
              <input class="form-control" type="text" name="nombre" id="nombre">
            </div>
            <div class="form-group">
              <label for="apellidos">Apellidos</label>
              <input class="form-control" type="text" name="apellidos" id="apellidos">
            </div>
            <label for="estado">Estado</label>
            <select class="form-control" name="estado" id="estado">
                <c:forEach var="estado" items="${requestScope.estado}">
                    <option value="${estado}">${estado}</option>      
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary btn-block mt-4">Ingresar</button>
          </div>
        </div>
      </div>
    </div>
</form>
<%@include file="jspf/footer.jspf"%>
