package Parser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by UA06NP on 09/11/2016.
 */
public class helper {
    static <E> List<E> addToList(E... values) {
        LinkedList<E> list = new LinkedList<E>();
        for (E value : values) {
            list.addLast(value);
        }

        return list;
    }

}
