
/**
 * The part of the voting system that holds the input/output portion
 */

import java.util.LinkedList;
import java.util.Scanner;

public class VotingMachine {
	
	private Scanner keyboard = new Scanner(System.in);
	private ElectionData data = new ElectionData();
	
	
	public VotingMachine() {

	}
	
	/**
	 * Prints out the candidate names on the existing ballot
	 */
	private void printBallot() {
		System.out.println("The candidates are ");
		for (String s : data.getBallot()) {
			System.out.println(s);
		}
	}

	
	/**
	 * The main method the user works with to be able to use
	 * @throws DuplicateVotesException When a vote votes for the same candidate more than once
	 * @throws UnknownCandidateException When a candidate that is not on the ballot is voted for
	 * @throws CandidateExistsException When the candidate is already on the ballot
	 */
	public void screen() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException  {
		
		String candidate1 = null;
		String candidate2 = null;
		String candidate3 = null;
			
		try {
			this.printBallot();
			System.out.println("Who do you want to vote for?");
			candidate1 = keyboard.next();
			candidate2 = keyboard.next();
			candidate3 = keyboard.next();
				
			data.processVote(candidate1, candidate2, candidate3);
				
			System.out.println("You voted for " + candidate1 + ", " + candidate2 + ", and " + candidate3);
		}
		catch (DuplicateVotesException e){
				
			System.out.println("You may not vote for the same candidate twice \n");
			this.screen();
				
		}
		catch (UnknownCandidateException e){
				
			System.out.println("Atleast one of the candidates is not on the ballot, would you like to add them? \n");
			String input = keyboard.next();
					
			if(input.contentEquals("Y") || input.contentEquals("y")) {
				LinkedList<String> ballot = new LinkedList<String>();
					
				ballot = data.getBallot();
				
				if(!ballot.contains(candidate1)) addWriteIn(candidate1);
					
				if(!ballot.contains(candidate2)) addWriteIn(candidate2);
					
				if(!ballot.contains(candidate3)) addWriteIn(candidate3);
			}
			
			this.screen();		
		}
	}
	
	
	/**
	 * Adds a candidate to the ballot
	 * @param candidate The candidate that the user wants to add to the ballot
	 * @throws CandidateExistsException When the candidate is already on the ballot
	 */
	public void addWriteIn(String candidate) throws CandidateExistsException {
		try {
			data.addCandidate(candidate);
			System.out.println("Candidate added succesfully");
		}
		catch(CandidateExistsException e) {
			System.out.println("The candidate Already Exists");
		}	
	}
}
