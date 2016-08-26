/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
 
package Kagoyume;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 追加要件:ユーザ名とパスワードが重複していると新規会員登録出来なくする。
 * @author 8mile_000
 */
public class RegistrationLogic {
    /*public RegistrationLogic(){}
    
    public static RegistrationLogic getInstance(){
        return new RegistrationLogic();
    }
    //ユーザー名とパスワードが重複していない(true)の場合登録可能、falseの場合は登録不可能
    public boolean checkUserDataInDB (UserData udb) throws SQLException{
        UserDataDTO dto = new UserDataDTO();
        udb.UDB2DTOMapping(dto);
        /*return UserDataDAO.getInstance().selectUserData(dto);
       
    }
    public void insertUserData2DB (UserData udd) throws SQLException{
        UserDataDTO dto = new UserDataDTO();
        udd.UDB2DTOMapping(dto);
        UserDataDAO.getInstance().UserInsert(dto);
    }
    
    public List<String> caution (String userName, String password,String mail,String address){
        List<String> caution = new ArrayList();
        if("".equals(userName)){
            caution.add("userName");
        }
        if("".equals(password)){
            caution.add("password");
        }
        if("".equals(address)){
            caution.add("address");
        }
        return caution;
    }*/
}
