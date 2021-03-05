package votecounterproject;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version Spring 2019
 * @author Lucas Stafford
 */
public class ArrayVoteCounter {

    private String[] votes;
    private String[] spoiledVotes;
    private SithSenateMember[] sithSenateMembers;
    private int voteIndex;
    private int spoiledVoteIndex;

    /**
     * Create new ArrayVoteCounter with file
     * Collect election data
     */
    public ArrayVoteCounter() {
        votes = new String[10];
        spoiledVotes = new String[10];
        sithSenateMembers = new SithSenateMember[4];
        voteIndex = 0;
        spoiledVoteIndex = 0;
        setupCandidates();
    }

    /**
     * Initialize 4 Sith Senate Members into the sithSenateMembers array
     */
    private void setupCandidates() {
        sithSenateMembers[0] = new SithSenateMember("Sidius");
        sithSenateMembers[1] = new SithSenateMember("Maul");
        sithSenateMembers[2] = new SithSenateMember("Vader");
        sithSenateMembers[3] = new SithSenateMember("Plagueis");
    }

    /**
     * Add each vote to votes array
     * @param name the full name of the sith senate member
     */
    public void recordVote(String name) {
        if (!name.isEmpty()) {
            if (voteIndex == votes.length) {
                votes = expandCapacity(votes);
            }
            votes[voteIndex] = name;
            voteIndex++;

            switch (name) {
                case "Darth Sidius":
                    sithSenateMembers[0].addVote();
                    break;
                case "Darth Maul":
                    sithSenateMembers[1].addVote();
                    break;
                case "Darth Vader":
                    sithSenateMembers[2].addVote();
                    break;
                case "Darth Plagueis":
                    sithSenateMembers[3].addVote();
                    break;
                default:
                    if (spoiledVoteIndex == spoiledVotes.length) {
                        spoiledVotes = expandCapacity(spoiledVotes);
                    }
                    spoiledVotes[spoiledVoteIndex] = name;
                    spoiledVoteIndex++;
            }
        }
    }

    /**
     * Display results
     * Show percent of votes earned by each candidate
     */
    public void reportResults() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        int validVotes = voteIndex - spoiledVoteIndex;
        System.out.println(validVotes + " valid votes were cast.");
        for (SithSenateMember sithSenateMember : sithSenateMembers) {
            System.out.println("Darth " + sithSenateMember.getSurname() + ": " + getMemberVotes(sithSenateMember.getFullName())
                    + " votes, " + df.format(sithSenateMember.getNumVotes() / (float) validVotes * 100)
                    + "%.");
        }
        System.out.println("There were " + spoiledVoteIndex + " spoiled votes.");
    }

    /**
     * Copy contents of array into a new one of double the original length
     * @param myArray to be expanded
     * @return new array
     */
    private String[] expandCapacity(String[] myArray) {
        System.out.println("Capacity reached! Doubling the array's size.");
        String[] newArray = Arrays.copyOf(myArray, myArray.length * 2);
        return newArray;

    }

    /**
     * Method used for testing
     * @param name - the senate member's name
     * @return the number of votes for the candidate.
     */
    public int getMemberVotes(String name) {
        for (int i = 0; i < sithSenateMembers.length; i++) {
            if(sithSenateMembers[i].getFullName().equalsIgnoreCase(name) ||
                sithSenateMembers[i].getSurname().equalsIgnoreCase(name))
                return sithSenateMembers[i].getNumVotes();
        }
        return 0;
    }

    /**
     * Test helper method
     * @return the vote index
     */
    public int getVoteIndex() {
        return voteIndex;
    }

    /**
     * Getter
     * 
     * @return the array of votes
     */
    public String[] getVotes() {
        return votes;
    }

    /**
     * Getter
     * 
     * @return the array of spoiled votes
     */
    public String[] getSpoiledVotes() {
        return spoiledVotes;
    }
    
    
    
    /**
     * Begin Random Test Zone
     */
    
    /**
     * Builds and runs a random array vote counter object.
     */
    public static void runRandomElectionResults() {
        ArrayVoteCounter voteCounter = new ArrayVoteCounter();
        voteCounter.setupCandidates();
        voteCounter.generateRandomElectionData();
        voteCounter.reportResults();
    }

    /**
     * Generate random election data after shuffling the candidate array contents.
     * Generates decent variation among the candidates.
     */
    private void generateRandomElectionData() {
        int n;
        int ballotCount = ThreadLocalRandom.current().nextInt(99999);
        Collections.shuffle(Arrays.asList(sithSenateMembers));
        for (int i = 0; i < ballotCount; i++) {
            n = ThreadLocalRandom.current().nextInt(0, 100);
            if(0 < n && n < 25)
                recordVote(sithSenateMembers[0].getFullName());
            else if(25 < n && n < 45)
                recordVote(sithSenateMembers[1].getFullName());
            else if(45 < n && n < 74)
                recordVote(sithSenateMembers[2].getFullName());
            else if(74 < n && n < 95)
                recordVote(sithSenateMembers[3].getFullName());
            else
                recordVote("unknown");
        }
    }
    
}