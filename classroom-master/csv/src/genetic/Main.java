package genetic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

import genetic.csv.*;
import genetic.data.*;
import genetic.mutation.*;
import genetic.gene.*;

public class Main
{
	static DistanceTable distanceTable;

	private static class cmp implements Comparator<Gene>
	{
		public int compare(Gene g1, Gene g2)
		{
			Integer i1 = new Integer(g1.getTotalNextDistance());
			return i1.compareTo(new Integer(g2.getTotalNextDistance()));
		}
	}

	public static void main(String[] args)
	{
		ClassSystem system = new ClassSystem(args[0]);
		system.selectSemester(2013, 1);

		for(int k=0; k<20; k++)
		{
		Gene gene = ClassManager.getInstance().makeGene();

		for(int i=1; i < 100; i++)
		{
			GreedyAdaptor rA = new GreedyAdaptor();
			rA.setGene(gene);
			gene= rA.mutate(100);
			System.out.println(i + "," + gene.getTotalNextDistance()+ ",");
		}
		System.out.println("====");
		}

		/*
		   distanceTable = (DistanceTable)(new CSVReader(args[0], "distance.csv").makeTable("DISTANCE"));
		   ClassManager.getInstance().setNextClass();

		   ClassManager cM = ClassManager.getInstance();
		   for(ClassInfo _class : cM.getAllClasses())
		   _class.setTable(distanceTable);


		System.out.println(cM.getNextTotalDistance());

		GreedyAdaptor gA = new GreedyAdaptor();
		gA.set(cM.getAllClasses());
		gA.get(100);

		ClassManager.getInstance().setNextClass();

		int next = (ClassManager.getInstance().getNextTotalDistance());
		System.out.println(next);

		gA = new GreedyAdaptor();
		gA.set(cM.getAllClasses());
		gA.get(50);

		ClassManager.getInstance().setNextClass();

		next = (ClassManager.getInstance().getNextTotalDistance());
		System.out.println(next);

		ClassSystem system = new ClassSystem(args[0]);
		system.selectSemester(2013, 1);

		RandomArrayAdaptor adaptor = new RandomArrayAdaptor();
		adaptor.set(ClassManager.getInstance().getAllClasses());

		ArrayList<ClassInfo> newArray = adaptor.get(50);	
		for(ClassInfo c : newArray)
		{
			c.makeEmpty();
		}

		MutateClassAdaptor ad = new MutateClassAdaptor();
		ad.set(newArray);
		ad.get();
		System.out.println(ClassManager.getInstance().ClassPair());

		   ClassManager.getInstance().setNextClass();

		   int next = (ClassManager.getInstance().getNextTotalDistance());
		   System.out.println(next);
*/

		/*
		   int min = 100000;
		   for(int i=0; i<10; i++)
		   {
		   ClassManager.getInstance().clear();
		   ClassRoomManager.getInstance().clear();
		   StudentManager.getInstance().clear();

		   ClassSystem system = new ClassSystem(args[0]);
		   system.selectSemester(2013, 1);

		   ClassManager.getInstance().setNextClass();
		   int pre = (ClassManager.getInstance().getNextTotalDistance());
		   System.out.println("\n\n" + i + "th iteration");
		   System.out.println(pre);

		   RandomArrayAdaptor adaptor = new RandomArrayAdaptor();
		   adaptor.set(ClassManager.getInstance().getAllClasses());

		   ArrayList<ClassInfo> newArray = adaptor.get(100);	
		   for(ClassInfo c : newArray)
		   {
		   c.makeEmpty();
		   }
		   System.out.println("selected");

		   MutateClassAdaptor ad = new MutateClassAdaptor();
		   ad.set(newArray);
		   ad.get();

		   System.out.println("sorted");



		   ClassManager.getInstance().setNextClass();

		   int next = (ClassManager.getInstance().getNextTotalDistance());
		   System.out.println(next);
		   if(min > next) min = next;

		   System.out.println(ClassManager.getInstance().ClassPair());
		   }

		   System.out.println("\n\nmin : " + min);
		 */	
	}
}
