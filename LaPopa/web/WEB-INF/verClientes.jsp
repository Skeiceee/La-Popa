<%--
    Document   : verClientes
    Created on : 24-09-2018, 15:26:11
    Author     : Q w e r t y
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="jspf/header.jspf"%>
<div class="row">
  <div id="mostrar_cliente" class="col-lg-8 col-sm col-md-8 mx-auto mt-4">
    <div class="card ">
        <div class="card-header">
          <span>Clientes</span>
        </div>
        <div class="card-body">
          <table id="mostrar_producto" class="table table-bordered table-hover table-striped">
            <thead class = "theade-danger">
              <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Estado</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.clientes}" var="t">
                    <tr>
                        <td>${t.nombre}</td>
                        <td>${t.apellidos}</td>
                        <td>${t.estado}</td>
                    </tr>
                </c:forEach>
                <c:forEach items="${requestScope.FiltradoCli}" var="t">
                    <tr>
                        <td>${t.nombre}</td>
                        <td>${t.apellidos}</td>
                        <td>${t.estado}</td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
        <form class="form-group mt-4" action="VerClientesController.do" method="post">
          <div class="row">
            <div class="col-lg-6 col-sm col-md-6 mx-auto">
                <div class="form-group">

                  <select class="form-control" name="estado" id="estado">
                        <c:forEach var="estado" items="${requestScope.estado}">
                            <option value="${estado}">${estado}</option>      
                        </c:forEach>
                  </select>
                  <button class="btn btn-primary btn-block mt-2" type="submit" name="button">Filtrar</button>
                </div>
            </div>
          </div>
        </form>
        </div>
      </div>
  </div>
</div>
<%@include file="jspf/footer.jspf"%>
