package votecounterproject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version Spring 2019
 * @author Lucas Stafford
 */
public class ArrayListVoteCounter {

    private ArrayList<String> votes;
    private ArrayList<String> spoiledVotes;
    private SithSenateMember[] sithSenateMembers;
    
//    // TODO: Remove the index trackers.
//    private int voteIndex;
//    private int spoiledVoteIndex;

    /**
     * Create new ArrayVoteCounter with file
     * Collect election data
     */
    public ArrayListVoteCounter() {
        votes = new ArrayList<>();
        spoiledVotes = new ArrayList<>();
        sithSenateMembers = new SithSenateMember[4];
        
//        // TODO: Remove the index trackers.
//        voteIndex = 0;
//        spoiledVoteIndex = 0;
        
        
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
            // BEGIN EDIT
            votes.add(name);
            // END EDIT

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
                    // BEGIN EDIT
                    spoiledVotes.add(name);
                    // END EDIT
                    
                    // TODO: Remove me
                    //spoiledVoteIndex++;
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
        int validVotes = votes.size() - spoiledVotes.size();
        System.out.println(validVotes + " valid votes were cast.");
        for (SithSenateMember sithSenateMember : sithSenateMembers) {
            System.out.println("Darth " + sithSenateMember.getSurname() + ": " + getSithSenateMemberVotes(sithSenateMember.getFullName())
                    + " votes, " + df.format(sithSenateMember.getNumVotes() / (float) validVotes * 100)
                    + "%.");
        }
        System.out.println("There were " + spoiledVotes.size() + " spoiled votes.");
    }

    /**
     * Copy contents of array into a new one of double the original length
     * @param myArray to be expanded
     * @return new array
     */
//    private String[] expandCapacity(String[] myArray) {
//        String[] newArray = Arrays.copyOf(myArray, myArray.length * 2);
//        return newArray;
//
//    }

    /**
     * Method used for testing
     * @param name - the senate member's name
     * @return the number of votes for the candidate.
     */
    public int getSithSenateMemberVotes(String name) {
        for (int i = 0; i < sithSenateMembers.length; i++) {
            if(sithSenateMembers[i].getFullName().equalsIgnoreCase(name) ||
                sithSenateMembers[i].getSurname().equalsIgnoreCase(name))
                return sithSenateMembers[i].getNumVotes();
        }
        return 0;
    }
    
    /**
     * Getter
     * 
     * @return the votes array list 
     */
    public ArrayList<String> getVotes() {
        return votes;
    }

    /**
     * Getter
     * 
     * @return the spoiled votes array list
     */
    public ArrayList<String> getSpoiledVotes() {
        return spoiledVotes;
    }
    
    
    
    /**
     * Begin Random Test Zone
     */
    
    /**
     * Builds and runs a random array vote counter object.
     */
    public static void runRandomElectionResults() {
        ArrayListVoteCounter voteCounter = new ArrayListVoteCounter();
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