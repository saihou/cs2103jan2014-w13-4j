import java.util.ArrayList;
import java.util.Collections;


public class SearchCommand extends DisplayCommand {
	
	private final static String MESSAGE_NO_SEARCH_RESULTS = "ERROR: No records found!";
	
	public SearchCommand(ArrayList<Task> current) {
		super();
		currentTaskList = current;
	}
	
	//constructor used by junit test for initialisation
	public SearchCommand() {
		super();
	}
	
	/**
	 * @author Cai Di
	 */
	@Override
	public void execute(String taskName) {
		Search seeker = new Search(allTasks);
		boolean isSearchingByDate = false;

		taskName = taskName.trim();

		try {
			Integer.parseInt(taskName);
			if (taskName.length() == 6) {
				isSearchingByDate = true;
			}
			else {
				isSearchingByDate = false;
			}
		} catch(NumberFormatException e) {
			isSearchingByDate = false;
		}

		Collections.sort(allTasks);

		if(isSearchingByDate) {
			currentTaskList = seeker.searchByDate(taskName);
		}
		else {
			currentTaskList = seeker.searchByName(taskName);
		}

		if (currentTaskList.size() > 0) {
			formDisplayText(result);
			systemFeedback = "Search results:";
		}
		else {
			systemFeedback = MESSAGE_NO_SEARCH_RESULTS;
		}
		result.setSystemFeedback(systemFeedback);
	}
}
