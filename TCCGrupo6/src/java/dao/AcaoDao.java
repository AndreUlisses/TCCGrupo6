package dao;

import conexao.ConnectionManager;
import entidade.Acao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class AcaoDao {
    
     public int salvar(Acao acao) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into ACAO (nome) values (?)";
            String QUERY_UPDATE = "update ACAO set nome = ? where idacao = ? ";

            if (acao.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, acao.getNome());
               

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, acao.getNome());
                 stmt.setInt(2, acao.getId());

                stmt.executeUpdate();
                resultado = acao.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }
    

public boolean excluir(Acao acao) {
     boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from ACAO where idacao = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, acao.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }     
}