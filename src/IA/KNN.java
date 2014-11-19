package IA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import DATA.DatasetType;

class DistancePoint implements Comparable<DistancePoint>
{
	public double distance;
	public int indexInDataset;
	@Override
	public int compareTo(DistancePoint arg) {
		if (distance > arg.distance)
			return 1;
		else if (distance == arg.distance)
			return 0;
		else
			return -1;
	}
	
}

public class KNN<T extends DatasetType> extends Classifier<T> {
	
	int k;
	T[] dataset;
	
	public KNN(int k, T[] dataset)
	{
		this.k = k;
		this.dataset = dataset;
	}
	
	public String classify(T elem, int q)
	{
		//pega todas possíveis classes
		LinkedList<String> l = new LinkedList<String>();
		for(int i = 0; i < dataset.length; i++)
		{
			if (l.contains(dataset[i].getClasse()) == false)
				l.add(dataset[i].getClasse());
		}
		
		//computa a distancia de minkowski para cada elemento
		DistancePoint[] distances = new DistancePoint[dataset.length];
		for(int i = 0; i < dataset.length; i++)
		{
			distances[i] = new DistancePoint();
			distances[i].indexInDataset = i;
			distances[i].distance = minkowski_distance(dataset[i],elem,q);
		}
		
		//ordena baseado no critério definido em DistancePoint
		Arrays.sort(distances);
		
		//acha a moda
		int[] hist = new int[l.size()];
		for(int i = 0; i < k; i++)
		{
			for(int j = 0; j < l.size(); j++)
			{
				if (dataset[distances[i].indexInDataset].getClasse().compareTo(l.get(j)) == 0)
				{
					hist[j]++;
					break;
				}
			}
		}
		
		int moda = Integer.MIN_VALUE;
		int maior = -1;
		for(int i = 0; i < l.size(); i++)
		{
			if (moda < hist[i])
			{
				moda = hist[i];
				maior = i;
			}
		}
		
		if (maior == -1)
			System.out.println("Erro no computo da moda");

		return l.get(maior);
	}
	
	public double minkowski_distance(T elem1, T elem2, int q)
	{
		double dist = 0;
		for(int i = 0; i < elem1.getNumAttr(); i++)
		{
			dist += Math.pow(elem1.getAttr(i) - elem2.getAttr(i),q); 
		}
		dist = Math.pow(dist, 1f/q);
		
		return dist;
	}

}
