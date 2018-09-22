package com.example.concurrent.publish;
/**
 * 
 * @author xum890312
 * 对象溢出：对象还没有构造完成，就被其他对象使用或暴露，称为对象溢出，是一种错误的发布对象
 * 
 */
public class Escape {

	private int canBeEscape = 1;
	
	public Escape() {
		new InnerClass();
	}
	
	public class InnerClass{
		public InnerClass() {
			//调用了 Escape的成员变量，此事Escape还没有构造完成
			Escape.this.canBeEscape=6;
		}
	}
}
