package com.gmail.sitoa.printermain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.sitoa.mathclass.Point_3;

public class Startclass {
	public static File file;
	private static double nozuru;
	public static Point_3 highest;
	public static Point_3 lowest;
	public static String shapename= "";
	public static int shapekazu=0;
	public static HashMap<Integer, Point_3> shapepointa = new HashMap<Integer, Point_3>();
	public static HashMap<Integer, Point_3> shapepointb = new HashMap<Integer, Point_3>();
	public static HashMap<Integer, Point_3> shapepointc = new HashMap<Integer, Point_3>();
	
	static ArrayList<String> array;
	public static void main(String[] args) {
        
		System.out.println("Starting...");
		Fileload.fileload();
		

	}
	public static void setfile(File f) {
		file = f;
		
	}
	public static void setnozuru(double d){
		nozuru = d;
	}
	public static void setpoint(HashMap<Integer,Point_3> pointa,HashMap<Integer,Point_3> pointb,HashMap<Integer,Point_3> pointc){
	shapepointa = pointa;
	shapepointb = pointb;
	shapepointc = pointc;
		
	}
	public static void sethighest(Point_3 a){
		highest = a;
		
	}
	public static void setlowest(Point_3 a){
		lowest = a;
		
	}
	public static void setname(String a){
		shapename = a;
	}
	public static void setshapekazu(int i){
		shapekazu = i;
	}
	//データーから平面配列データに変換
	public static void caliculation(){
		CaliculationClass cali = new CaliculationClass();
		cali.setPoints(shapepointa, shapepointb, shapepointc);
		cali.setNozuru(nozuru);
		cali.sethigh(highest);
		cali.setlow(lowest);
		System.out.println("Caliculation starting...");
		cali.CaliculationStart();
	}
	
	
	//ファイロード完了→ファイル内データー読み取り
	public static void fileok() throws InterruptedException{
		Thread.sleep(1);
		array = new ArrayList<String>();
		  try {
			FileReader stlfile = new FileReader(file);
			BufferedReader b = new BufferedReader(stlfile);
			String s = null;
			try {
				while((s = b.readLine())!=null){
				    System.out.println(s);
				    array.add(s);
				    
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TextloadClass load = new TextloadClass();
			load.setnozuru(nozuru);
			load.linewake(array);
			System.out.println("File load Complete!");
			caliculation();
		} catch (FileNotFoundException e) {
			
			System.out.println("file load error");
		}
		  
	}
	

}
