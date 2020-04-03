package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDao {

    private static String sql = "";
    private static PreparedStatement pstm = null;
    private static ResultSet rs;

    public static boolean salvar(Produto obj) {
        if (obj.getId() == null) {
            sql = "INSERT INTO produto (descricao, preco_custo, preco_venda, saldo, marca_id, categoria_id) "
                    + " VALUES "
                    + "(?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE produto descricao = ?,preco_custo  = ?, preco_venda = ?, saldo  = ?, marca_id = ?, categoria_id = ? WHERE id= ?";
        }

        try {
            pstm = Conexao.getInstance().prepareStatement(sql);
            pstm.setString(1, obj.getDescricao());
            pstm.setDouble(2, obj.getPrecoCusto());
            pstm.setDouble(3, obj.getPrecoVenda());
            pstm.setInt(4, obj.getSaldo());
            pstm.setLong(5, obj.getMarca().getId());
            pstm.setLong(6, obj.getCategoria().getId());
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

    public static boolean excluir(Produto obj) {

        sql = "DELETE FROM produto WHERE id = ?";
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
    
    public static List<Produto> listar(String txtbusca) {

        List<Produto> lista = new ArrayList<>();

        try {
            if (txtbusca.isEmpty()) {
                sql = "SELECT FROM produto";
            } else {
                sql = "SELECT FROM marca WHERE produto LIKE '" + txtbusca + "%'";
            }
            pstm = Conexao.getInstance().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getLong("id"));
                obj.setDescricao(rs.getString("descricao"));
                lista.add(obj);

            }
        } catch (Exception e) {
        } finally {
            return lista;
        }
}
