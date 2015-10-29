
package servlet;

import facade.UsuarioFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("txtObjeto").equals("Usuario")) {
            
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            switch (request.getParameter("txtMetodo")) {
                case "Cadastrar":
                    usuarioFacade.incluir(request, response);
                    break;
                case "Salvar":
                    usuarioFacade.salvar(request, response);
                    break;
                case "Editar":
                    usuarioFacade.editar(request, response);
                    break;
                case "Listar":
                    usuarioFacade.listar(request, response);
                    break;
                case "Excluir":
                    usuarioFacade.excluir(request, response);
                    break;
            }
        }
    }

        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
    
