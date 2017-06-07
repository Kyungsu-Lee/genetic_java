package genetic.data;

import genetic.csv.*;
import java.util.*;

public class ClassInfo
{
	ArrayList<Student> taking_students = new ArrayList<Student>();
	ArrayList<ClassInfo> next_class = new ArrayList<>();
	
	Table table;

	private int[] class_time;
	private String year;
	private String semester;
	private String class_code;
	private String class_section;
	private String credit;
	private String max_num;
	private String class_room;
	private String taking_num;

	public ClassInfo(String year, String semester, String class_code, String class_section, String credit, String max_num, String class_room, String taking_num, String class_time)
	{
		this.year = year;
		this.semester = semester;
		this.class_code = class_code;
		this.class_section = class_section;
		this.credit = credit;
		this.class_room = class_room;
		this.taking_num = taking_num;
		this.class_time = parseTime(class_time);
	}
	
	public void setTable(Table table)
	{
		this.table = table;
	}

	public String getYear() { return this.year; }
	public String getSemester() { return this.semester; }
	public String getClassCode() { return this.class_code; }
	public String getClassSection() { return this.class_section; }
	public String getCredit() { return this.credit; }
	public String getMaxNum() { return this.max_num; }
	public String getClassRoom() { return this.class_room; }
	public String getTakingNum() { return this.taking_num; }

	public int[] parseTime(String time)
	{	
		String[] tmp = time.split("::");
		int[] value = new int[tmp.length];
		for(int i=0; i<tmp.length; i++)
		{
			try
			{
				value[i] = Integer.parseInt(tmp[i]);
			}catch(Exception e)
			{
				value[i] = -1;
			}
		}
		return value;
	}

	public int[] getTime()
	{
		return this.class_time;
	}

	public ClassKey getKey()
	{
		return new ClassKey(year, semester, class_code, class_section);
	}

	public String toString()
	{
		String tmp = "";
		tmp += "{"
		+ "year : " + year + ", "
		+ "semester : " + semester + "," 
		+ "class_code : " + class_code + ", "
		+ "class_section : " + class_section + ", "
		+ "credit : " + credit + ", "
		+ "max_num : " + max_num + ", "
		+ "class_room : " + class_room + ", "
		+ "taking_num : " + taking_num + ", "
		+ "taking_time : ";

		for(int time : class_time)
			tmp += time + ", ";

		tmp += "taking_student : "
		;
		
		for(Student student : taking_students)
			tmp += student.getId() + ", ";
		tmp += "}";
		return tmp;
	}

	public void setNextClass(ClassInfo classInfo)
	{
		boolean flag = false;
		for(int time : class_time)
		{
			for(int otherTime : classInfo.getTime())
				flag |= (time + 1 == otherTime);
		}

		if(flag)
			this.next_class.add(classInfo);
	}

	public ArrayList<ClassInfo> getNextClass()
	{
		return this.next_class;
	}

	public int getNextDistance(DistanceTable table)
	{
		int distance = 0;

		for(ClassInfo _class : next_class)
		{
			int count = 0;
			for(Student student : _class.getStudent())
				for(Student s : getStudent())
					if(s.getId().equals(student.getId()))
						count++;
			if(count > 0)
				System.out.println(this+ "=>" + _class);
			distance += (count * table.getDistance(this.class_room, _class.getClassRoom()));
		}
		return distance;
	}

	public void addStudent(Student student)
	{
		this.taking_students.add(student);
	}
	
	public ArrayList<Student> getStudent()
	{	
		return this.taking_students;
	}
}
