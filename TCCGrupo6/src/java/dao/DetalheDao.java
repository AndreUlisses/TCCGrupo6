package dao;

import conexao.ConnectionManager;
import entidade.Detalhe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DetalheDao {
     public int salvar(Detalhe detalhe) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into DETALHE (posx, posy) values (?, ?)";
            String QUERY_UPDATE = "update DETALHE set posx = ?, posy = ?,  where iddetalhe = ? ";

            if (detalhe.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, detalhe.getPosX());
                stmt.setString(2, detalhe.getPosY());
                

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, detalhe.getPosX());
                stmt.setString(2, detalhe.getPosY());
                stmt.setInt(4, detalhe.getId());

                stmt.executeUpdate();
                resultado = detalhe.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }
     
     public boolean excluir(Detalhe detalhe) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from DETALHE where iddetalhe = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, detalhe.getId());

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
