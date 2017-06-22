package genetic.mutation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import genetic.data.*;
import genetic.csv.*;

public class GreedyAdaptor
{
	private ArrayList<ClassInfo> array;

	public void set(ArrayList<ClassInfo> array)
	{
		this.array = array;

		Collections.sort(this.array, new MovementComp());
	}

	public ArrayList<ClassInfo> get(int rate)
	{
		if(rate < 0 || rate > 100) return null;
		double mutation_rate = (rate * array.size()-1) / 100.0;
		Random rd = new Random();

		for(int i=0; i<= mutation_rate; i++)
		{
			int rd_num = rd.nextInt(ClassRoomManager.getInstance().getAllClassRooms().size());
			if(
				(ClassRoomManager.getInstance().getAllClassRooms().get(rd_num).hasTime(array.get(i).getTime())) 
				&& !condition(array.get(i), ClassRoomManager.getInstance().getAllClassRooms().get(rd_num))
			  ) continue;

			ClassRoom _old = array.get(i).getClassRoom();
			ClassRoom _new = ClassRoomManager.getInstance().getAllClassRooms().get(rd_num);

			int _prev = array.get(i).getMovement();
			_new.enroll(array.get(i).getKey());
			int _next = array.get(i).getMovement();

			if(_prev < _next)
			{
				_old.enroll(array.get(i).getKey());
				continue;
			}		

		}

		return array;
	}

	private boolean condition(ClassInfo classInfo, ClassRoom classRoom)
	{
		boolean flag = true;

		try{
			flag &= Integer.parseInt(classInfo.getMaxNum()) <= classRoom.getNum();
			flag &= Integer.parseInt(classInfo.getTakingNum()) >= classRoom.getNum()/2;

		}catch(Exception e){
			return false;
		}
		return flag;
	}


	private class MovementComp implements  Comparator<ClassInfo>
	{
		public int compare(ClassInfo arg1, ClassInfo arg0) //increase
		{
			Integer one = new Integer(arg0.getMovement());
			return one.compareTo(arg1.getMovement());
		}
	}
}
