package facade;


import dao.EquipeDao;
import entidade.Equipe;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EquipeFacade {
    
 private Equipe requestForm(HttpServletRequest request) {

        Equipe retorno = new Equipe();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtNome") != null) && (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        
         return retorno;
     }
     
         public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("EquipeIncluir.jsp");
        rd.forward(request, response);
    }
          public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Equipe equipe = new Equipe();
        EquipeDao equipeDao = new EquipeDao();

        equipe = requestForm(request);

        if (equipeDao.salvar(equipe) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        }
    }
            public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           Equipe equipe = new Equipe();
        EquipeDao equipeDao = new EquipeDao();

        equipe = requestForm(request);

        if (equipeDao.excluir(equipe)) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }

}

