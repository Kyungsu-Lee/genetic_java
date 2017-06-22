package genetic.mutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import genetic.data.ClassInfo;

public class RandomArrayAdaptor
{
	private ArrayList<ClassInfo> array;
	
	public void set(ArrayList<ClassInfo> array)
	{
		this.array = array;
	}

	public ArrayList<ClassInfo> get(int rate)
	{
		if(rate < 0 || rate > 100) return null;

		double mutation_rate = (rate * array.size()) / 100.0;
		ArrayList<ClassInfo> tmp = new ArrayList<>();
		HashMap<Integer, ClassInfo> map = new HashMap<>();
		
		while(!(map.size() >= mutation_rate))
		{
			Random rd = new Random();
			int random_num = rd.nextInt(array.size());
			if(array.get(random_num).getClassRoom() == null) continue;
			map.put(random_num, array.get(random_num));
//			System.out.println("added : " + array.get(random_num));
		}

		for(int i : map.keySet())
			tmp.add(map.get(i));

		return tmp;
	}

}
