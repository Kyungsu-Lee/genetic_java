package genetic.mutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomArrayAdaptor<T>
{
	private ArrayList<T> array;
	
	public void set(ArrayList<T> array)
	{
		this.array = array;
	}

	public ArrayList<T> get(int rate)
	{
		if(rate < 0 || rate > 100) return null;

		double mutation_rate = (rate * array.size()) / 100.0;
		ArrayList<T> tmp = new ArrayList<>();
		HashMap<Integer, T> map = new HashMap<>();
		
		while(!(map.size() >= mutation_rate))
		{
			Random rd = new Random();
			int random_num = rd.nextInt(array.size());
			map.put(random_num, array.get(random_num));
		}

		for(int i : map.keySet())
			tmp.add(map.get(i));

		return tmp;
	}
}
