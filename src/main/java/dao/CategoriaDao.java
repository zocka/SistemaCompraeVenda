package dao;

import model.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private static String sql = "";
    private static PreparedStatement pstm = null;
    private static ResultSet rs;

    public static boolean salvar(Categoria obj) {
        if (obj.getId() == null) {
            sql = "INSERT INTO categoria (descricao) VALUES (?)";
        } else {
            sql = "UPDATE categoria descricao = ? WHERE id= ?";
        }
        try {
            pstm = Conexao.getInstance().prepareStatement(sql);
            pstm.setString(1, obj.getDescricao());
            if (obj.getId() != null) {
                pstm.setLong(2, obj.getId());
            }
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e);
        }
        return false;
    }

    public static boolean excluir(Categoria obj) {

        sql = "DELETE FROM categoria WHERE id = ?";
        try {
            pstm = Conexao.getInstance().prepareStatement(sql);
            pstm.setString(1, obj.getDescricao());
            if (obj.getId() != null) {
                pstm.setLong(2, obj.getId());
            }
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e);
        }

        return true;
    }

    public static List<Categoria> listar(String txtbusca) {

        List<Categoria> lista = new ArrayList<>();

        try {
            if (txtbusca.isEmpty()) {
                sql = "SELECT FROM categoria";
            } else {
                sql = "SELECT FROM categoria WHERE descricao LIKE '" + txtbusca + "%'";
            }
            pstm = Conexao.getInstance().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setId(rs.getLong("id"));
                obj.setDescricao(rs.getString("descricao"));
                lista.add(obj);

            }
        } catch (Exception e) {
        } finally {
            return lista;
        }

    }

}
