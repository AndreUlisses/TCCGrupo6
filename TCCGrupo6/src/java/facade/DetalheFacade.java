package facade;


import dao.AcaoDao;
import dao.DetalheDao;
import entidade.Acao;
import entidade.Detalhe;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DetalheFacade {
       private Detalhe requestForm(HttpServletRequest request) {

        Detalhe retorno = new Detalhe();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtPosX") != null) && (!request.getParameter("txtPosX").equals(""))) {
            retorno.setPosX(request.getParameter("txtPosX"));
        }
        if ((request.getParameter("txtPosY") != null) && (!request.getParameter("txtPosY").equals(""))) {
            retorno.setPosY(request.getParameter("txtPosY"));
        }
       

        return retorno;
    }
       
        public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("DetalheIncluir.jsp");
        rd.forward(request, response);
    }
          public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Detalhe detalhe = new Detalhe();
        DetalheDao detalheDao = new DetalheDao();

        detalhe = requestForm(request);

        if (detalheDao.salvar(detalhe) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        }
    }
            public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Detalhe detalhe = new Detalhe();
        DetalheDao detalheDao = new DetalheDao();

        detalhe = requestForm(request);

        if (detalheDao.excluir(detalhe)) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
