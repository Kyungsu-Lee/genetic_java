package genetic;

import java.util.HashSet;
import java.util.Iterator;
import genetic.csv.*;
import genetic.data.*;
import genetic.mutation.*;

public class Main
{
	public static void main(String[] args)
	{
		ClassSystem system = new ClassSystem(args[0]);
		system.selectSemester(2016, 1);

		RandomArrayAdaptor<ClassInfo> adaptor = new RandomArrayAdaptor<>();
		adaptor.set(ClassManager.getInstance().getAllClasses());
		
		for(ClassInfo c : adaptor.get(10))
		{
			System.out.println(c.toString_classroom());
		}
/*
		(ClassManager.getInstance().getAllClasses()).get(10).makeEmpty();
		for(ClassInfo c : ClassManager.getInstance().getAllClasses())
		{
			System.out.println(c.toString_classroom());
		}
*/
		
//		ClassManager.getInstance().setNextClass();
//		System.out.println(ClassManager.getInstance().getNextClassDistance(distanceTable));
//		System.out.println(ClassManager.getInstance().getNextClass());
//		System.out.println(ClassManager.getInstance().getNextTotalDistance(distanceTable));

//		System.out.println(ClassManager.getInstance());
//		System.out.println(StudentManager.getInstance());

	}
}
