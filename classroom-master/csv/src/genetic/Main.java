package genetic;

import java.util.HashSet;
import java.util.Iterator;
import genetic.csv.*;
import genetic.data.*;

public class Main
{
	public static void main(String[] args)
	{
		Table student = new CSVReader(args[0], "enrollment.csv").makeTable("ENROLLMENT");
		Table _class  = new CSVReader(args[0], "class.csv").makeTable("CLASS");
		DistanceTable distanceTable = (DistanceTable)(new CSVReader(args[0], "distance.csv").makeTable("DISTANCE"));

		Table sub_stu = student.selectRows("year", "2013").selectRows("semester", "1");
		Table sub_class = _class.selectRows("year", "2013").selectRows("semester", "1");


		for(CSVElement e : sub_class.getRows())
		{
			ClassManager.getInstance().insertClass(new ClassInfo(
				e.getElement("year"),
				e.getElement("semester"),
				e.getElement("class_code"),
				e.getElement("class_section"),
				e.getElement("credit"),
				e.getElement("max_num"),
				e.getElement("class_room"),
				e.getElement("taking_num"),
				e.getElement("class_time")
			));
		}

		for(CSVElement e : sub_stu.getRows())
		{
			StudentManager.getInstance().insertStudent(new Student(
				e.getElement("ID"),
				e.getElement("year"),
				e.getElement("semester"),
				e.getElement("class_code"),
				e.getElement("class_section")
			));
		}
		
		for(Student s : StudentManager.getInstance().getStudentList())
		{
			ClassManager.getInstance().addStudent(s.getClassKey(), s);
		}

		ClassManager.getInstance().setNextClass();
//		System.out.println(ClassManager.getInstance().getNextClassDistance(distanceTable));
//		System.out.println(ClassManager.getInstance().getNextClass());
		System.out.println(ClassManager.getInstance().getNextTotalDistance(distanceTable));

//		System.out.println(ClassManager.getInstance());
//		System.out.println(StudentManager.getInstance());

	}
}
