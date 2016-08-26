/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author yoshikawatoshio
 */
public class UserData implements Serializable {
    //user_t
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteFlg;
    
    public UserData() {
        //user_t 初期値を設定
        this.userID = 0;
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
        this.total = 0;
        this.newDate = null;
        this.deleteFlg = 0;
    }
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        //初期選択の場合0をセット
        if(userID.equals("")) {
            this.userID = 0;
        }else {
            this.userID = Integer.parseInt(userID);     //intに変換
        }
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合""をセット
        if(name.trim().length() == 0){
            this.name = "";
        }else{
            this.name = name;
        }
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        //空文字(未入力)の場合""をセット
        if(password.trim().length() == 0) {
            this.password = "";
        }else {
            this.password = password;
        }
    }
        
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        //空文字(未入力)の場合空文字をセット
        if(mail.trim().length() == 0) {
            this.mail = "";
        }else {
            this.mail = mail;
        }
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        //空文字(未入力)の場合空文字をセット
        if(address.trim().length()==0){
            this.address = "";
        }else{
            this.address = address;
        }
    }
    
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        
            this.total = total;       //intに変換
        
    }
    
    public Timestamp getNewDate() {
        return newDate;
    }
    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }
    
    public int getDeleteFlg() {
        return deleteFlg;
    }
    public void setDeleteFlg(String deleteFlg) {
        if(deleteFlg.equals("")) {
            this.deleteFlg = 0;
        }else {
            this.deleteFlg = Integer.parseInt(deleteFlg);
        }
    }

    
    //ユーザ情報登録の際の未入力チェックを実装
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")) {
            chkList.add("address");
        }
        
        return chkList;
    }
    
    //BD専用パラメータに変換(UserData→DTO)
    public void UDB2DTOMapping(UserDataDTO udd){
        udd.setUserID(this.userID);        
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
        udd.setDeleteFlg(this.deleteFlg);
    }
    
    //View用パラメータに変換(DTO→UserData)
    public void DTOUD2Mapping(UserDataDTO udd) {
        //int型をString型に変換
        String UserID = String.valueOf(udd.getUserID());
        String DeleteFlg = String.valueOf(udd.getDeleteFlg());
        
        //DTOの値をUserDataBeansに格納
        this.setUserID(UserID);
        this.setMail(udd.getMail());
        this.setPassword(udd.getPassword());
        this.setName(udd.getName());       
        this.setAddress(udd.getAddress());
        this.setTotal(udd.getTotal());
        this.setDeleteFlg(DeleteFlg);
        this.setNewDate(udd.getNewDate());
    }
}