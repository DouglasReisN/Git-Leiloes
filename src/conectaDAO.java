
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11atv1", "root", "Mangekyou55@");
            JOptionPane.showMessageDialog(null, "Sucesso de conexão");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "falha ao carregar classe de conexão");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL,falha ao conectar ao banco de dados");
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar");
        }
    }

}
