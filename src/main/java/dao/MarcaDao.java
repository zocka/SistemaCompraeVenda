package dao;

import model.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao {

    private static String sql = "";
    private static PreparedStatement pstm = null;
    private static ResultSet rs;

    public static boolean salvar(Marca obj) {
        if (obj.getId() == null) {
            sql = "INSERT INTO Marca (descricao) VALUES (?)";
        } else {
            sql = "UPDATE Marca descricao = ? WHERE id= ?";
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

    public static boolean excluir(Marca obj) {

        sql = "DELETE FROM marca WHERE id = ?";
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

    public static List<Marca> listar(String txtbusca) {

        List<Marca> lista = new ArrayList<>();

        try {
            if (txtbusca.isEmpty()) {
                sql = "SELECT FROM marca";
            } else {
                sql = "SELECT FROM marca WHERE descricao LIKE '" + txtbusca + "%'";
            }
            pstm = Conexao.getInstance().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Marca obj = new Marca();
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
