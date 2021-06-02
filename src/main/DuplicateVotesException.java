
/**
 * An exception thrown when a vote votes for the same candidate more than once
 */

public class DuplicateVotesException extends Exception{
	
	private String candidateName;
	
	public DuplicateVotesException(String candidateName) {
		this.candidateName = candidateName;
	}
}
