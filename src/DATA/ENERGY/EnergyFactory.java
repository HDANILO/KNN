package DATA.ENERGY;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import DATA.IRIS.Iris;
import DATA.IRIS.IrisFactory;

public class EnergyFactory {

	public static Energy[] getAllEnergy()
	{
		Energy[] energia = null;
		String csvFile = IrisFactory.class.getResource("/Resource/energy_regression.txt").getPath();
		BufferedReader br = null;
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			StringBuffer fileb = new StringBuffer();
			while (br.ready()) {
				fileb.append(br.readLine() + "\n");
			}
			String file = fileb.toString();
			        // use comma as separator
			String[] line = file.split("\n");
			energia = new Energy[line.length];
			for(int i = 0; i < line.length; i++)
			{
				String[] item = line[i].split(cvsSplitBy);
				
				double[] d = new double[item.length-1];
				for(int aux = 0; aux < item.length - 1; aux++)
				{
					d[aux] = Double.parseDouble(item[aux]);
				}
				energia[i] = new Energy(d, "Exemplo " + i,Double.parseDouble(item[item.length - 1]));
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		shuffleArray(energia);
		return energia;
	}
	
	static void shuffleArray(Energy[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Energy a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
}
