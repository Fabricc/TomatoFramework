package testermodule;

public abstract class RuleTuple {
	String stateFormula;
	String method;
	String variable;
	String rule;
	int int_value;
	double double_value;
	
	RuleTuple(String stateFormula, String method, String variable, String  rule){
		this.stateFormula = stateFormula;
		this.method = method;
		this.variable = variable;
		this.rule = rule;
	}
	
	RuleTuple(String stateFormula, String method, String variable, String rule, double value){
		this.stateFormula = stateFormula;
		this.method = method;
		this.variable = variable;
		this.double_value = value;
		this.rule = rule;
	}

	boolean identifyRule(String stateFormula, String method, String variable){
		if(this.stateFormula.equals(stateFormula) &&
				this.method.equals(method) &&
				this.variable.equals(variable)) return true;
		else return false;
	}
	
	String getRule(){
		return this.rule;
	}
	

}
