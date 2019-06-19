package ProjetoJavaGUI;

import java.sql.*;
import java.util.*;

public class ProdutoDAO {
    
    private Connection con;
    
    public ProdutoDAO(Connection con){
        setCon(con);
    }
    
    public Connection getCon(){
        return con;
    }
    
    public void setCon(Connection con){
        this.con = con;
    }
    
    public String inserir(ProdutoBean produto){
        
        String sql = "insert into produtos(codigo,nome,preco,categoria)values(?,?,?,?)";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getPreco());
            ps.setString(4, produto.getCategoria());
            
            if(ps.executeUpdate() > 0){
                return "Inserido com sucesso.";
            }else{
                return "Falha na inserção.";
            }
        }catch(SQLException e){
            return e.getMessage();
        }
    }
    
    public String alterar(ProdutoBean produto){
        
        String sql = "update produtos set nome=?, preco=?, categoria=? where=codigo?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getPreco());
            ps.setString(3, produto.getCategoria());
            
            if(ps.executeUpdate() > 0){
                return "Alterado com sucesso";
            }else{
                return "Falha ao alterar";
            }
        }catch(SQLException e){
            return e.getMessage();
        }
    }
    
    public String excluir(ProdutoBean produto){
        
        String sql = "delete from produtos where codigo=?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, produto.getCodigo());
            
            if(ps.executeUpdate() > 0){
                return "Excluído com sucesso";
            }else{
                return "Falha ao alterar";
            }
        }catch(SQLException e){
            return e.getMessage();
        }
    }
    
    public List<ProdutoBean> listarTodos(){
        
        String sql = "select * from produtos";
        
        List<ProdutoBean> listaProduto = new ArrayList<ProdutoBean>();
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    ProdutoBean pb = new ProdutoBean();
                    pb.setCodigo(rs.getString(1));
                    pb.setNome(rs.getString(2));
                    pb.setPreco(rs.getString(3));
                    pb.setCategoria(rs.getString(4));
                    listaProduto.add(pb);
                }
                return listaProduto;
            }else {
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }
}
