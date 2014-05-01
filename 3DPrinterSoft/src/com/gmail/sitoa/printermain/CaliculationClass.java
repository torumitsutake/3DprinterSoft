package com.gmail.sitoa.printermain;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.sitoa.mathclass.Flat_3;
import com.gmail.sitoa.mathclass.Line_3;
import com.gmail.sitoa.mathclass.Point_3;

public class CaliculationClass {
	
	private HashMap<Integer,Point_3> pointa  = new HashMap<Integer,Point_3>();
	private HashMap<Integer,Point_3> pointb  = new HashMap<Integer,Point_3>();
	private HashMap<Integer,Point_3> pointc  = new HashMap<Integer,Point_3>();
	private HashMap<Double,Flat_3> flat = new HashMap<Double,Flat_3>();
	private double nozuru;
	private Point_3 low;
	private Point_3 high;
	public void setPoints(HashMap<Integer, Point_3> shapepointa,
			HashMap<Integer, Point_3> shapepointb,
			HashMap<Integer, Point_3> shapepointc) 
	{	
			pointa = shapepointa;
			pointb = shapepointb;
			pointc = shapepointc;
	}
	public void setNozuru(double nozurua){
	nozuru = nozurua;
	}
	public void setlow(Point_3 l){
		low = l;
	}
	public void sethigh(Point_3 h){
		high = h;
	}
	public void CaliculationStart(){
		try {
		System.setOut(new PrintStream("Log.txt"));
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		double highs = high.gety()-low.gety();
		for(double nowhigh=0;nowhigh <= highs;){
			System.out.println("caliculating nowhigh="+nowhigh);
			for(int i = 0; i<pointa.size();i++){
				// i =  shapekazu
				ArrayList<Line_3> lineinflat = new ArrayList<Line_3>();
			if(pointa.get(i).gety() >= nowhigh && pointc.get(i).gety() <= nowhigh){
				//現在のたかさの位置に、三角形があるかどうかを判別
				//ac
				Point_3 fp = null;
				Point_3 ep = null;
				Line_3 ac = new Line_3(pointa.get(i),pointc.get(i));
				fp = ac.getPointbyHigh(nowhigh);
				
				//ab
				if(pointb.get(i).gety() >= nowhigh){
					Line_3 ab = new Line_3(pointa.get(i),pointb.get(i));
					ep = ab.getPointbyHigh(nowhigh);
				}else{

					Line_3 bc = new Line_3(pointb.get(i),pointc.get(i));
					//bc
					ep = bc.getPointbyHigh(nowhigh);
				}
				Line_3 line =new Line_3(fp,ep);
				lineinflat.add(line);
				System.out.println(fp.getx()+","+fp.gety()+","+fp.getz());
				System.out.println(" "+ep.getx()+","+ep.gety()+","+ep.getz());
				
			}
			
			flat.put(nowhigh,new Flat_3(lineinflat));
			}
			System.out.println("high= "+nowhigh+" is ok!");
			nowhigh = nowhigh+nozuru;
	
		}
		FileOutputStream fdOut = new FileOutputStream(FileDescriptor.out);
		System.setOut(new PrintStream(new BufferedOutputStream(fdOut, 128), true));
		System.out.println("Caliculatin finished!");
	}
	
	
	
}
