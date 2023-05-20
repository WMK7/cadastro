package Controller;

import Model.Cliente;
import Model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private String consultarFornecedor = "SELECT * FROM fornecedor;";
    private String consultarFornecedorNome = "SELECT * FROM fornecedor WHERE fornecedor.nome LIKE ?;";
    private String incluirFornecedor = "INSERT INTO fornecedor(nome, endereco, bairro, cidade, uf, cep, telefone, email)values (?,?,?,?,?,?,?,?);";
    private String alterarFornecedor = "UPDATE fornecedor SET nome = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE fornecedor.id = ?;";
    private String excluirFornecedor = "DELETE FROM fornecedor WHERE fornecedor.id = ?;";
    
    public List<Fornecedor> consultarCliente(){
        List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        Fornecedor fornecedor;        
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarFornecedor);
            rs = pst.executeQuery();            
            while(rs.next()){
                fornecedor = new Fornecedor();
                //setando atributos
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                //adicionando na lista
                listaFornecedores.add(fornecedor);
             }
            Conexao.desconectar(Conexao.conectar());
         } catch (Exception e) {
             e.printStackTrace();
        }
        return listaFornecedores;
    }
    
    
    public List<Fornecedor> consultaFornecedorNome(String nome){
        List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
        Fornecedor fornecedor;
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultarFornecedorNome);
            //nome = "%"+nome +"%";
            pst.setString(1, nome);
            rs = pst.executeQuery();
            
            while(rs.next()){
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                
                listaFornecedor.add(fornecedor);
            }
            Conexao.desconectar(Conexao.conectar());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFornecedor;
    }
    
    public boolean icluirFornecedor(Fornecedor fornecedor){
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());
            
            pst.executeUpdate();
            
            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean alterarFornecedor(Fornecedor fornecedor){
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());
            
            pst.setInt(9, fornecedor.getId());
            
            pst.executeUpdate();
            
            Conexao.desconectar(Conexao.conectar());
          } catch (Exception e) {
              e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean excluirFornecedor(Fornecedor fornecedor){
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirFornecedor);
            
            pst.setInt(1,fornecedor.getId());
            
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
