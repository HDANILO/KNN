package Teste;

import static org.junit.Assert.*;

import org.junit.Test;

import DATA.ENERGY.Energy;
import DATA.ENERGY.EnergyFactory;
import DATA.ENERGY.EnergyValidationFactory;
import DATA.IRIS.Iris;
import DATA.IRIS.IrisFactory;
import DATA.IRIS.IrisValidationFactory;
import IA.Avaliador;
import IA.KNN;

public class TestRegression {

	@Test
	public void test() {
		Energy[] energia = EnergyFactory.getAllEnergy();
		Energy[] iris_teste = EnergyValidationFactory.getAllEnergy();
		KNN<Energy> knn = new KNN<Energy>(5,1,energia);
		double EQM = 0;
		for (int i = 0; i < iris_teste.length; i++)
		{
			double result = knn.regression(iris_teste[i]);
			System.out.println("Valor esperado: " + iris_teste[i].getDesiredValue());
			System.out.println("Valor obtido: " + result);
			EQM += Math.pow(result - iris_teste[i].getDesiredValue(),2); 
			//System.out.println("Erro Médio Quadratico: " + EQM);
			
		}
		EQM = EQM/iris_teste.length;
		System.out.println("Erro Médio Quadratico: " + EQM);
		//System.out.println("Tentando classificar uma " + iris_teste[0].getClasse() + ". Resultado = " + knn.classify(iris_teste[0], 2));
		//Avaliador<Iris> av = new Avaliador<Iris>(knn,iris_teste);
		//av.gerarRelatorio();
	}

}
