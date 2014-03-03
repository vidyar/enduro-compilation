package controllers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.ModelInitiator;
import models.RaceEvent;
import models.Time;
import race.*;
import utils.FileReader;
import utils.FileWriter;

public class FormatterController {
	
    public static final int SIMPLE_RACE = 0, LAP_RACE = 1, TIME_RACE = 2, STAGE_RACE = 3, SORT=0, DONT_SORT=1;

    private RaceEvent raceEvent;

    /**
     * Returns a string of the results table generated by the files at each
     * location.
     *
     * @param startPath  , URL-address of file containing start times.
     * @param finishPath , URL-address of file containing start times.
     * @param namePath   , URL-address of file containing start times.
     * @param printLimit , Number of laps to be displayed in the result table.
     * @return A fully formatted result table as a string
     * @throws FileNotFoundException
     */
    public String result(String[] startPath, String[] finishPath, String namePath, int raceType, String limit, int printLimit, int sortOption) throws FileNotFoundException {
        Race race;
        switch (raceType) {
            case SIMPLE_RACE:
                race = new SimpleRace();
                break;
            case LAP_RACE:
                int lapLimit = Integer.parseInt(limit);
                race = new LapRace(lapLimit);
                break;
            case TIME_RACE:
                Time timeLimit = new Time(limit);
                race = new TimeRace(timeLimit);
                break;
            case STAGE_RACE:
                race = new StageRace();
                break;
            default:
                throw new IllegalArgumentException("Racetypen finns inte!");
        }
        raceEvent = new RaceEvent(race);
        FileReader fr = new FileReader();

        Iterator fileIterator = fr.readFileByLine(namePath);
        ModelInitiator initiator = new ModelInitiator(fileIterator, raceEvent);


        Iterator[] iterators = new Iterator[startPath.length];
        for (int i = 0; i < startPath.length; i++) {
            iterators[i] = fr.readFileByLine(startPath[i]);
        }
        initiator.registerStartTimes(iterators);

        iterators = new Iterator[finishPath.length];
        for (int i = 0; i < finishPath.length; i++) {
            iterators[i] = fr.readFileByLine(finishPath[i]);
        }
        initiator.registerFinishTimes(iterators);
        
        if(sortOption == SORT){
        	sort();
        	return raceEvent.print(printLimit, SORT);
        }
        
        return raceEvent.print(printLimit, DONT_SORT);
    }
    
    public void resetRace(){
    	raceEvent = null;
    }
    
    public void sort(){
    	raceEvent.sort();
    }

}
