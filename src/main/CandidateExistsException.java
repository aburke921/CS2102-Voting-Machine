
/**
 * An exception thrown when the candidate is already on the ballot
 */

public class CandidateExistsException extends Exception{
	
	private String candidateName;
	
	public CandidateExistsException(String candidateName) {
		this.candidateName = candidateName;
	}
}
