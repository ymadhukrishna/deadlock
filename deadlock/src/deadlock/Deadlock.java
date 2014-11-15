package deadlock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deadlock {

	public static void main(String[] args) {
		

//		try {
			
			String sample = "x_y:a_b";
			
			String[] split1 = sample.split(":");
			HashMap<String, String> hashmap1 = new HashMap<String, String>();
			
			for(int i=0; i<split1.length;i++)
			{
				
				String[] split2 = split1[i].split("_");
				
				hashmap1.put(split2[0], split2[1]);
				
				
				
				System.out.println("map key " + hashmap1.containsKey(split2[0]));
				System.out.println("map value:::"+hashmap1.get(split2[0]));
				
				
			}
			
			
			
//			BufferedReader bfReader = new BufferedReader(
//					new FileReader(
//							"/Users/deepikat/git/deadlock/deadlock/src/deadlock/inputRAG.txt"));    //input file path
//
//			int i = 0;
//
//			String line = null;
//			RAG[] outputArray = new RAG[300];
//			while ((line = bfReader.readLine()) != null) {
//
//				String[] input = line.split(" ");
//
//				RAG rag = new RAG();
//				rag.setProcessNr(Integer.parseInt(input[0]));
//				rag.setStatus(input[1].charAt(0));
//				rag.setResourceNr(Integer.parseInt(input[2]));
//				outputArray[i] = rag;
//
//				i++;
//
//			}
//			bfReader.close();
//
//			RAG[] holdList = new RAG[300];
//
//			StringBuffer outputRecord = checkHold(outputArray, holdList);
//			
//			File file = new File("/Users/deepikat/git/deadlock/deadlock/src/output.txt");  //output file
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//			bufferedWriter.write(outputRecord.toString());
//			bufferedWriter.close();
//			System.err.println(outputRecord.toString());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static StringBuffer checkHold(RAG[] ragArray, RAG[] outputArray) {
		StringBuffer outputString = new StringBuffer();
		StringBuffer deadLockProcessNR = new StringBuffer();
		StringBuffer deadLockResourceNR = new StringBuffer();
		RAG holdRAG = new RAG();

		boolean deadlockIdentified = false;
		for (int i = 0; i < getSize(ragArray); i++) {
			boolean holdFound;
			holdFound = false;

			for (int j = 0; j < getSize(outputArray); j++) {

				if ('R' == ragArray[i].getStatus()) {
					// processing Resource Release

					if ((ragArray[i].getResourceNr() == outputArray[j]
							.getResourceNr())
							&& ('H' == outputArray[j].getStatus())) {

						holdFound = true;
						boolean allocate = false;

						for (int k = 0; k < getSize(outputArray); k++) {

							if ((ragArray[i].getResourceNr() == outputArray[k]
									.getResourceNr())
									&& ('W' == outputArray[k].getStatus())) {

								// Change Wait --> Hold
								outputArray[k].allocateResource(outputArray[k]);
								outputString.append("Process "
										+ ragArray[i].getProcessNr()
										+ " releases resource "
										+ ragArray[i].getResourceNr()
										+ " - Resource "
										+ outputArray[k].getResourceNr()
										+ " is allocated to Process "
										+ outputArray[k].getProcessNr() + ".\n");
								allocate = true;
								break;
							}
						}
						if (!allocate) 
						{
							outputString.append("Process "
									+ ragArray[i].getProcessNr()
									+ " releases resource "
									+ ragArray[i].getResourceNr()
									+ " - Resource "
									+ ragArray[i].getResourceNr()
									+ " is now free. \n");
							

						}
						outputArray[j].resetRAG(outputArray[j]);

						break;
					}
				} else {
					// processing Resource Need.

					if ((ragArray[i].getResourceNr() == outputArray[j]
							.getResourceNr())) {
						holdFound = true;

						holdRAG.setResourceNr(ragArray[i].getResourceNr());
						holdRAG.setProcessNr(ragArray[i].getProcessNr());
						holdRAG.setStatus('W');
						outputArray[getSize(outputArray)] = new RAG(holdRAG);

						outputString.append("Process "
								+ ragArray[i].getProcessNr()
								+ " needs resource "
								+ ragArray[i].getResourceNr() + " - Process "
								+ ragArray[i].getProcessNr() + " must wait.\n");
						

						// everytime we put a resource on wait - check for a
						// potential deadlock.
						// -recursion FOR X- SAY CURRENT PROCESS X holding
						// Resource R1
						// check process P1 holding current resource R1
						// Check if P1 is Waiting for any resource R2 - no HOLD
						// == NO DEADLOCK
						// IF R2 OWNER Y WHICH IS SAME AS X == DEADLOCK.
						// ELSE -- GO TO - RECURSION - FOR Y

						deadlockIdentified = checkDeadLock(outputArray,
								holdRAG.getProcessNr(),
								holdRAG.getResourceNr(), deadLockProcessNR,
								deadLockResourceNR);

						break;
					}
				}

			}// end of inner for

			if (deadlockIdentified) {

				deadLockProcessNR.append(ragArray[i].getProcessNr());

				deadLockResourceNR.append(ragArray[i].getResourceNr());

				deadLockProcessNR = sort(deadLockProcessNR);

				deadLockResourceNR = sort(deadLockResourceNR);

				outputString.append("DEADLOCK DETECTED: Processes "
						+ deadLockProcessNR.toString() + " and Resources "
						+ deadLockResourceNR.toString()
						+ " are found in a cycle.");
				
				break;
				// 2, 3, 4, and Resources 2, 3, 6, are found in a cycle.
			}

			if (!holdFound) {
				// add as HOLD

				holdRAG.setResourceNr(ragArray[i].getResourceNr());
				holdRAG.setProcessNr(ragArray[i].getProcessNr());
				holdRAG.setStatus('H');
				outputArray[getSize(outputArray)] = new RAG(holdRAG);

				outputString.append("Process " + ragArray[i].getProcessNr()
						+ " needs resource " + ragArray[i].getResourceNr()
						+ " - Resource " + ragArray[i].getResourceNr()
						+ " is allocated to Process "
						+ ragArray[i].getProcessNr() + ".\n");

				

			}

		}// end of big forloop

		if (!deadlockIdentified) {
			outputString.append("EXECUTION COMPLETED: No deadlock encountered.");
		}
		return outputString;

	}

	public static int getSize(RAG[] ragArray) {
		int size = 0;
		if(ragArray != null)
		{
		for (int i = 0; i < ragArray.length; i++) {
			if (ragArray[i] == null) {
				break;
			}
			size++;
		}
		}
		return size;
	}

	public static boolean checkDeadLock(RAG[] resourceGrp, int processNr,
			int resourceNr, StringBuffer deadLockprocessNr,
			StringBuffer deadLockResourceNr) {

		// -recursion FOR X- SAY CURRENT PROCESS X holding Resource R1
		// check process P1 holding current resource R1
		// Check if P1 is Waiting for any resource R2 - no HOLD == NO DEADLOCK
		// IF R2 OWNER Y WHICH IS SAME AS X == DEADLOCK.
		// ELSE -- GO TO - RECURSION - FOR Y

		// input P R
		// IF OWNER(R) IS WAIT - CONTINUE
		// -LOOP-
		// IF WAITING RESOURCE OWNER IS P -- DEADLOCK
		// ELSE - CALL ( RESOURCEGRP, P, WAITINGRESOURCE)
		// ELSE RETURN - FALSE

		// FIND OWNER OF INPUT RESOURCE
		RAG inputResourceOwner = null;
		RAG holdingProcess = null;

		for (int i = 0; i < getSize(resourceGrp); i++) {
			if (resourceGrp[i].getResourceNr() == resourceNr
					&& resourceGrp[i].getStatus() == 'H') {
				// got holding resource owner
				inputResourceOwner = new RAG(resourceGrp[i]);
				break;
			}
		}
		
		//CANNOT BE DEADLOCK IF PROCESSOWNER IS NOT HOLDING RESOURCE REQUESTED FOR.
		if (inputResourceOwner == null) {
			return false;
		}

		for (int i = 0; i < getSize(resourceGrp); i++) {
			if (resourceGrp[i].getProcessNr() == inputResourceOwner
					.getProcessNr() && resourceGrp[i].getStatus() == 'W') {
				
				deadLockprocessNr.append(resourceGrp[i].getProcessNr());
				deadLockprocessNr.append(",");
				deadLockResourceNr.append(resourceGrp[i].getResourceNr());
				deadLockResourceNr.append(",");
				
				holdingProcess = new RAG(resourceGrp[i]);
				break;
			}
		}
		
		//CANNOT BE A DEADLOCK SITUATION IF PROCESS  IS NEVER PUT IN WAIT STATUS.
		if (holdingProcess == null) {
			return false;
		}
		boolean deadlockFound = false;

		for (int i = 0; i < getSize(resourceGrp); i++) {
			if (resourceGrp[i].getResourceNr() == holdingProcess
					.getResourceNr() && resourceGrp[i].getStatus() == 'H') {
				if (resourceGrp[i].getProcessNr() == processNr) {
					// DEADLOCK found

					deadlockFound = true;
					break;
				}
			}
		}

		//RECURSIVE CALL IF DEADLOCK NOT FOUND.
		if (!deadlockFound) {
			deadlockFound = checkDeadLock(resourceGrp, processNr,
					holdingProcess.getResourceNr(), deadLockprocessNr,
					deadLockResourceNr);
		}

		return deadlockFound;
	}

	public static StringBuffer sort(StringBuffer deadLockResourceNR) {

		String[] deadLockResourcesAfterSplit = deadLockResourceNR.toString()
				.split(",");
		int[] deadLockResourceArray = new int[deadLockResourcesAfterSplit.length];
		
		for (int i = 0; i < deadLockResourceArray.length; i++) {
			deadLockResourceArray[i] = Integer
					.parseInt(deadLockResourcesAfterSplit[i]);
		}

		Arrays.sort(deadLockResourceArray);

		deadLockResourceNR = new StringBuffer();
		for (int i = 0; i < deadLockResourceArray.length; i++) {

			deadLockResourceNR.append(deadLockResourceArray[i]);
			deadLockResourceNR.append(",");

		}

		return deadLockResourceNR;
	}
}
