package DATA.IRIS;

import DATA.DatasetType;

public class Iris extends DatasetType {
	double[] attr;
	String nome;
	int id;
	double desiredValue;
	
	@Override
	public double getAttr(int index) {
		return attr[index];
	}
	
	@Override
	public void setAttr(int index, double attr) {
		this.attr[index] = attr;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getClasse() {
		return nome;
	}

	public Iris(double[] attr, String nome, int id)
	{
		this.attr = attr;
		this.nome = nome;
		this.id = id;
	}
	
	@Override
	public int getNumAttr() {
		return 5;
	}

	public double getAttrRange(int index) {
		return 10;
	}
}
