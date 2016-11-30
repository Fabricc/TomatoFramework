package main.java;

/**
 * Created by UA06NP on 30/11/2016.
 */
public aspect TesterModuleAspect {

    pointcut anyCall() : call(void *.*(..));

    after():anyCall(){
        System.out.println("gotcha");
    }

}
