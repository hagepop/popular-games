package gamePop;
import java.util.*;

public class TwitchTable {

	/* Table ADT Representation:
	 * Arranges the data in a virtual table.
	 * Two maps, both use the date and time as their keys.
	 * One of the maps has the date mapped to a list of game titles, 
	 * the other has the date mapped to a list containing the no of viewers corresponding to the list of game titles.
	 * Both maps MUST be of the same size.
	 * If date A maps to a list containing N game title records, 
	 * then date A MUST also map to a list containing N number of viewer records.
	 * A list of game titles CANNOT have duplicates, whereas a list of viewers may have duplicates. 
	 */
	private final Map<String, List<String>> GameTitle = new HashMap<String, List<String>>();
	private final Map<String, List<Integer>> ViewerCount = new HashMap<String, List<Integer>>();
	
	
	/**
	 * Add a new record to the map of game titles.
	 * 
	 * @param title
	 * 				Contains the game title and date the data was recorded
	 * @return true 
	 * 				if the record was added successfully, 		
	 * 				false otherwise
	 * @modifies GameTitle
	 */
	public boolean addGTitle(GameData title)
	{
		//Gets the title and date record was taken.
		String gameTitle = title.getName();
		String gameDate = title.getDate();
		
		//defines an empty list for temporary storage
		List<String> games;
		
		//if the date entry exists, we retrieve the list of games it points to, and append
		//gameTitle to it
		if(GameTitle.containsKey(gameDate))
		{
			
			games = GameTitle.get(gameDate);
			if(!games.contains(gameTitle))
			{
				games.add(gameTitle);
				return true;
			}
			else
			{
				return false;
			}
			
		}
		//if the date entry doesnt exist, we create a new list, append gameTitle, then put both
		//in the TwitchTable
		else if(!GameTitle.containsKey(gameDate))
		{
			
			games = new ArrayList<String>();
			games.add(gameTitle);
			GameTitle.put(gameDate, games);
			return true;
			
		}
		
		return false;
	}
	
	
	/**
	 * Add a new record to the map of viewers watching a game.
	 * 
	 * @param title
	 * 				Contains the no of viewers and date the data was recorded
	 * @return true 
	 * 				if the record was added successfully, 		
	 * 				false otherwise
	 * @modifies ViewerCount
	 */
	public boolean addGViewer(GameViewer views)
	{
		//Gets the title and date record was taken.
		String gameDate = views.getDate();
		int gameViewer = views.getViewer();
		
		//defines an empty list for temporary storage
		List<Integer> games;
		
		//if the date entry exists, we retrieve the list of games it points to, and append
		//gameTitle to it
		if(ViewerCount.containsKey(gameDate))
		{
			
			games = ViewerCount.get(gameDate);
			games.add(gameViewer);
			return true;
			
		}
		//if the date entry doesnt exist, we create a new list, append gameTitle, then put both
		//in the TwitchTable
		else if(!ViewerCount.containsKey(gameDate))
		{
			
			games = new ArrayList<Integer>();
			games.add(gameViewer);
			ViewerCount.put(gameDate, games);
			return true;
			
		}
		
		return false;
	}
	
	
	/**
	 * Returns the list containing the top game titles given a date and time
	 * @param date 
	 * 				date and time 
	 * @return
	 * 				a list containing the top game titles for that date
	 */
	public List<String> displayGame(String date)
	{
		List<String> gameNames = new ArrayList<String>();
		gameNames = GameTitle.get(date);
		return gameNames;
	}
	
	
	/**
	 * Returns the list containing the number of viewers corresponding to each game title
	 * @param date 
	 * 				date and time 
	 * @return
	 * 				list containing the number of viewers
	 */
	public List<Integer> displayViewer(String date)
	{
		List<Integer> gameViews = new ArrayList<Integer>();
		gameViews = ViewerCount.get(date);
		return gameViews;
	}
	
	
	/**
	 * @return
	 * 			a set containing all the dates we have data for
	 */
	public Set<String> getKeys()
	{
		Set<String> keys = new HashSet<String>();
		keys = GameTitle.keySet();
		return keys;
	}
	
}

