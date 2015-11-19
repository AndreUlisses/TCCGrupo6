package facade;



import dao.EstrategiaDao;
import dao.UsuarioDao;
import entidade.Estrategia;
import entidade.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EstrategiaFacade {
        private Estrategia requestForm(HttpServletRequest request) {

        Estrategia retorno = new Estrategia();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtNome") != null) && (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        if ((request.getParameter("txtTipo") != null) && (!request.getParameter("txtTipo").equals(""))) {
            retorno.setTipo(request.getParameter("txtTipo"));
        }
        if ((request.getParameter("txtMapa") != null) && (!request.getParameter("txtMapa").equals(""))) {
            retorno.setMapa(request.getParameter("txtMapa"));
        }

        return retorno;
    }

          public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("EstrategiaIncluir.jsp");
        rd.forward(request, response);
    }
           public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Estrategia estrategia = new Estrategia();
        EstrategiaDao estrategiaDao = new EstrategiaDao();

        estrategia = requestForm(request);
        estrategia = estrategiaDao.editar(estrategia.getId());

        if (estrategia != null) {
            request.setAttribute("estrategia", estrategia);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }
          public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Estrategia estrategia = new Estrategia();
        EstrategiaDao estrategiaDao = new EstrategiaDao();

        estrategia = requestForm(request);

        if (estrategiaDao.salvar(estrategia) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        }
    }
            public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Estrategia estrategia = new Estrategia();
        EstrategiaDao estrategiaDao = new EstrategiaDao();

        estrategia = requestForm(request);

        if (estrategiaDao.excluir(estrategia)) {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemOk.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("MensagemErro.jsp");
            rd.forward(request, response);
        }
    }

}

