/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.maps;

import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 *
 * @author koussai
 */
public class testMap {
           private maps.java.Geocoding ObjGeocoding=new maps.java.Geocoding();
           
    public  void point  () throws UnsupportedEncodingException, MalformedURLException {
    String Alt = null , Long = null  ;      
    
     
         //   JText_CD_DireEnc.setText("");
            Point2D.Double resultado=ObjGeocoding.getCoordinates("soukra");
           Alt =  (String.valueOf(resultado.x));
            Long = (String.valueOf(resultado.y));
          /*  JText_CD_DireEnc.setText(String.valueOf(ObjGeocoding.getAddressFound()));
            JText_CD_CodigPost.setText(ObjGeocoding.getPostalcode());
            JText_CD_Resolucion.setText(ObjGeocoding.getPostalcode());*/
        System.out.println("Alt = "+Alt +"Long= "+ Long);
    
    }
            
             public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException {
                 
             
       testMap M = new  testMap() ;  
       M.point(); 
    }
}
