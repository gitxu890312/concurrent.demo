package com.example.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author 徐明
 * @date 2018年11月13日
 * @desc
 * 反序列化单例失效问题
 */
public class User implements Serializable{

	private String name;

	private static User instance = new User("zhangsan");

	private User() {

	}

	private User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

//	private Object readResolve() {
//		return instance;
//	}
	public static User getInstance() {
		return instance;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		User user = User.getInstance();
		try {
            FileOutputStream fos = new FileOutputStream(new File("singToneTest.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            fos.close();
            oos.close();
            System.out.println("user"+user.hashCode());
            
            FileInputStream fis= new FileInputStream(new File("singToneTest.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            User newuser = (User) ois.readObject();
            fis.close();
            ois.close();
            System.out.println("newuser"+newuser.hashCode());
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
