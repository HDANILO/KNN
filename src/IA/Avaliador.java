package IA;

import java.util.LinkedList;

import DATA.DatasetType;

//Classe que recebe um classificador e avalia o mesmo
public class Avaliador<T extends DatasetType> {
	private int[][] confusao;
	LinkedList<String> l;
	T[] datasetValidacao;
	//passa o classifier já treinado
	//e o dataset de validacao
	public Avaliador(Classifier<T> algoritmo, T[] datasetValidacao)
	{
		this.datasetValidacao = datasetValidacao;
		l = new LinkedList<String>();
		for(int i = 0; i < datasetValidacao.length; i++)
		{
			if (l.contains(datasetValidacao[i].getClasse()) == false)
				l.add(datasetValidacao[i].getClasse());
		}
		
		confusao = new int[l.size()][l.size()];
		
		for(int i = 0; i < datasetValidacao.length; i++)
		{
			int predict_index = -1;
			int desired_index = -1;
			String classe = algoritmo.classify(datasetValidacao[i], 2);
			for(int j = 0; j < l.size(); j++)
			{
				if (classe.compareTo(l.get(j)) == 0)
				{
					predict_index = j;
					break;
				}
			}
			
			for(int j = 0; j < l.size(); j++)
			{
				if (datasetValidacao[i].getClasse().compareTo(l.get(j)) == 0)
				{
					desired_index = j;
					break;
				}
			}
			confusao[desired_index][predict_index]++;			
		}
	}
	
	public double Accuracy()
	{
		int c = 0;
		for(int i = 0; i < l.size(); i++)
		{
			c += confusao[i][i];
		}
		return ((double)c)/datasetValidacao.length;
	}
	
	public double Recall(String classe)
	{
		int classe_index = -1;
		for(int i = 0; i < l.size(); i++)
		{
			if (classe.compareTo(l.get(i)) == 0)
			{
				classe_index = i;
				break;
			}
		}
		
		int vp = confusao[classe_index][classe_index];
		int fn = 0;
		for(int i = 0; i < l.size(); i++)
		{
			if (i != classe_index)
			{
				fn += confusao[classe_index][i];
			}
		}
		
		return ((double) vp)/(vp+fn);
	}
	
	public double Precision(String classe)
	{
		int classe_index = -1;
		for(int i = 0; i < l.size(); i++)
		{
			if (classe.compareTo(l.get(i)) == 0)
			{
				classe_index = i;
				break;
			}
		}
		
		int vp = confusao[classe_index][classe_index];
		int fp = 0;
		for(int i = 0; i < l.size(); i++)
		{
			if (i != classe_index)
			{
				fp += confusao[i][classe_index];
			}
		}
		
		return ((double) vp)/(vp+fp);
	}
	
	public double f_measure(String classe, int w)
	{
		return ((w+1)*Recall(classe)*Precision(classe))/(Recall(classe)+(w*Precision(classe)));
	}
	
	public void gerarRelatorio()
	{
		System.out.println("Acurácia do sistema: " + Accuracy());
		System.out.println();
		for(int i = 0; i < l.size(); i++)
		{
			System.out.println("Recall da Classe " + l.get(i) + ": " + Recall(l.get(i)));
			System.out.println("Precision da Classe " + l.get(i) + ": " + Precision(l.get(i)));
			System.out.println("Medida F da Classe " + l.get(i) + " com w = 1: " + f_measure(l.get(i),1));
			System.out.println();
		}
		
		System.out.println("Matriz de confusão");
		for(int i = 0; i < l.size(); i++)
		{
			for(int j = 0; j < l.size(); j++)
			{
				System.out.print(confusao[i][j] + " ");
			}
			System.out.println();
		}
	}

}
