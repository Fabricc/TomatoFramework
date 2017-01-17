package testermodule.main;

import java.util.concurrent.ThreadLocalRandom;

public class RandomRuleTuple extends RuleTuple {
	
	Double doublemin, doublemax;
	int intmin, intmax;
	
	RandomRuleTuple(String stateFormula, String method, String variable, Double min, Double max){
		super(stateFormula, method, variable, "randomDouble");
		this.doublemin = min;
		this.doublemax = max;
	}
	
	RandomRuleTuple(String stateFormula, String method, String variable, int min, int max){
		super(stateFormula, method, variable, "randomInt");
		this.intmin =min;
		this.intmax =max;
	}
	
	Double getRandomDoubleValue(){
		return ThreadLocalRandom.current().nextDouble(doublemin, doublemax + 1);
	}
	
	int getRandomIntValue(){
		return ThreadLocalRandom.current().nextInt(intmin, intmax + 1);
	}

}
