package genetic.data;

import java.util.HashMap;
import java.util.ArrayList;
import genetic.csv.DistanceTable;

public class ClassManager
{
	HashMap<ClassKey, ClassInfo> map;

	private static ClassManager instance;

	private ClassManager()
	{
		map = new HashMap<ClassKey, ClassInfo>();
	}

	public static ClassManager getInstance()
	{
		if(instance == null)
			instance = new ClassManager();
		
		return instance;
	}

	public void insertClass(ClassInfo classInfo)
	{
		map.put(classInfo.getKey(), classInfo);
	}

	public ClassInfo get(ClassKey key)
	{
		return map.get(key);
	}

	public ArrayList<ClassInfo> getAllClasses()
	{
		ArrayList<ClassInfo> tmp = new ArrayList<ClassInfo>();

		for(ClassKey key : map.keySet())
			tmp.add(map.get(key));
		
		return tmp;
	}

	public void addStudent(ClassKey key, Student student)
	{
		if(!map.containsKey(key))
			return;
		get(key).addStudent(student);
	}

	public ArrayList<ClassKey> getClassKeys()
	{
		ArrayList<ClassKey> tmp = new ArrayList<>();
		for(ClassKey key : map.keySet())
			tmp.add(key);
		
		return tmp;
	}

	public void setNextClass()
	{
		for(ClassKey key : map.keySet())
		{
			for(ClassKey p : map.keySet())
			{
				map.get(key).setNextClass(map.get(p));
			}
		}			
	}

	public String getNextClass()
	{
		String tmp = "";
		for(ClassKey key : map.keySet())
		{
			tmp += map.get(key).getClassCode() + " => ";
			for(ClassInfo c : map.get(key).getNextClass())
				tmp += c.getClassCode() + ", ";
			tmp += "\n";
		}
		return tmp;
	}

	public String getNextClassDistance(DistanceTable table)
	{
		String tmp = "";
		for(ClassKey key : map.keySet())
		{
			tmp += map.get(key).getClassCode() + " => ";
			tmp += map.get(key).getNextDistance(table);
			tmp += "\n";
		}
		return tmp;
	}

	public int getNextTotalDistance(DistanceTable table)
	{
		int tmp = 0;
		for(ClassKey key : map.keySet())
		{
			tmp += map.get(key).getNextDistance(table);
		}
		return tmp;
	}
	

	@Override
	public String toString()
	{
		StringBuilder tmp = new StringBuilder("");
		for(ClassKey key : map.keySet())
			tmp.append(map.get(key).toString() + "\n");
		return tmp.toString();
	}
}
