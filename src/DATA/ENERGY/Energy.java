package DATA.ENERGY;

import DATA.DatasetType;

public class Energy extends DatasetType {
	double[] attr;
	String nome;
	double desiredValue;
	
	public Energy(double[] attr, String nome, double desiredValue)
	{
		this.attr = attr;
		this.nome = nome;
		this.desiredValue = desiredValue;
	}
	
	@Override
	public int getNumAttr() {
		return 4;
	}

	@Override
	public double getAttr(int index) {
		return attr[index];
	}

	@Override
	public void setAttr(int index, double value) {
		attr[index] = value;
	}

	@Override
	public String getClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDesiredValue() {
		return desiredValue;
	}

}
