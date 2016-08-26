/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;
import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *ユーザー情報・商品購入情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 
 */
/**
 *
 * @author yoshikawatoshio
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * ユーザーデータの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 
     * @throws SQLException 
     */
    public void UserInsert(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());     
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            
            System.out.println("insert completed!!");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public UserDataDTO UserSearch(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM user_t WHERE name like ? AND password like ?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));
            
            System.out.println("search completed!!");
            
            return resultUd;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally {
            if(con != null) {
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDによるデータの更新処理を行う。更新日時は更新する直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void UserUpdate(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "UPDATE user_t SET name=?, password=?, mail=?, address=?, newDate=? WHERE userID= ?" ;
            st =  con.prepareStatement(sql);
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6,ud.getUserID());
            st.executeUpdate();
            
            System.out.println("update completed");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        } 
    }
    
    /**
     * 疑似削除のためユーザーIDによるdeleteFlgデータの更新処理を行う。
     * @param ud     
     * @throws java.sql.SQLException     
     */
    public void UserDeleteFlg(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "UPDATE user_t SET deleteFlg=? WHERE userID= ?" ;
            st =  con.prepareStatement(sql);        
            st.setInt(1, 1);
            st.setInt(2,ud.getUserID());
            st.executeUpdate();
            
            System.out.println("update completed");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        } 
    }
    
    /**
     * 商品購入データの挿入処理を行う。現在時刻は挿入直前に生成 
     * @param udd
     * @throws SQLException 
     */
    public void BuyInsert(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,newDate) VALUES(?,?,?,?)");
            st.setInt(1, udd.getUserID());
            st.setString(2, udd.getItemCode());
            st.setInt(3, udd.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            
            System.out.println("insert completed!!");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDによる商品購入データの検索処理を行う
     * @param ud
     * @return 検索結果
     * @throws SQLException 
     */
    public ArrayList<UserDataDTO> SearchByUserID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM buy_t WHERE userID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            
            ArrayList<UserDataDTO> uddList = new ArrayList<UserDataDTO>();
            while(rs.next()) {
                UserDataDTO udd = new UserDataDTO();
                udd.setBuyID(rs.getInt(1));
                udd.setUserID(rs.getInt(2));
                udd.setItemCode(rs.getString(3));
                udd.setType(rs.getInt(4));
                udd.setNewDate(rs.getTimestamp(5));
                
                uddList.add(udd);
            }

            System.out.println("SearchByUserID complete");

            return uddList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * 総購入金額をuse_tへ追加する
     * @param ud
     * @throws SQLException 
     */
    public void UserTotal(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "UPDATE user_t SET total=? WHERE userID= ?" ;
            st =  con.prepareStatement(sql);        
            st.setInt(1, ud.getTotal());
            st.setInt(2,ud.getUserID());
            st.executeUpdate();
            
            System.out.println("totalupdate completed");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        } 
    }

    void updateTotal(int parseInt, int userID) {
        

}
}