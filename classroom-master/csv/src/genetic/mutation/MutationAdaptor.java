package genetic.mutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import genetic.data.*;
import genetic.csv.*;

public class MutationAdaptor
{
	private ArrayList<ClassInfo> array1;
	private ArrayList<ClassInfo> array2;

	public void set(ArrayList<ClassInfo> array1, ArrayList<ClassInfo> array2)
	{
		this.array1 = array1;
		this.array2 = array2;
	}

	//rate for array1
	public ArrayList<ClassInfo> get(int rate)
	{
		if(rate < 0 || rate > 100) return null;
		double mutation_rate = (rate * array1.size()-1) / 100.0;
		Random rd = new Random();

		HashSet<ClassInfo> tmp = new HashSet<>();

		while(tmp.size() <= mutation_rate)
		{
			int rd_num = rd.nextInt();
			tmp.add(array1.get(rd_num));
		}
	
		assert array1.size() == array2.size();

		for(int i=0; i<array2.size()-1 ; i++)
		{
			if(!tmp.contains(array2.get(i))) tmp.add(array2.get(i));
		}


		ArrayList<ClassInfo> array = new ArrayList<>();
		for(ClassInfo classInfo : tmp)
			array.add(classInfo);

		return array;
	}

}
