package DATA;

public abstract class DatasetType {
	
	public abstract int getNumAttr();
	public abstract double getAttr(int index);
	public abstract void setAttr(int index, double value);
	public abstract String getClasse();
	public abstract double getDesiredValue();
}
