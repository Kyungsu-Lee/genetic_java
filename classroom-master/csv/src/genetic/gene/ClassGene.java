package genetic.gene;

import genetic.data.*;
import genetic.csv.*;
import java.util.ArrayList;

public class ClassGene
{
	private ClassKey classKey;

	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<ClassGene> previous_class = new ArrayList<>();
	private ArrayList<ClassGene> next_class = new ArrayList<>();

	private String tmp_classRoom;
	private ClassRoomGene classRoom;
	private int[] class_time;

	private String max_num;
	private String taking_num;

	public ClassGene(ClassKey classKey)
	{
		this.classKey = classKey;
	}

	public ClassGene(ClassGene other)
	{
		this.classKey = other.getClassKey();
		this.setStudentList(other.getStudent());
		this.setNum(other.getMaxNum(), other.getNum());
		this.setClassTime(other.getTime());
		this.set_tmp_class_room(other.getClassRoom().getName());
	}

	public void set_tmp_class_room(String classroom)
	{
		this.tmp_classRoom = classroom;	
	}
	
	public String get_tmp_class_room()
	{
		return this.tmp_classRoom;
	}

	public void setNum(String max, String taking)
	{
		this.max_num = max;
		this.taking_num = taking;
	}

	public String getMaxNum() { return this.max_num; }
	public String getNum() { return this.taking_num; }

	public void setClassRoom(ClassRoomGene classGene)
	{
		this.classRoom = classGene;	
	}

	public ClassKey getClassKey()
	{
		return this.classKey;
	}

	public ClassRoomGene getClassRoom()
	{
		return this.classRoom;
	}
	
	public void addStudent(Student student)
	{
		this.students.add(new Student(student));
	}	

	public void setStudentList(ArrayList<Student> list)
	{
		this.students = list;
	}

	public ArrayList<Student> getStudent()
	{
		return this.students;
	}	

	public void setClassTime(int[] time)
	{
		this.class_time = new int[time.length];
		for(int i=0; i<class_time.length; i++)
			class_time[i] = time[i];
	}

	public int[] getTime()
	{
		return this.class_time;
	}

	public int getNextDistance(DistanceTable table)
	{
		int distance = 0;

		for(ClassGene _class : next_class)
		{
			int count = 0;
			for(Student student: _class.getStudent())
				for(Student s : getStudent())
				{
					if(s.getId().equals(student.getId()))
					{
						count++;
					}
				}
			int _distance = (count * table.getDistance(this.classRoom.getName(), _class.getClassRoom().getName()));
			distance += _distance;
			//if(_distance > 0)
			//System.out.println(classKey + "=>" + _class.getClassKey() + ":" + _distance);
		}
		return distance;
	}

	public int getPreviousDistance(DistanceTable table)
	{
		int distance = 0;

		for(ClassGene _class : previous_class)
		{
			int count = 0;
			for(Student student: _class.getStudent())
				for(Student s : getStudent())
				{
					if(s.getId().equals(student.getId()))
					{
						count++;
					}
				}
			distance += (count * table.getDistance(this.classRoom.getName(), _class.getClassRoom().getName()));
		}
		return distance;
	}

	public int getMovement()
	{
		return Math.abs(getPreviousDistance(DistanceTable.getInstance()) - getNextDistance(DistanceTable.getInstance()));
	}

	public void setClassRelation(ClassGene _class)
	{
		boolean flag = false;

		for(int time : class_time)
		{
			for(int otherTime : _class.getTime())
				flag |= (time + 1 == otherTime);
		}

		if(flag)
		{
			this.next_class.add(_class);
			_class.setPreviousClass(this);
		}
	}

	public void setPreviousClass(ClassGene _class) { this.previous_class.add(_class); }

	public void clear()
	{
		next_class.clear();
	}

	public String toString()
	{
		return this.classKey.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof ClassGene)) return false;

		ClassGene other = (ClassGene)o;

		return this.classKey.equals(other.getClassKey());
	}

	@Override
	public int hashCode()
	{
		return this.classKey.hashCode();
	}
}
