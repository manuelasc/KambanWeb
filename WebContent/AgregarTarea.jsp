<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AGREGAR TAREA</title>
</head>
<body>
<script>
function alertar(msg){
	alert(msg+" esta vacio")
	}
	function validar(){
		var titulo=document.forms["formularioTarea"]["titulo"].value;
		var descripcion=document.forms["formularioTarea"]["descripcion"].value;
		var categoria=document.forms["formularioTarea"]["categoria"].value;
		var propietario=document.forms["formularioTarea"]["propietario"].value;
		if(!titulo){alertar("titulo");
		return false;
		}else if(!categoria){
			alertar("categoria");
		return false;
		}else if(!descripcion){
			alertar("descripcion");
		return false;
		}else if(!propietario){alertar("propietario");
		return false;
		}
		}
	</script>
<form method="post" action="TareasServlet">
    <table>
	<tr>
	<td>Titulo</td>
	<td>
	<input type="text" name="TITULO">
	</td>
	</tr>
	<tr>
	<td>Categoria</td>
	<td>
	<input type="text" name="CATEGORIA">
	</td>
	</tr>
	<tr>
	<td>Prioridad</td>
	<td>
	<select name="PRIORIDAD">
	<%
		for(int i=1;i<11;i++){
			out.print("<option value=\""+i+"\">"+i+"</option>");
		}
	%>
	</select>	
	</td>
	</tr>
	<tr>
	<td>Descripcion</td>
	<td>
	<input type="text" name="DESCRIPCION">
	</td>
	</tr>
	<tr>
	<td>Propietario</td>
	<td>
	<input type="text" name="PROPIEDAD">
	</td>
	</tr>
	<tr>
	<td>Estado</td>
	<td>
	<select name="ESTADO">
	<%			
				for(Estado estado: Estado.values()){
					out.print("<option value=\""+estado.name()+" \">"+estado.name()+"</option>");
				}
	%>			
				
				
	</td>
	</tr>
	<tr>
	<td>Fecha de entrega</td>
	<td>
	
	<select name="DIA">
	<%			
			for(int i=1;i<32;i++){
				out.print( "<option value=\""+i+"\">"+i+"</option>");
			}
		out.print("</select><select name=\"MES\"");
				
		
				for(Meses estado: Meses.values()){
					if(estado.equals(Meses.Enero)){
						out.print("<option value=\""+estado.name()+" selected \">"+estado.name()+"</option>");
					}
					out.print("<option value=\""+estado.name()+" \">"+estado.name()+"</option>");
				}
		out.print("</select><select name=\"AÑO\"");
				
				

				for(int i=2009;i<2020;i++){
					out.print("<option value=\""+i+"\">"+i+"</option>");
				}
		%>		
		</select>
		</td>
		</tr>
		<tr>
		<td>
		<input type="submit" name="AGREGAR" value="AGREGAR TAREA">
				</td>
				</tr>
				</table>
				</form>

<%--
<%  
	String[] Values={"hola","adios"};
	String prueva=request.getParameter("test");
	out.println(prueva);
	out.print("hola mundo " + new Date().toString());
	 %>
	 <%@ include file="derechos.jsp" %>--%>

</body>
</html>