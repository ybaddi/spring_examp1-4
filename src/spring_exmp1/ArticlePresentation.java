package spring_exmp1;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

import spring_exmp1.implementations.ArticleData2;
import spring_exmp1.implementations.ArticleMetier;
import spring_exmp1.interfaces.IArticleData;
import spring_exmp1.interfaces.IArticleMetier;

public class ArticlePresentation {
	
	public static void main(String[] args) {
		try {
			File file = new File("src/config.txt");
			Scanner scanner = new Scanner(file);
			String dataClassName = scanner.next();
			String metierClassName = scanner.next();
			System.out.println(dataClassName);
			System.out.println(metierClassName);
			
			Class cData = Class.forName(dataClassName);
			IArticleData articleData = (IArticleData) cData.newInstance();
			
			Class cMetier = Class.forName(metierClassName);
			IArticleMetier articleMetier = (IArticleMetier) cMetier.newInstance();
			
			Method meth = cMetier.getMethod("setArticleData", new Class[] {IArticleData.class});
			meth.invoke(articleMetier, new Object[] {articleData});
			System.out.println(articleMetier.computePrice());
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
