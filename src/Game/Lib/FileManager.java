package Lib;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Security.Crypto;
import Security.trippleDES;

public class FileManager {
	
	
    public void WriteObjectToFile(String filepath,Object serObj) {
    	 
        try {
 
			FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Object ReadObjectToFile(String filepath) {
   	 
        try {
 
			InputStream fileOut = new FileInputStream(filepath);
            ObjectInputStream objectOut = new ObjectInputStream(fileOut);
            objectOut.close();

            return objectOut.readObject();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return null;
    }
    public BufferedImage loadImage(String filepath){
    	
    
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File(filepath));
    } catch (IOException e) {
    }
	return img;
    }
	
	public ArrayList ReadFileLines (String filepath){
	      ArrayList<String> arrlist = new ArrayList<String>(0);		
	      try {
			Scanner scanner = new Scanner(new File(filepath));
			while (scanner.hasNextLine()) {
				arrlist.add(scanner.nextLine());
				//System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return arrlist;
	}


}
