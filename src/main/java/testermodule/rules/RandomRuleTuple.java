package testermodule.rules;

import java.util.concurrent.ThreadLocalRandom;

public class RandomRuleTuple extends RuleTuple {
	
	Double doublemin, doublemax;
	int intmin, intmax;
	
	public RandomRuleTuple(String stateFormula, String method, String variable, Double min, Double max){
		super(stateFormula, method, variable, "randomDouble");
		this.doublemin = min;
		this.doublemax = max;
	}
	
	public RandomRuleTuple(String stateFormula, String method, String variable, int min, int max){
		super(stateFormula, method, variable, "randomInt");
		this.intmin =min;
		this.intmax =max;
	}
	
	public Double getRandomDoubleValue(){
		return ThreadLocalRandom.current().nextDouble(doublemin, doublemax + 1);
	}
	
	public int getRandomIntValue(){
		return ThreadLocalRandom.current().nextInt(intmin, intmax + 1);
	}

}
