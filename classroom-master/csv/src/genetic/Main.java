package genetic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;

import genetic.csv.*;
import genetic.data.*;
import genetic.mutation.*;

public class Main
{
	static DistanceTable distanceTable;

	public static void main(String[] args)
	{

		ClassSystem system = new ClassSystem(args[0]);
		system.selectSemester(2013, 1);

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
/*
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
