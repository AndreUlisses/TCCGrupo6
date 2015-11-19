package facade;


import dao.PapelDao;
import entidade.Papel;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PapelFacade {
         private Papel requestForm(HttpServletRequest request) {

        Papel retorno = new Papel();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtNome") != null) && (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        
         return retorno;
     }
     
         public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("PapelIncluir.jsp");
        rd.forward(request, response);
    }
          public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Papel papel = new Papel();
        PapelDao papelDao = new PapelDao();

        papel = requestForm(request);

        if (papelDao.salvar(papel) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        }
    }
            public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Papel papel = new Papel();
        PapelDao papelDao = new PapelDao();

        papel = requestForm(request);

        if (papelDao.excluir(papel)) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }

}

