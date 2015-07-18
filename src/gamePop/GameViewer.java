package gamePop;

public class GameViewer {
	private String dateTime;
	private int viewerCount;
	
	/**
	 * Create a new GameViewer object with the given information.
	 * 
	 * @param time
	 * 				the date the information was recorded
	 * @param viewer
	 * 				the number of viewers watching a game
	 */
	public GameViewer(String time, int viewer)
	{
		this.dateTime = time;
		this.viewerCount = viewer;
	}
	
	
	/**
	 * Return the number of viewers watching a game
	 * 
	 * @return
	 * 			number of viewers watching
	 */
	public int getViewer()
	{
		return this.viewerCount;
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
