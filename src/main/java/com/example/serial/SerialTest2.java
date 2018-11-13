package com.example.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialTest2 implements Serializable {

	/**
	 * serialVersionUID 在序列化是 UID会写入到序列化文件中，在反序列化时会将序列化的值和当前的值进行对比，
	 * 如果相同说明版本一致，序列化成功，如果不一致，版本不兼容，反序列化失败
	 * 
	 * 如果没有显示的定义UID，JAVA序列化机制会根据编译的class自动生成一个uid作为序列化版本比较使用，这种情况下，
	 * 如果class文件没有发生变化，就算经过太多次的编译，uid也不会变化
	 */
//	private static final long serialVersionUID = 1l;

	private String aa;

	/**
	 * transient 修饰的变量默认不会被序列化
	 */
	private transient String bb;
	/**
	 * 成员变量要序列化 也必须要实现serializable
	 */
	private Param param;
	
	public SerialTest2() {
		System.out.println("System.out.println();");
	}
	public SerialTest2(String aa, String bb) {
		System.out.println("SerialTest2(String aa, String bb)");
		this.aa = "not transient " + aa;
		this.bb = "transient " + bb;
		param = new Param();
		param.setA(2);
		param.setCc("cccc");
	}

	public String toString() {
		return aa + "\n" + bb+"\n"+param;
	}

	public static void write() throws FileNotFoundException {
		SerialTest2 sc = new SerialTest2("Test1", "Test2");
		System.out.println("Before:\n" + sc);
		FileOutputStream fout = new FileOutputStream(new File("D://aa"));
		try {
			ObjectOutputStream out1 = new ObjectOutputStream(fout);
			out1.writeObject(sc);
			out1.flush();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(new File("D://aa")));
		SerialTest2 sc2 = (SerialTest2) in1.readObject();
		System.out.println("After:\n" + sc2);
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		/**
		 * Before: not transient Test1 transient Test2
		 */
		write();
//		System.out.println("-----------------------");
//		read();
	}
}
