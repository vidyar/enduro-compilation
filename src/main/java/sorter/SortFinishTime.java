package sorter;

import java.util.Iterator;

import models.Participant;
import models.TimeHandler;

public class SortFinishTime extends Sorter {

	@Override
	protected void addInfo(int columnNbr, Iterator itr, TimeHandler time) {
		while (itr.hasNext()) {
			String line = (String) itr.next();
			String[] lines = line.split("; ");
			time.addFinish(new Participant(Integer.parseInt(lines[0])),
					lines[columnNbr]);
			
		}
	}

}