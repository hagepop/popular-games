package gamePop;

public class GameData {
	private String dateTime;
	private String gameName;
	
	
	/**
	 * Create a new GameData object with the given information.
	 * 
	 * @param time
	 * 				the date the information was recorded
	 * @param name
	 * 				the name of the game
	 */
	public GameData(String time, String name)
	{
		this.dateTime = time;
		this.gameName = name;
	}
	
	
	/**
	 * Return the name of the game
	 * 
	 * @return
	 * 			name of the game
	 */
	public String getName()
	{
		return this.gameName;
	}
	
	
	/**
	 * Return the date the information was recorded
	 * 
	 * @return
	 * 			date
	 */
	public String getDate()
	{
		return this.dateTime;
	}
}
