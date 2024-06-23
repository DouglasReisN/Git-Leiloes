
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    public static boolean venderProduto(ProdutosDTO p) {   //ATUALIZAÇÂO STATUS
        conectaDAO conexao = new conectaDAO();
        conexao.conectar();

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setInt(1, p.getId());

            consulta.execute();
            conexao.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar venda!");
            return false;
        }

    }

    public static List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM ProdutoVendido WHERE status = 'Vendido';";    // View sql para busca de produtos "Vendidos"
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            ResultSet resultset = query.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                produtosVendidos.add(produto);
            }

            conexao.desconectar();
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos vendidos: " + e.getMessage());
        }

        return produtosVendidos;

    }

    public static boolean cadastrarProduto(ProdutosDTO produto) {

        conectaDAO conexao = new conectaDAO();
        conexao.conectar();
        String sql = " INSERT INTO produtos (id,nome,valor,status)values(?,?,?,?)";       //  Inserir dados
        try {
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setInt(1, produto.getId());
            consulta.setString(2, produto.getNome());
            consulta.setInt(3, produto.getValor());
            consulta.setString(4, produto.getStatus());

            consulta.execute(); //executando a consulta sql
            conexao.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar registro no banco de dados");
            return false;
        }

    }

    public static List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> lista = new ArrayList<>();

        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            // /Sql
            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                lista.add(p);
            }
            conexao.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar registro no banco");
            System.out.println(e.getMessage());
        }

        return lista;
    }

}
