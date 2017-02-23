package test;

import org.apache.commons.lang3.StringUtils;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String test ="hello world";
//		System.out.println(StringUtils.split(test, "@_@")[0]);
		String s = "改进了转发功能，能够显示转发次数（但不能查看具体的转发链），被转发时会在“@我“”那里有提示。";
		String [] t = StringUtils.split(s,"@_@");
		System.out.println(t[0]);
		System.out.println(t[1]);
		System.out.println(t.length);
		
		System.out.println("------------------------------------------------------");
		
		String []  ff = s.split("@_@");
		System.out.println(ff.length);
		System.out.println(ff[0]);
	}

}
