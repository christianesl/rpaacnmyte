package com.accenture.test.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class FileSystem {
	
	public String createDirectory() {
		String directoryName ="";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		directoryName = "C:\\RPA_LinkedIn_Result_"+dateFormat.format(date);
		new File(directoryName).mkdir();
	    System.out.println("Directory has been created as: "+directoryName);
	    
	    return(directoryName);
	  }
	
	public void crearFile(String path, String source, String resourceName){
		try {
			PrintWriter writer = new PrintWriter(path+"\\"+resourceName+".txt", "UTF-8");
			writer.println(source);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void getFileNamesAndPrintMatch(String directory, String skill) throws FileNotFoundException {
		File directoryPath = new File(directory);
		ArrayList<String> resourcesReport = new ArrayList<String>();
		
			
		//List text files only
		System.out.println("\n------------Text files------------");
		File[] files=directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
			
		for (File file : files) {
			System.out.println(file.getName());
			
			
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				// Apertura del fichero y creacion de BufferedReader para poder
				// hacer una lectura comoda (disponer del metodo readLine()).
				archivo = new File (directory+"\\"+file.getName());
				fr = new FileReader (archivo);
				br = new BufferedReader(fr);

				// Lectura del fichero
				String linea;
				while((linea=br.readLine())!=null)
					if(linea.contains(skill)){
						resourcesReport.add(file.getName());
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				
				// En el finally cerramos el fichero, para asegurarnos
				// que se cierra tanto si todo va bien como si salta 
				// una excepcion.
				try{                    
					if( null != fr ){   
						fr.close();     
					}                  
				}catch (Exception e2){ 
					e2.printStackTrace();
				}
			}
		}
		
		System.out.println("\n------------Resources with skill "+skill+"------------");
		HashSet<String> set = new HashSet<>(resourcesReport);
		ArrayList<String> result = new ArrayList<>(set);
		//System.out.println(result.toString());
		
		PrintWriter pw = new PrintWriter(new File(directory+"\\ResourcesWithSkills.csv"));
		StringBuilder sb = new StringBuilder();
		
		sb.append("Resource");
        sb.append(',');
        sb.append("Linkedin_Id");
        sb.append('\n');
        
		for (int i = 0; i < result.size(); i++) {
			String res = result.get(i).replace(".txt", "");
			res = res.replace("[", " ");
			String[] parts = res.split(" ");
			String part1 = parts[0];
			String part2 = parts[1]; 
			
			System.out.println(result.get(i).replace(".txt", ""));
			
			sb.append(part1);
	        sb.append(',');
	        sb.append(part2);
	        sb.append('\n');
			
		}
		
		 pw.write(sb.toString());
		 pw.close();
	     System.out.println("RPA Process Completed!");
	}
			

}