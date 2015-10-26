package com.nbh.util;
import java.io.*;

// class to create m3u files for a particular directory.
// The directory contains all the songs for a particular
// album.
// This class will create the m3u file.
// Format

public class M3UFileCreator{


	private static final String EXTENTION=".M3U";


	public static void 	main(String[] args) throws Exception{
		String directory=null; //"C:\\Documents and Settings\\uinxh\\My Documents\\My music\\The cure\\The Cure";

		if (args.length>0){
			directory = args[0];
		}else{
			System.out.println("Nothing to do !! ");
		}

		M3UFileCreator creator = new M3UFileCreator();
		creator.createM3UFile(directory);

	}


	public M3UFileCreator(){
	}


	public void createM3UFile(String directory) throws IOException{


		String[] files = this.getFiles(directory);
		String fileName = this.getParentName(directory);
		fileName = directory+"\\"+fileName+EXTENTION;

		System.out.println("fileName: "+fileName);

		File f = new File(fileName);
		Writer writer = new FileWriter(f);

		try{

			for (int i=0;i<files.length;i++){
				writer.write(files[i]+"\r\n");
				System.out.println("adding "+files[i]);
			}
		}finally{
			writer.close();
		}
	}



	private String[] getFiles(String directory){


		FilenameFilter filter = new FilenameFilter() {
		        public boolean accept(File dir, String name) {
		            if (name.indexOf(".mp3")>-1 || name.indexOf(".wma")>-1 || name.indexOf(".m4a")>-1 || name.indexOf(".flac")>-1){
						return true;
					}
					return false;
		        }
		    };

		File files =new File(directory);
		return (files.list(filter));
	}


	private String[] getSubDirectories(String directory){


		FilenameFilter filter = new FilenameFilter() {
		        public boolean accept(File dir, String name) {
		            if (name.indexOf(".")>-1){
						return false;
					}
					return true;
		        }
		    };

		File files =new File(directory);
		return (files.list(filter));
	}





	private String getParentName(String directory) throws IOException{

		int toSlash = directory.lastIndexOf("\\");
		if (toSlash>-1){
			return (directory.substring(++toSlash));
		}
		return "songs";
	}

}