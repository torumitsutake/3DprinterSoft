package com.gmail.sitoa.printermain;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.sitoa.mathclass.MathAPI;
import com.gmail.sitoa.mathclass.Point_3;

public class TextloadClass {
	
	public double nozuru = 0;
	public Point_3 highest;
	public Point_3 lowest;
	public String shapename= "";public int shapekazu=0;
	public HashMap<Integer, Point_3> shapepointa = new HashMap<Integer, Point_3>();
	public HashMap<Integer, Point_3> shapepointb = new HashMap<Integer, Point_3>();
	public HashMap<Integer, Point_3> shapepointc = new HashMap<Integer, Point_3>();
	
	public double highestx = 0;
	public double highesty = 0;
	public double highestz = 0;
	public double lowestx = 0;
	public double lowesty = 0;
	public double lowestz = 0;
	public HashMap<Integer, Double> shapevectorx = new HashMap<Integer, Double>();
	public HashMap<Integer, Double> shapevectory = new HashMap<Integer, Double>();
	public HashMap<Integer, Double> shapevectorz = new HashMap<Integer, Double>();
	public int nowpoint;
	public void linewake(ArrayList<String> lines) throws InterruptedException{
	for(int i=0;i<lines.size();i++){
	String line = lines.get(i);
	
	String[] lineinword = null;
	lineinword = line.trim().split("[\\s]+");
	if(lineinword[0].equalsIgnoreCase("solid")){
		shapename = lineinword[0];
	}
	if(lineinword[0].equalsIgnoreCase("facet normal")){
			shapevectorx.put(shapekazu, Double.valueOf(lineinword[1]));
			shapevectory.put(shapekazu, Double.valueOf(lineinword[2]) );
			shapevectorz.put(shapekazu, Double.valueOf(lineinword[3]) );
	}
	if(lineinword[0].equalsIgnoreCase("outer loop")){
				nowpoint = 0;
				
	}
	if(lineinword[0].equalsIgnoreCase("vertex")){
				if(nowpoint==0){
					shapepointa.put(shapekazu,new Point_3(MathAPI.kinnituD(Double.valueOf(lineinword[1]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[2]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[3]),nozuru)));}
				if(nowpoint==1){
					shapepointb.put(shapekazu,new Point_3(MathAPI.kinnituD(Double.valueOf(lineinword[1]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[2]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[3]),nozuru)));
				}
				if(nowpoint==2){
					shapepointc.put(shapekazu,new Point_3(MathAPI.kinnituD(Double.valueOf(lineinword[1]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[2]),nozuru),MathAPI.kinnituD(Double.valueOf(lineinword[3]),nozuru)));
					}
				
				if(highestx < Double.valueOf(lineinword[1])){
					highestx = Double.valueOf(lineinword[1]);
				}
				if(highesty < Double.valueOf(lineinword[2])){
					highesty = Double.valueOf(lineinword[2]);
					
				}
				if(highestz < Double.valueOf(lineinword[3])){
					highestz = Double.valueOf(lineinword[3]);

				}
				if(lowestx > Double.valueOf(lineinword[1])){
					lowestx = Double.valueOf(lineinword[1]);
				}
				if(lowesty > Double.valueOf(lineinword[2])){
					lowesty = Double.valueOf(lineinword[2]);
					
				}
				if(lowestz > Double.valueOf(lineinword[3])){
					lowestz = Double.valueOf(lineinword[3]);

				}
				nowpoint++;
				
			}
	if(lineinword[0].equalsIgnoreCase("endloop")){
				nowpoint=0;
			}
	if(lineinword[0].equalsIgnoreCase("endfacet")){
				shapekazu++;
			}
		}
		
		for(int i=0;i<shapekazu;i++){
			double shapepoint1x = shapepointa.get(i).getx();
			double shapepoint1y = shapepointa.get(i).gety();
			double shapepoint1z = shapepointa.get(i).getz();
			double shapepoint2x = shapepointb.get(i).getx();
			double shapepoint2y = shapepointb.get(i).gety();
			double shapepoint2z = shapepointb.get(i).getz();
			double shapepoint3x = shapepointc.get(i).getx();
			double shapepoint3y = shapepointc.get(i).gety();
			double shapepoint3z = shapepointc.get(i).getz();
			Point_3 bpointa = new Point_3(shapepoint1x-lowestx,shapepoint1y-lowesty,shapepoint1z-lowestz);
			Point_3 bpointb= new Point_3(shapepoint2x-lowestx,shapepoint2y-lowesty,shapepoint2z-lowestz);
			Point_3 bpointc = new Point_3(shapepoint3x-lowestx,shapepoint3y-lowesty,shapepoint3z-lowestz);
			Point_3 pointa = SeiriClass.highestpoint(bpointa, bpointb, bpointc);
			Point_3 pointb = SeiriClass.middlepoint(bpointa, bpointb, bpointc);
			Point_3 pointc = SeiriClass.lowestpoint(bpointa, bpointb, bpointc);
			shapepointa.put(i, pointa);
			shapepointb.put(i, pointb);
			shapepointc.put(i, pointc);	
			highestx=MathAPI.kinnituD(highestx-lowestx,nozuru);
			highesty=MathAPI.kinnituD(highesty-lowesty,nozuru);
			highestz=MathAPI.kinnituD(highestz-lowestz,nozuru);
			lowesty=MathAPI.kinnituD(lowesty-lowesty,nozuru);
			lowestx=MathAPI.kinnituD(lowestx-lowestx,nozuru);
			lowestz=MathAPI.kinnituD(lowestz-lowestz,nozuru);
			
			System.out.println("----------------------");
			System.out.println(shapepoint1x+" "+shapepoint1y+ "   "+shapepoint1z);
			System.out.println(shapepoint2x+" "+shapepoint2y+ "   "+shapepoint2z);
			System.out.println(shapepoint3x+" "+shapepoint3y+ "   "+shapepoint3z);
			System.out.println("----------------------");
		}
	System.out.println("file load finished!");
	System.out.println(shapekazu);
	System.out.println((highestx-lowestx)+"×"+(highesty-lowesty)+"×"+(highestz-lowestz));
	System.out.println("prepering for calculation...");
	System.out.println("highest  x="+highestx+" y="+highesty+"  Z="+highestz);
	System.out.println("lowest  x="+lowestx+" y="+lowesty+"  Z="+lowestz);
	System.out.println("highestsize"+(highestx-lowestx)+"×"+(highesty-lowesty)+"×"+(highestz-lowestz));
	highest = new Point_3(highestx,highesty,highestz);
	lowest = new Point_3(lowestx,lowesty,lowestz);
	Startclass.setpoint(shapepointa, shapepointb, shapepointc);
	Startclass.sethighest(highest);
	Startclass.setlowest(lowest);
	Startclass.setshapekazu(shapekazu);
	Startclass.setname(shapename);
	}
	
	public void setnozuru(double nozuru2) {
		nozuru = nozuru2;
		
	}
}
