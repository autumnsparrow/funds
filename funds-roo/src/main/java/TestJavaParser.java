import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import japa.parser.JavaParser;
import japa.parser.ParseException;

/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 30, 2013
 *
 */

/**
 * @author sparrow
 *
 */
public class TestJavaParser {

	/**
	 * 
	 */
	public TestJavaParser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JavaParser parser=null;
		try {
			List<String> files=FileUtils.readLines(new File(args[0]), "utf-8");
			for(String file:files){
				System.out.println("parsing file:"+file);
				JavaParser.parse(new File(file));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
