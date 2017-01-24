package testermodule.support;

import testermodule.exceptions.IncorrectConditionException;

public class conditionChecker{
	public conditionChecker(String condition){
		if(condition.equals("<")){
			this.mode=0;
		}else if(condition.equals(">")){
			this.mode=1;
		}else if(condition.equals("=")){
			this.mode=2;
		}else if(condition.equals("<=")){
			this.mode=3;
		}else if(condition.equals(">=")){
			this.mode=4;
		}else this.mode=5;
	}
	
	private int mode;
	
	public boolean compare(double a, double b) throws IncorrectConditionException{
		if(this.mode==0) return (a<b);
		if(this.mode==1) return (a>b);
		if(this.mode==2) return (a==b);
		if(this.mode==3) return (a<=b);
		if(this.mode==4) return (a>=b);
		throw new IncorrectConditionException();
	}
}