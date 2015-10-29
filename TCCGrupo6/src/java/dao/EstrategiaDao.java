package dao;

import conexao.ConnectionManager;
import entidade.Estrategia;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EstrategiaDao {
    
     public int salvar(Estrategia estrategia) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into ESTRATEGIA (nome, tipo, mapa) values (?, ?, ?)";
            String QUERY_UPDATE = "update ESTRATEGIA set nome = ?, tipo = ?, mapa = ? where idestrategia = ? ";

            if (estrategia.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, estrategia.getNome());
                stmt.setString(2, estrategia.getTipo());
                stmt.setString(3, estrategia.getMapa());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, estrategia.getNome());
                stmt.setString(2, estrategia.getTipo());
                stmt.setString(3, estrategia.getMapa());
                stmt.setInt(4, estrategia.getId());

                stmt.executeUpdate();
                resultado = estrategia.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

      public boolean excluir(Estrategia estrategia) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from ESTRATEGIA where idestrategia = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, estrategia.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }
          public Estrategia editar(int id) {

        Estrategia estrategia = new Estrategia();
        
        try {

            String QUERY_DETALHE = "select * from ESTRATEGIA where idestrategia = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                estrategia = new Estrategia();
                estrategia.setId(rs.getInt("idEstrategia"));
                estrategia.setNome(rs.getString("nome"));
                estrategia.setMapa(rs.getString("mapa"));
                estrategia.setTipo(rs.getString("tipo"));
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            estrategia = null;
            
        }
        
        return estrategia;
    }
}
