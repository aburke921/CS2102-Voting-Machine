
/**
 * An exception thrown when a candidate that is not on the ballot is voted for
 */

public class UnknownCandidateException extends Exception{

	private String candidateName;
	
	public UnknownCandidateException(String candidateName) {
		this.candidateName = candidateName;
	}
}
