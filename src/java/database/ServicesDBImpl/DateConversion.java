/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import java.util.Date;

/**
 *
 * @author Jessica
 */
public class DateConversion {
    
    public static String DateConvert(Date utilDate) {    

        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        return sqlDate.toString();   
    }

    public static String TimeConvert(Date utilDate) {
        
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        
        return sqlTime.toString();
    }
    
}