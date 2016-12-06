package tomato.TesterModule.main;

import java.util.concurrent.ThreadLocalRandom;

public class RandomRuleTuple extends RuleTuple {
	
	Double min, max;
	
	RandomRuleTuple(String stateFormula, String method, String variable, Double min, Double max){
		super(stateFormula, method, variable, "random");
		this.min = min;
		this.max = max;
	}
	
	Double getRandomValue(){
		return ThreadLocalRandom.current().nextDouble(min, max + 1);
	}

}
