package deadlock;

public class RAG {
	int processNr;
	int resourceNr ; 
	public int getProcessNr() {
		return processNr;
	}
	public void setProcessNr(int processNr) {
		this.processNr = processNr;
	}
	public int getResourceNr() {
		return resourceNr;
	}
	public void setResourceNr(int resourceNr) {
		this.resourceNr = resourceNr;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	char status;

}
