
/**
 * The part of the voting system that holds the ballot and votes information. 
 */

import java.util.HashMap;
import java.util.LinkedList;

class ElectionData {
	
	private LinkedList<String> ballot = new LinkedList<String>();
	private HashMap<String, LinkedList<Integer>> votes = new HashMap<String, LinkedList<Integer>>();


	public ElectionData() {
		
	}

	/**
	 * Stores a single voter's choices in your data structure
	 * @param candidate1 The voter's first place candidate
	 * @param candidate2 The voter's second place candidate
	 * @param candidate3 The voter's third place candidate
	 * @throws DuplicateVotesException When a vote votes for the same candidate more than once
	 * @throws UnknownCandidateException When a candidate that is not on the ballot is voted for
	 */
	public void processVote(String candidate1, String candidate2, String candidate3) throws DuplicateVotesException, UnknownCandidateException{
		
		//Issues with the who the voter wants to vote for
		if(!ballot.contains(candidate1)) {
			
			throw new UnknownCandidateException(candidate1);

		}
		if(!ballot.contains(candidate2)) {
			throw new UnknownCandidateException(candidate2);
		}
		if(!ballot.contains(candidate3)) {
			throw new UnknownCandidateException(candidate3);
		}
		
		if(candidate1.equals(candidate2) || candidate1.contentEquals(candidate3)) {
			throw new DuplicateVotesException(candidate1);
		}
		
		if(candidate2.equals(candidate3) ) {
			throw new DuplicateVotesException(candidate2);
		}
		
		
		
		// Issues with candidate have no previous votes
		if(votes.containsKey(candidate1)){
			
			LinkedList<Integer> firstVote =  votes.get(candidate1);
			firstVote.add(1);
			
			votes.put(candidate1, firstVote);
		}
		else {
			LinkedList<Integer> firstVote =  new LinkedList<Integer>();
			firstVote.add(1);
			
			votes.put(candidate1, firstVote);
		}
		
		
		
		if(votes.containsKey(candidate2)) {
			LinkedList<Integer> secondVote =  votes.get(candidate2);
			secondVote.add(2);
			
			votes.put(candidate2, secondVote);
		}
		else {
			LinkedList<Integer> secondVote =  new LinkedList<Integer>();
			secondVote.add(2);
			
			votes.put(candidate2, secondVote);
		}
		
		
		
		if(votes.containsKey(candidate3)) {
			LinkedList<Integer> thirdVote =  votes.get(candidate3);
			thirdVote.add(3);
			
			votes.put(candidate3, thirdVote);
		}
		else {
			LinkedList<Integer> thirdVote =  new LinkedList<Integer>();
			thirdVote.add(3);

			votes.put(candidate3, thirdVote);
		}	
	}

	
	/**
	 * Adds a candidate to the list of candidates (ballot)
	 * @param candidate The name of the candidate the user wants to add
	 * @throws CandidateExistsException When the candidate is already on the ballot
	 */
	public void addCandidate(String candidate) throws CandidateExistsException {
		
		boolean exists = ballot.contains(candidate);
		
		if(exists) throw new CandidateExistsException(candidate);
		
		ballot.add(candidate);	
	}
	
	
	/**
	 * Determines the winner of the first place votes determined by which candidate with more than 50% of the first place votes
	 * @return The name of the candidate with more than 50% of the the first place votes
	 */
	public String findWinnerMostFirstVotes() {
		
		HashMap<String, Integer> numberOfFirstPlaceVotes = new 	HashMap<String, Integer>();
		String winner = "Runoff required";
		
		int totalVotes = 0;
		
		for(String candidateName : ballot) {
			int candidateFirstPlaceVotes = 0;
			
			if (votes.containsKey(candidateName) == true) {
				LinkedList<Integer> votesForCandidate = votes.get(candidateName);
				
				for(Integer choice : votesForCandidate) {
					if(choice == 1) {
						candidateFirstPlaceVotes++;
						totalVotes++;
					}
				}
				
				numberOfFirstPlaceVotes.put(candidateName, candidateFirstPlaceVotes);
			
			}
			else {
				numberOfFirstPlaceVotes.put(candidateName, 0);
			}
		}
		
		for(String candidateName : ballot) {
			double number =  numberOfFirstPlaceVotes.get(candidateName);
			double calculation =  number / (double) totalVotes;
			if( calculation > 0.5) {
				winner = candidateName;
				break;
			}
		}
		
		return winner;
	}
	
	
	/**
	 * Determines the winner of the the election based on total points
	 * @return The name of the candidate with the most points
	 */
	public String findWinnerMostPoints() {
		
		String winner = ballot.getFirst();
		int maxPoints = 0;
		
		for(String candidate : ballot) {
			int candidatePoints = 0;
			
			if(votes.containsKey(candidate)) {
				LinkedList<Integer> candidatesVotes = votes.get(candidate);
				
				for(Integer value : candidatesVotes) {
					if(value == 1) candidatePoints = candidatePoints + 3;
					
					if(value == 2) candidatePoints = candidatePoints + 2;
					
					if(value == 3) candidatePoints++;
				}
			}
			
			if(candidatePoints > maxPoints) {
				maxPoints = candidatePoints;
				winner = candidate;
			}
		}
		
		return winner;
	}

	
	/**
	 * Gets the ballot field
	 * @return the ballot field
	 */
	public LinkedList<String> getBallot(){
		return this.ballot;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}


