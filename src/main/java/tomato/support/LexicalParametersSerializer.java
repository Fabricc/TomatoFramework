package tomato.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import tomato.ParserModule.Pair;

public class LexicalParametersSerializer {

	public static HashMap<String, Pair> deserialize() {
		HashMap<String, Pair> map = null;
        try
        {
            FileInputStream fis = new FileInputStream("lexicalparams.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.err.println("Class not found");
            c.printStackTrace();
            return null;
        }
        //System.out.println("Deserialized HashMap..");
        return map;
        // Display content using Iterator
	}

}
