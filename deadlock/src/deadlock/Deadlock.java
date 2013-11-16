package deadlock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Deadlock {

	public static void main(String[] args) {
		
		
			try {
				BufferedReader bfReader = new BufferedReader(new FileReader("/Users/deepikat/Documents/workspace"
						+ "/deadlock/src/deadlock/inputRAG.txt"));
				
			
				int i =0;
				
				String line = null;
				RAG[] outputArray = new RAG[30];
				while ((line=bfReader.readLine())!= null)
				{
					
					String[] input = line.split(" ");
					
						RAG rag = new RAG();
						rag.setProcessNr(Integer.parseInt(input[0]));
						rag.setStatus(input[1].charAt(0));
						rag.setResourceNr(Integer.parseInt(input[2]));
						outputArray[i] = rag;
					
					i++;
					

				}
				
				String holdStatus = null;
				RAG[] holdList = new RAG[30];
				
				
				
						 String status = checkHold(outputArray, holdList);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				
		
		
		// TODO Auto-generated method stub

	}
	public static String checkHold(RAG[] ragArray , RAG[] outputArray)
	{
		RAG holdRAG= new RAG();
		
		for(int i=1;i <ragArray.length;i++)
		{
			boolean holdFound ;
			holdFound = false; 
			boolean relseFound = false;
			for(int j=0;j<outputArray.length;j++)
			{
//				System.out.println("status****"+ragList.get(i).getStatus());
				if('R' == ragArray[i].getStatus())
				{
					
					if((ragArray[i].getResourceNr() == outputArray[j].getResourceNr()) && ('H'== outputArray[j].getStatus()))
					{ 
						
						holdFound=true;
						relseFound = true;
						System.out.println("****"+ragArray[i].getStatus());
						System.out.println("before if***"+outputArray.length);
						outputArray[j] = null;
						break;
					}
				}
				else 
				{
					if((ragArray[i].getResourceNr() == outputArray[j].getResourceNr()))
					{
						holdFound=true;
						
						ragArray[i].setStatus("WAIT".charAt(0));
							holdRAG.setResourceNr(ragArray[i].getResourceNr());
							holdRAG.setProcessNr(ragArray[i].getProcessNr());
							holdRAG.setStatus(ragArray[i].getStatus());
							outputArray[i]	=	holdRAG;
							System.out.println("Process number list****"+holdRAG.getProcessNr());
							System.out.println("status****"+holdRAG.getStatus());
							break;
					}
				}
				
			}//end of inner for
			
			if(relseFound)
			{
				System.out.println(outputArray.length);
				for(int k=0;k<outputArray.length;k++)
				{
					
					if( (ragArray[i].getResourceNr() == outputArray[k].getResourceNr()) && ('W' == outputArray[k].getStatus()))
						{
							
							holdRAG.setResourceNr(ragArray[k].getResourceNr());
							holdRAG.setProcessNr(ragArray[k].getProcessNr());
							holdRAG.setStatus('H');
							outputArray[i] = holdRAG;
							System.out.println("ready Process number list****"+holdRAG.getProcessNr());
							System.out.println("ready resource number***"+holdRAG.getResourceNr());
							System.out.println("ready status****"+holdRAG.getStatus());
							break;
						}
				}
			}
			if ( !holdFound)
			{
				//add as HOLD
				ragArray[i].setStatus("HOLD".charAt(0));
				
				
				holdRAG.setResourceNr(ragArray[i].getResourceNr());
				holdRAG.setProcessNr(ragArray[i].getProcessNr());
				holdRAG.setStatus(ragArray[i].getStatus());
				outputArray[i]=holdRAG;
				System.out.println("Process number list****"+holdRAG.getProcessNr());
				System.out.println("resource number***"+holdRAG.getResourceNr());
				System.out.println("status****"+holdRAG.getStatus());
//				System.out.println("hold size****"+holdList.size());
			
			}
		}
		return null;
		
	}
	
}
