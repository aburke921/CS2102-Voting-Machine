
// Ashley Burke and Ally Salvino

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

public class Examples {

	//tests findWinnerMostFirstVotes()
	//when there is a clear winner
	ElectionData Setup1 () {
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes
		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "ziggy", "husky");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	
	
	//tests findWinnerMostFirstVotes()
	//when there is a tie
	ElectionData Setup2 () {
		
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes
		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "ziggy", "husky");
			ED.processVote("husky", "gompei", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	
	
	//tests findWinnerMostPoints()
	//when there is a tie
	ElectionData Setup3 () {
		
		ElectionData ED = new ElectionData();

		// put candidates on the ballot
		try {

			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");

		} catch (Exception e) {}

		// cast votes
		try {

			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("gompei", "husky", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");
			ED.processVote("husky", "gompei", "ziggy");

		} catch (Exception e) {}

		return(ED);

	}
	
	
	LinkedList<String> checkAllWinners(){
		
		LinkedList<String> ballot = new LinkedList<String>();
		ballot.add("gompei");
		ballot.add("husky");
		ballot.add("ziggy");
		
		HashMap<String, LinkedList<Integer>> votes = new HashMap<String, LinkedList<Integer>>();
		
		LinkedList<Integer> gompeiVotes = new LinkedList<Integer>();
		gompeiVotes.add(1);
		gompeiVotes.add(1);
		gompeiVotes.add(2);
		gompeiVotes.add(2);
		
		votes.put("gompei" , gompeiVotes );

		
		LinkedList<Integer> huskyVotes = new LinkedList<Integer>();
		huskyVotes.add(2);
		huskyVotes.add(2);
		huskyVotes.add(1);
		huskyVotes.add(1);
		
		votes.put("husky", huskyVotes);
		
		LinkedList<Integer> ziggyVotes = new LinkedList<Integer>();
		ziggyVotes.add(3);
		ziggyVotes.add(3);
		ziggyVotes.add(3);
		ziggyVotes.add(3);
		
		votes.put("ziggy", ziggyVotes);
		
		
		HashMap<Integer, LinkedList<String>> points = new HashMap<Integer, LinkedList<String>>();
		
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
				
				if(points.containsKey(candidatePoints)) {
					LinkedList<String> candidates = points.get(candidatePoints);
					candidates.add(candidate);
					
					points.put(candidatePoints, candidates);
					
				}
				else {
					LinkedList<String> candidates = new LinkedList<String>();
					candidates.add(candidate);
					
					points.put(candidatePoints, candidates);
				}
			
			}
			
			if(candidatePoints > maxPoints) maxPoints = candidatePoints;
			
		}
		

	
		return points.get(maxPoints);

	}
	
	
	
	
	










	//tests findWinnerMostFirstVotes()
	//when there is a clear winner
	@Test
	public void testMostFirstWinner1() {
		assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
	}
	
	
	//tests findWinnerMostFirstVotes()
	//when there is a tie
	@Test
	public void testMostFirstWinner2() {
		assertEquals ("Runoff required", Setup2().findWinnerMostFirstVotes());
	}
	
	
	//tests findWinnerMostPoints()
	@Test
	public void testMostFirstWinner3() {
		
		LinkedList<String> optionalWinners = checkAllWinners();
		
		String suposedWinner = Setup3().findWinnerMostPoints();
		
		assertTrue(optionalWinners.contains(suposedWinner));
		
	}
	
	
	@Test(expected = UnknownCandidateException.class)
	public void testUnknownCandidateException1() throws UnknownCandidateException, DuplicateVotesException
	{
		Setup1().processVote("bob", "gompei", "husky");
	}
	
	
	@Test(expected = UnknownCandidateException.class)
	public void testUnknownCandidateException2() throws UnknownCandidateException, DuplicateVotesException
	{
		Setup1().processVote("gompei", "gompei", "bob");
	}
	
	
	@Test(expected = DuplicateVotesException.class)
	public void testDuplicateVotesException() throws UnknownCandidateException, DuplicateVotesException
	{
		Setup1().processVote("gompei", "gompei", "husky");
	}
	
	
	@Test(expected = CandidateExistsException.class)
	public void testAddCandidate() throws CandidateExistsException
	{
		Setup1().addCandidate("gompei");
	}
	
	


}
