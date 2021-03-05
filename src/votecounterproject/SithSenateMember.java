/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votecounterproject;

/**
 * @version Spring 2019
 * @author Lucas Stafford
 */
public class SithSenateMember {
    private String title;
    private String surname;
    private int numVotes;

    /**
     * Main constructor
     *
     * @param surname name of sith member
     */
    public SithSenateMember(String surname) {
        this.title = "Darth";
        this.surname = surname;
        numVotes = 0;
    }

    /**
     * Getter
     *
     * @return numVotes
     */
    public int getNumVotes() {
        return numVotes;
    }

    /**
     * Increment numVotes
     */
    public void addVote() {
        this.numVotes++;
    }

    /**
     * Getter
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter
     *
     * @return title + surname
     */
    public String getFullName() {
        return title + " " + surname;
    }

}
