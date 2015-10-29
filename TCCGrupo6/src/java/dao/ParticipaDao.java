package dao;

import conexao.ConnectionManager;
import entidade.Participa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ParticipaDao {
     public int salvar(Participa participa) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into PARTICIPA (dt_ingresso) values (?)";
            String QUERY_UPDATE = "update PARTICIPA set dt_ingresso = ? where idparticipa = ? ";

            if (participa.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, participa.getDt_Ingresso());
              

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, participa.getDt_Ingresso());                
                stmt.setInt(4, participa.getId());

                stmt.executeUpdate();
                resultado = participa.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    
    public boolean excluir(Participa participa) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from PARTICIPA where idparticipa = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, participa.getId());

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
