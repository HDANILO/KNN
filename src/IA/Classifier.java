package IA;

import DATA.DatasetType;

public abstract class Classifier<T extends DatasetType> {
	abstract public String classify(T elem, int q);
}
