package tomato.TesterModule.test;

import tomato.TesterModule.main.stateFormulaImplementation;

public class QosListener implements stateFormulaImplementation  {
	int qos;
	
	public boolean execute(){
		if(qos<3) return true;
		else return false;
	}

}