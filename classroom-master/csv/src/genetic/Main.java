package genetic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

import genetic.csv.*;
import genetic.data.*;
import genetic.mutation.*;
import genetic.gene.*;

public class Main
{
	static DistanceTable distanceTable;

	private static class cmp implements Comparator<Gene>
	{
		public int compare(Gene g1, Gene g2)
		{
			Integer i1 = new Integer(g1.getTotalNextDistance());
			return i1.compareTo(new Integer(g2.getTotalNextDistance()));
		}
	}

	public static void main(String[] args)
	{

		ClassSystem system = new ClassSystem(args[0]);
		system.selectSemester(2013, 1);

		Gene gene = ClassManager.getInstance().makeGene();

		Gene[] parent = new Gene[200];

		parent[0] = gene;

		for(int i=1; i<parent.length/10; i++)
		{
			RandomArrayAdaptor rA = new RandomArrayAdaptor();
			rA.setGene(parent[0]);
			parent[i] = rA.mutate(100);
			System.out.print("a");
			if(i % 10 == 0)
				System.out.println("");		
		}

		for(int i=parent.length/10; i<parent.length/2; i++)
		{
			RandomArrayAdaptor rA = new RandomArrayAdaptor();
			rA.setGene(parent[0]);

			if(i%4 == 0)
				parent[i] = rA.mutate(10);
			if(i%4 == 1)
				parent[i] = rA.mutate(20);
			if(i%4 == 2)
				parent[i] = rA.mutate(30);
			if(i%4 == 3)
				parent[i] = rA.mutate(40);
		
			System.out.print("r");
			if(i % 10 == 0)
				System.out.println("");		
		}

		for(int i=parent.length/2; i<parent.length; i++)
		{
			GreedyAdaptor gA = new GreedyAdaptor();
			gA.setGene(gene);
			parent[i] = gA.mutate(10);
			System.out.print("g");
			if(i % 10 == 0)
				System.out.println("");		
		}

		int t = 0;
		for(Gene _gene : parent)
		{
			System.out.println((++t) + ":" + _gene.getTotalNextDistance());
		}
		System.out.println("===");


		HashSet<Integer> rd_set = new HashSet<>();
		HashSet<Integer> rd_greedy_set = new HashSet<>();
		Random rd = new Random();
/*
		Gene[] first_generation = new Gene[100];
		int index = 0;
		while(rd_set.size() < 100)
		{
			int rd_num = rd.nextInt(parent.length/2);
			if(rd_set.contains(rd_num)) continue;
			rd_set.add(rd_num);
			
			int rd_greedy_num = rd.nextInt(parent.length/2) + parent.length/2;
			while(rd_greedy_set.contains(rd_greedy_num)) 
				rd_greedy_num = rd.nextInt(parent.length/2) + parent.length/2;
			rd_greedy_set.add(rd_greedy_num);

			MutationAdaptor mA = new MutationAdaptor();
			if(index%4 == 0)
			{
				first_generation[index++] = mA.mutate(parent[rd_num], parent[rd_greedy_num], 10);
				continue;
			}
			if(index%4 == 1)
			{
				first_generation[index++] = mA.mutate(parent[rd_num], parent[rd_greedy_num], 30);
				continue;
			}
			if(index%4 == 2)
			{
				first_generation[index++] = mA.mutate(parent[rd_num], parent[rd_greedy_num], 50);
				continue;
			}
			if(index%4 == 3)
			{
				first_generation[index++] = mA.mutate(parent[rd_num], parent[rd_greedy_num], 80);
				continue;
			}
			System.out.print("c");
			if(index % 10 == 0)
				System.out.println("");		
			if(index > 100) break;
		}

		Arrays.sort(first_generation, new cmp());
*/		t = 0;
		for(Gene _gene : parent)
		{
			System.out.println((++t) + ":" + _gene.getTotalNextDistance());
		}
		System.out.println("===");

		Gene[] generation = new Gene[200];
		for(int i=0; i<200; i++)
			generation[i] = parent[i];
		for(int count = 0; count < 10; count++)	//new generation
		{
			System.out.println("generation : " + (count + 1));
			rd_set.clear();
			
			Arrays.sort(generation , new cmp());

			Gene[] tmp = new Gene[200];
			for(int i=0; i<100; i++)
			{
				tmp[i] = generation[i];
				rd_set.add(i);
			}

			for(int i=100; i<20; i++)
			{
				RandomArrayAdaptor rA = new RandomArrayAdaptor();
				rA.setGene(gene);
				tmp[i] = rA.mutate(100);

				System.out.print("r");
				if(i % 10 == 0)
					System.out.println("");		
			}
			int tmp_i = 130;
			while(rd_set.size() < 30)
			{
				int rd_num = rd.nextInt(100);
				if(rd_set.contains(rd_num)) continue;
				rd_set.add(rd_num);	
				
				tmp[tmp_i++] = generation[rd_num];

				System.out.print(".");
				if(tmp_i % 10 == 0)
					System.out.println("");		
			}

			for(int i=150; i<200; i++)
			{	
				RandomArrayAdaptor rA = new RandomArrayAdaptor();
				rA.setGene(gene);
				GreedyAdaptor gA = new GreedyAdaptor();
				gA.setGene(rA.mutate(100));
				tmp[i] = gA.mutate(20);

				System.out.print("g");
				if(i % 10 == 0)
					System.out.println("");		
			} 

			for(int i=0; i<100; i++)
				generation[i] = tmp[i];


			Arrays.sort(generation, new cmp());
			t = 0;
			for(Gene _gene : generation)
			{
				System.out.println((++t) + ":" + _gene.getTotalNextDistance());
			}
			System.out.println("===");
		}

		/*
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
