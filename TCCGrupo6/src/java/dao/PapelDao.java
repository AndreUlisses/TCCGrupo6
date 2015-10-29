

package dao;

import conexao.ConnectionManager;
import entidade.Papel;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PapelDao {
    
    public int salvar(Papel papel) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into PAPEL (nome) values (?)";
            String QUERY_UPDATE = "update PAPEL set nome = ? where idpapel = ? ";

            if (papel.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, papel.getNome());
               

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, papel.getNome());               
                stmt.setInt(4, papel.getId());

                stmt.executeUpdate();
                resultado = papel.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }
    
  public boolean excluir(Papel papel) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from PAPEL where idpapel = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, papel.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }
  
  public Papel editar(int id) {

        Papel papel = new Papel();
        
        try {

            String QUERY_DETALHE = "select * from PAPEL where idpapel = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                papel = new Papel();
                papel.setId(rs.getInt("idPapel"));
                papel.setNome(rs.getString("nome"));
                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            papel = null;
            
        }
        
        return papel;
    }

}
