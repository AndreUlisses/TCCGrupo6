package dao;

import conexao.ConnectionManager;
import entidade.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class EquipeDao {
    
    
    
     public int salvar(Equipe equipe) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into EQUIPE (nome) values (?)";
            String QUERY_UPDATE = "update EQUIPE set nome = ? where idequipe = ? ";

            if (equipe.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, equipe.getNome());
               

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, equipe.getNome());
                 stmt.setInt(2, equipe.getId());

                stmt.executeUpdate();
                resultado = equipe.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }
     
     public boolean excluir(Equipe equipe) {
     boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from EQUIPE where idequipe = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, equipe.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }     
     
     public Equipe editar(int id) {

        Equipe equipe = new Equipe();
        
        try {

            String QUERY_DETALHE = "select * from EQUIPE where idequipe = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                equipe = new Equipe();
                equipe.setId(rs.getInt("idEquipe"));
                equipe.setNome(rs.getString("nome"));
                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            equipe = null;
            
        }
        
        return equipe;
    }
     
}
