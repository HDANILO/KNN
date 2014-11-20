package Teste;

import org.junit.Test;
import DATA.IRIS.Iris;
import DATA.IRIS.IrisFactory;
import DATA.IRIS.IrisValidationFactory;
import IA.Avaliador;
import IA.KNN;

public class TesteClassify {

	@Test
	public void test() {
		Iris[] iris = IrisFactory.getAllIris();
		Iris[] iris_teste = IrisValidationFactory.getAllIris();
		KNN<Iris> knn = new KNN<Iris>(5,2,iris);
		//System.out.println("Tentando classificar uma " + iris_teste[0].getClasse() + ". Resultado = " + knn.classify(iris_teste[0], 2));
		Avaliador<Iris> av = new Avaliador<Iris>(knn,iris_teste);
		av.gerarRelatorio();
	}

}
