package com.example.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
/**
 * 
 * @author 徐明
 * @date 2018年11月13日
 * @desc
 * Externalizable 
 * 默认不会序列化任何字段，可以通过实现writeExternal接口自定义序列化的字段，甚至可以序列化transient修饰的字段。
 * Externalizable 反序列化时会调用类的默认构造函数，如果没有会报错
 */
public class SerialTest implements Externalizable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 377724426420661657L;

	private String aa;

	private transient String bb;
	public SerialTest() {
		
	}
	public SerialTest(String aa, String bb) {
		this.aa = "not transient " + aa;
		this.bb = "transient " + bb;
	}

	public String toString() {
		return aa + "\n" + bb;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
//		out.writeObject(aa);
//		out.writeObject(bb);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		aa = (String) in.readObject();
//		bb = (String)in.readObject();
	}

	public static void main(String[] args) {
		SerialTest sc = new SerialTest("Test1", "Test2");
		System.out.println("Before:\n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out1 = new ObjectOutputStream(buf);

			out1.writeObject(sc);

			ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));

			SerialTest sc2 = (SerialTest) in1.readObject();
			System.out.println("After:\n" + sc2);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
