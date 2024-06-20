/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class MySQLConnect {
    String Host="localhost";
    String Database="maketmini";
    String Username="huynh";
    String Password="123456";
    //String url = "jdbc:mysql://localhost:3306/maketmini";
    public Connection connect=null;
    public Statement statement = null;
    public ResultSet resultset = null;

    public MySQLConnect() {
    }

    public MySQLConnect(String Host, String Database, String Username, String Password){
        this.Host=Host;
        this.Username=Username;
        this.Password=Password;
        this.Database=Database;
    }
    
    protected void driverTest() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw  new Exception("MySQL JDBC Driver not found");
        }
    }
    public Connection getConnect() throws Exception{
        if(this.connect==null){
            driverTest();
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database;
            try {
                this.connect=DriverManager.getConnection(url, this.Username, this.Password);
            } catch (Exception e) {
                throw  new Exception("Không thể kết nối Database Server: " + url + e.getMessage());
            }
        }
        return this.connect;
    }
    public Statement getStatement() throws Exception{
        if(this.statement==null ? true : this.statement.isClosed()){
            this.statement = this.getConnect().createStatement();
        }
        return this.statement;
    }
    public ResultSet excuteQuery(String Query) throws Exception{
        try {
            this.resultset = getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage() + " = " + Query);     
        }
        return this.resultset;
    }
    public int executeUpdate(String Query) throws Exception{
        int res = Integer.MIN_VALUE;
        try {
            res= getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw  new Exception("Erro: " + e.getMessage() + " = " + Query);
        }
        finally{
                    this.clone();
        }
        return res;
    }
    public void Close() throws SQLException{
        if(this.resultset!=null && ! this.resultset.isClosed()){
            this.resultset.close();
            this.resultset=null;
        }
        if(this.statement!=null && ! this.statement.isClosed()){
            this.statement.close();
            this.statement=null;
        }
        if(this.connect!=null && ! this.connect.isClosed()){
            this.connect.close();
            this.connect=null;
        }
    }
}
