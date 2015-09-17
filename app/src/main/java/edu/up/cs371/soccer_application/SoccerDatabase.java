package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String,SoccerPlayer> hash = new Hashtable<String,SoccerPlayer>();
    public String hashKey(String firstName, String lastName){
        return firstName+"_"+lastName;
    }
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        if(hash.containsKey(hashKey(firstName, lastName))) {
            return false;
        }
        else {

            SoccerPlayer player = new SoccerPlayer(firstName,lastName,uniformNumber,teamName);
            hash.put(hashKey(firstName, lastName), player);
            return true;
        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))) {
            hash.remove(hashKey(firstName,lastName));
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            return hash.get(hashKey(firstName,lastName));
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(hash.containsKey(hashKey(firstName,lastName))){
            SoccerPlayer player = hash.get(hashKey(firstName,lastName));
            player.bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if(teamName == null){
            return hash.size();
        }
        int x = 0;
        Collection<SoccerPlayer> c = hash.values();
        Object[] playerArray = c.toArray();
        for(int i=0;i<hash.size();i++) {
            SoccerPlayer player = (SoccerPlayer)playerArray[i];
            if (player.getTeamName().equals(teamName)) {
                x++;
            }
        }
        return x;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        Collection<SoccerPlayer> c = hash.values();
        Object[] playerArray = c.toArray();
        if (teamName == null){
            if (idx>=hash.size()){
                return null;
            }
            return (SoccerPlayer)playerArray[idx];
        }
        int x = -1;
        for(int i=0;i<hash.size();i++) {
            SoccerPlayer player = (SoccerPlayer)playerArray[i];
            if (player.getTeamName().equals(teamName)) {
                x++;
            }
            if (x == idx){
                x = 0;
                return player;
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        Collection<SoccerPlayer> c = hash.values();
        Object[] playerArray = c.toArray();
        for(int i=0;i<hash.size();i++) {
            SoccerPlayer player = (SoccerPlayer) playerArray[i];
            try {
                PrintWriter pw = new PrintWriter(file);
                pw.println(logString("First Name: "+player.getFirstName()));
                pw.println(logString("Last Name: "+player.getLastName()));
                pw.println(logString("Team: "+player.getTeamName()));
                pw.println(logString("Uniform Number: "+player.getUniform()));
                pw.println(logString("Goals: "+player.getGoals()));
                pw.println(logString("Assists: "+player.getAssists()));
                pw.println(logString("Shots: "+player.getShots()));
                pw.println(logString("Saves: "+player.getSaves()));
                pw.println(logString("Fouls: "+player.getFouls()));
                pw.println(logString("Yellow Cards: "+player.getYellowCards()));
                pw.println(logString("Red Cards: "+player.getRedCards()));
                pw.println(logString(""));
                pw.close();
            } catch (FileNotFoundException fnfe) {
                return false;
            }
        }
        return true;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
