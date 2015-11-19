package facade;

import entidade.Participa;

import javax.servlet.http.HttpServletRequest;


public class ParticipaFacade {
    private Participa requestForm(HttpServletRequest request) {

        Participa retorno = new Participa();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtDt_Ingresso") != null) && (!request.getParameter("txtDt_Ingresso").equals(""))) {
            retorno.setDt_Ingresso(request.getParameter("txtDt_Ingresso"));
        }
       return retorno;
}
}
