import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TareasServlet
 */
public class TareasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TareasServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer= response.getWriter();
		writer.print(""
				+ "<html>"
				+ "<head>"
				+ "<title>agregar tarea</title>"
				+ "</head>"
				+ "<body>"
				+ "<form method=\"post\">"
    			+ "<table>"
				+ "<tr>"
				+ "<td>Titulo</td>"
				+ "<td>"
				+ "<input type=\"text\" name=\"titulo\">"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Categoria</td>"
				+ "<td>"
				+ "<input type=\"text\" name=\"categoria\">"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Prioridad</td>"
				+ "<td>"
				+ "<select name=\"prioridad\"");
		for(int i=0;i<11;i++){
			writer.print("<option value=\""+i+"\">"+i+"</option>");
		}
		
		writer.print("</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Descripcion</td>"
				+ "<td>"
				+ "<input type=\"text\" name=\"descripcion\">"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Propietario</td>"
				+ "<td>"
				+ "<input type=\"text\" name=\"propietario\">"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Estado</td>"
				+ "<td>"
				+ "<select name=\"Estado\"");
				
				
				for(Estado estado: Estado.values()){
					if(estado.equals(Estado.TO_DO)){
						writer.print("<option value=\""+estado.name()+" selected \">"+estado.name()+"</option>");
					}
					writer.print("<option value=\""+estado.name()+" \">"+estado.name()+"</option>");
				}
				
				
				
		writer.print("</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Fecha de entrega</td>"
				+ "<td>"
				+ "</select><select name=\"dia\"");
				
			for(int i=0;i<32;i++){
				writer.print( "<option value=\""+i+"\">"+i+"</option>");
			}
		writer.print("</select><select name=\"mes\"");
				
		
				for(Meses estado: Meses.values()){
					if(estado.equals(Meses.Enero)){
						writer.print("<option value=\""+estado.name()+" selected \">"+estado.name()+"</option>");
					}
					writer.print("<option value=\""+estado.name()+" \">"+estado.name()+"</option>");
				}
		writer.print("</select><select name=\"ano\"");
				
				

				for(int i=2009;i<2020;i++){
					writer.print("<option value=\""+i+"\">"+i+"</option>");
				}
				
		writer.print("</select></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ "<input type=\"submit\" name=\"agregar\" value=\"Agregar Tarea\">"
				+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</form>"
				+ "</body>"
				+ "</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer= response.getWriter();
		
//		Tarea a=new Tarea();
		Estado est=Estado.TO_DO;
		if(request.getParameter("Estado").equals("IN_PROGRESS")){
			est=Estado.IN_PROGRESS;
		}else if(request.getParameter("Estado").equals("DONE")){
			est=Estado.DONE;
		}
		datos d=new datos();
		d.validar(request);
		if(d.valido){
			Tarea tarea=new Tarea(d.datos[0],est,Integer.parseInt(request.getParameter("prioridad")));
			tarea.setCategoria(d.datos[1]);
			tarea.setDescripcion(d.datos[2]);
			tarea.setPropietario(d.datos[3]);
			Date f=new Date();
			tarea.setFechaDeCreacion(f);
			f=new Date(Integer.parseInt(request.getParameter("ano").trim()),d.mes(request.getParameter("mes")),Integer.parseInt(request.getParameter("dia").trim()));
			tarea.setFechaDeEntrega(f);
			programa.libreta.agragar(tarea);
			writer.print(""
					+ "<html>"
					+ "<head>"
					+ "<title>agregar tarea</title>"
					+ "</head>"
					+ "<body>"
					+ "La tarea se agrego con exito"
					+ "</body>"
					+ "</html>");
		}else{
			
			writer.print(""
					+ "<html>"
					+ "<head>"
					+ "<title>agregar tarea</title>"
					+ "</head>"
					+ "<body>"
					+ "El dato en "+d.fastidio+" esta Vacio"
					+ "</body>"
					+ "</html>");
		}
	}
	private static class datos{
		boolean valido=false;
		String[] datos=new String[4];
		String fastidio=null;
		public int mes(String S){
			switch(S.trim()){
			case "Enero": return 1;
			case "Febrero":return 2;
			case "Marzo":return 3;
			case "Abril":return 4;
			case "Mayo":return 5;
			case "Junio":return 6;
			case "Julio":return 7;
			case "Agosto":return 8;
			case "Septiemre":return 9;
			case "Octubre":return 10;
			case "Noviembre":return 11;
			case "Diciembre":return 12;
				default: return 0;
			}
		}
		public datos validar(HttpServletRequest re){
			datos[0]=re.getParameter("titulo");
			datos[1]=re.getParameter("categoria");
			datos[2]=re.getParameter("descripcion");
			datos[3]=re.getParameter("propietario");
			if(datos[0].trim().equalsIgnoreCase("")){
				fastidio="Titulo";
				return this;
			}else if(datos[1].trim().equalsIgnoreCase("")){
				fastidio="Categoria";
				return this;
			}else if(datos[2].trim().equalsIgnoreCase("")){
				fastidio="Descripcion";
				return this;
			}else if(datos[3].trim().equalsIgnoreCase("")){
				fastidio="Propietario";
				return this;
			}else {
				valido=true;
			}
			return this;
		}
	}
}