/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

/**
 *
 * @author dhuynh
 */
public class Check {
    public static boolean checkStringNumber(String s) {
        char[] arr = s.toCharArray();//Chuyển chuỗi vừa nhập sang mảng ký tự
        boolean flag = !(s.equalsIgnoreCase(""));//so sánh chuỗi vừa nhập có rỗng không, nếu không trả về false thì flag = true;       
        if (flag) {
            for (int x : arr) {
                if (x < '0' || x > '9') {
                    flag = false;
                }
            }
        }
        return flag;
    }
    
    public static boolean isNull(String s){
        return s.trim().equals("");
    }
}
