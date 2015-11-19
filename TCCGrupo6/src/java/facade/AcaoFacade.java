package facade;

import dao.AcaoDao;
import entidade.Acao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AcaoFacade {
     private Acao requestForm(HttpServletRequest request) {

        Acao retorno = new Acao();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtNome") != null) && (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        
         return retorno;
     }
     
         public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("AcaoIncluir.jsp");
        rd.forward(request, response);
    }
          public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Acao acao = new Acao();
        AcaoDao acaoDao = new AcaoDao();

        acao = requestForm(request);

        if (acaoDao.salvar(acao) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        }
    }
            public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Acao acao = new Acao();
        AcaoDao acaoDao = new AcaoDao();

        acao = requestForm(request);

        if (acaoDao.excluir(acao)) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }

}

