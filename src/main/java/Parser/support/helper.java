package Parser.support;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by UA06NP on 09/11/2016.
 */
public class helper {
    public static <E> List<E> addToList(E... values) {
        LinkedList<E> list = new LinkedList<E>();
        for (E value : values) {
            list.addLast(value);
        }

        return list;
    }

public static ArrayList<TermOccurrence> extractTerms(String expression){
    String s="";
    int first_char=0;
    ArrayList<TermOccurrence> res = new ArrayList<TermOccurrence>();

    for (int i = 0;i < expression.length(); i++){
        char c = expression.charAt(i);
        if(c==' '||c=='|'||c=='('||c==')'){
            if(s.equals("")) continue;
            else {
                res.add(new TermOccurrence(s, first_char, i));
                s="";
            }
        }else{
            if(s.equals("")) first_char=i;
            s+=c;
        }
    }
    return res;
}



    public static void main(String[] args) {
        String str = "(atBound | thanBound hji ||( jo))";
        System.out.println(extractTerms(str));



    }

}
