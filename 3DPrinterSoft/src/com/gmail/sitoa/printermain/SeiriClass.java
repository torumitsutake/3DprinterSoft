package com.gmail.sitoa.printermain;

import com.gmail.sitoa.mathclass.Point_3;

public class SeiriClass {
	public static Point_3 highestpoint(Point_3 a,Point_3 b,Point_3 c){
		Point_3 result = null;
		if(a.gety() >= b.gety() && a.gety() >= c.gety()){
			result = a;
		}else
		if(b.gety() >= a.gety()  && b.gety() >= c.gety()){
		result =b;	
		}else
		if(c.gety() >= a.gety() && c.gety() >= b.gety()){
			result = c;	
		}else{
			System.out.println("error top");
		}
		
		
		return result;
		
	}public static Point_3 lowestpoint(Point_3 a,Point_3 b,Point_3 c){
		Point_3 result = null;
		if(a.gety() <= b.gety() && a.gety() <= c.gety()){
			result = a;
		}else
		if(b.gety() <= a.gety()  && b.gety() <= c.gety()){
		result =b;	
		}else
		if(c.gety() <= a.gety() && c.gety() <= b.gety()){
			result = c;	
		}else{
			System.out.println("error low");
		}
		
		
		return result;
		
	}
	public static Point_3 middlepoint(Point_3 a,Point_3 b,Point_3 c){
		Point_3 result = null;
		if(a.gety() >= b.gety() && a.gety() <= c.gety()){
			result = a;
		}else
		if(b.gety() >= a.gety()  && b.gety() <= c.gety()){
		result =b;	
		}else
		if(c.gety() >= a.gety() && c.gety() <= b.gety()){
			result = c;	
		}else
			if(a.gety() >= c.gety() && a.gety() <=b.gety()){
				result = a;
			}else
			if(b.gety() >= c.gety()  && b.gety() <= a.gety()){
			result =b;	
			}else
			if(c.gety() >= b.gety() && c.gety() <= a.gety()){
				result = c;	
			}else
			{
			System.out.println("error middle ");
		}
		
		
		return result;
		
	}
	
}
