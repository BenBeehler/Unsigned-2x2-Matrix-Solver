package com.benbeehler.ms;

import java.util.Random;

public class MSolver {

	public static void main(String[] args) throws IOException{
       Scanner sc = new Scanner(System.in);
       System.out.println("please enter the coordinates of each line") /*Update these instructions please*/
		long start = System.nanoTime();
		double tL = sc.nextDouble();
		double bL = sc.nextDouble();
			
		double tR = sc.nextDouble();
		double bR = sc.nextDouble();
			
		double xC = sc.nextDouble();
		double yC = sc.nextDouble();
			
		double[] values = new double[] { tL, bL, tR, bR, xC, yC };
		init(values);
		
		Long end = System.nanoTime();
		Long time = (end - start);
		
		System.out.println();
		System.out.println("Took " + (time / 1000000.0) + " milli seconds to solve matrix");
	}
	
	public static void init(double[] values) {
		double tL = values[0];
		double bL =	values[1];
		
		double tR = values[2];
		double bR = values[3];
		
		double xC = values[4];
		double yC = values[5];
		
		System.out.println("{" + tL + "    " + tR + "   |    " + xC + "}");
		System.out.println("{" + bL + "    " + bR + "   |    " + yC + "}");
		
		System.out.println();
		
		do {
			System.out.println("solving...");
			System.out.println();
			
			values = reduce(values);
			
			tL = values[0];
			bL = values[1];
			tR = values[2];
			bR = values[3];
			xC = values[4];
			yC = values[5];
		} while(!(bL == 0 && tR == 0));
		
		System.out.println("Point: ");
		System.out.println("(" + xC/tL + ", " + yC/bR + ")");
	}
	
	public static double[] reduce(double[] values) {
		/*
		 * The Matrix
		 * { tL    tR  |  xC }
		 * { bL    bR  |  yC }
		 * 
		 * tL should = 1
		 * bL should = 0
		 * 
		 * tR should = 0
		 * bR should = 1
		 */
		
		double tL = values[0];
		double bL = values[1];
		double tR = values[2];
		double bR = values[3];
		double xC = values[4];
		double yC = values[5];
		
		System.out.println("{" + tL + "    " + tR + "   |    " + xC + "}");
		System.out.println("{" + bL + "    " + bR + "   |    " + yC + "}");
		System.out.println();
		
		double newbL = (tL*bL) - (bL*tL);
		double newbR = (tR*bL) - (bR*tL);
		double newyC = (xC*bL) - (yC*tL);
		
		System.out.println("{" + tL + "    " + tR + "   |    " + xC + "}");
		System.out.println("{" + newbL + "    " + newbR + "   |    " + newyC + "}");
		System.out.println();
		
		double newtR = (tR * newbR) - (newbR * tR);
		double newtL = (tL * newbR) - (newbL * tR);
		double newxC = (xC * newbR) - (newyC * tR);
		
		System.out.println("{" + newtL + "    " + newtR + "   |    " + newxC + "}");
		System.out.println("{" + newbL + "    " + newbR + "   |    " + newyC + "}");
		System.out.println();
		
		values = new double[] { newtL, newbL, newtR, newbR, newxC, newyC };
		
		return values;
	}
}
