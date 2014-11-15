package deadlock;

public class RAG {
	int processNr;
	int resourceNr;

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

	/**
	 * @param processNr
	 * @param resourceNr
	 * @param status
	 */
	public RAG(int processNr, int resourceNr, char status) {
		this.processNr = processNr;
		this.resourceNr = resourceNr;
		this.status = status;
	}

	public RAG() {

	}

	public RAG(RAG rag) {
		this.processNr = rag.getProcessNr();
		this.resourceNr = rag.getResourceNr();
		this.status = rag.getStatus();
	}

	public void resetRAG(RAG rag) {
		this.processNr = -1;
		this.resourceNr = -1;
		this.status = 'F';
	}
	
	public void allocateResource ( RAG rag)
	{
		this.processNr = rag.getProcessNr() ;
		this.resourceNr = rag.getResourceNr();
		this.setStatus('H');
	}
	char status;

}
