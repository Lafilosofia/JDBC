package day01;

import org.junit.Test;

/**
 * String提供的方法
 * starWith()
 * endWith()
 * @author 86180
 *
 */
public class DemoString08 {
	@Test
	public void tttt(){
		System.out.println("呵呵");
	}
	
	@Test
	public void dddd(){
		System.out.println("ddd");
	}
	
	public void testStartWith(){
		/*
		 * String类提供了starsWith(String str)
		 * 检测一个字符串是否以之地以指定字符串str开头
		 */
		String str = "http://baidu.com";
		if(str.startsWith("http")){
			System.out.println("是");
		}else{
			System.out.println("否");
		}
	}
	
	public void testEndWith(){
		/*
		 * String类提供了starsWith(String str)
		 * 检测一个字符串是否以之地以指定字符串str开头
		 */
		String str = "http://baidu.com";
		if(str.endsWith("http")){
			System.out.println("是");
		}else{
			System.out.println("否");
		}
	}
}
