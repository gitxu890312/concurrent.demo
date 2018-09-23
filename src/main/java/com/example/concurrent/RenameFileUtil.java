package com.example.concurrent;

import java.io.File;

public class RenameFileUtil {

	public static void main(String[] args) {
		String replaceName = "【www.zxit8.com】";
		String folder = "F:\\xum\\wangpan\\video\\030 Java读源码之Netty深入剖析";
		reName(folder, replaceName);
	}
	public static void reName(String folderName,String replaceName) {
		File folder =new File(folderName);
		File[] files = folder.listFiles();
		for(File file:files) {
			if(file.isDirectory()) {
				continue;
			}
			String name = file.getName();
			int indexOf = name.indexOf(replaceName);
			if(indexOf>=0) {
				String targetName = file.getName().replaceAll(replaceName, "");
				file.renameTo(new File(file.getParentFile(), targetName));
			}
		}
		
	}
}
