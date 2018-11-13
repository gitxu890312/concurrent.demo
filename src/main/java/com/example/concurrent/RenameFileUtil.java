package com.example.concurrent;

import java.io.File;

public class RenameFileUtil {

	public static void main(String[] args) {
		String replaceName = "[www.52yzzy.com 吾爱优质资源网]";
		String folder = "E:\\video\\零基础玩转Nginx入门实战高级视频教程";
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
				String targetName = file.getName().substring(indexOf+replaceName.length());
				file.renameTo(new File(file.getParentFile(), targetName));
			}
		}
		
	}
}
