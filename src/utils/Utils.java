/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tijana Lakic
 */
public class Utils {
    
public static Properties PROPERTIES;
public static String ROOT_RESOURCE = "./src/properties/";

static{

        try {
            PROPERTIES=new Properties();
            InputStream is = new FileInputStream(ROOT_RESOURCE+"config.properties");
            PROPERTIES.load(is);
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    
}
