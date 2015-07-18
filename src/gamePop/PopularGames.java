package gamePop;

import java.io.*;
import java.util.*;

public class PopularGames {
	
	private final static TwitchTable tableObj = new TwitchTable();

	public static void main(String[] args) throws IOException
	{
		try
		{
			String fileName = "test.txt";
			int gamesPerRank = 57;
			int numRanks = 40;
					
			if(createTable(fileName, gamesPerRank, numRanks))
			{
				System.out.println("Table created!");
				outputData();
				System.out.println("CSV created!");
			}
		
		}
		catch(NumberFormatException n)
		{
			System.out.println("Number not entered correctly.");
		}	
	}
	
	
	/**
	 * Creates a table containing the top games for all the given dates, along with their corresponding number of viewers
	 * @param fileName
	 * 					name of the text file containing the data
	 * @param gamesPerRank
	 * 					number of game titles for each rank
	 * @param numRanks
	 * 					number of rankings
	 * @return
	 * 					true if the table was successfully created
	 * 					false otherwise
	 * @throws IOException
	 */
	private static boolean createTable(String fileName, int gamesPerRank, int numRanks) throws IOException
	{
		try
		{
			//READ FILE
			FileReader data = new FileReader(fileName);
			BufferedReader file = new BufferedReader(data);
			
			//OBJECT TO HOLD DATE-GAMETITLE
			GameData gameTitle;
			//OBJECT TO HOLD DATE-GAMEVIEWERS
			GameViewer numViewer;
			
			//VARIABLES TO HOLD DATA
			String gameName="";
			String gameDate="";
			int gameView=0;
			
			//CONTAINS THE ENTIRE TEXT FILE IN ONE LINE
			String line=file.readLine();
			
			//BREAKS THE LINE INTO A NUMBER OF RECORDS USING REGEX
			String allData[] = line.split("((},)|(}],))");
			
			//FLAGS TO TELL ME WHEN I HAVE AN IMPORTANT STRING TOKEN
			int startCount=0;
            int date=99;
            int nameview=99;
            
            //IF 0, CREATE GAMEDATA OBJECT ELSE IF 1 CREATE GAMEVIEWER OBJECT
            int flag=0;
            
            //GOES THROUGH ALL DATA
            for(int j=0; j<allData.length; j++)
            {
            	//SPLITS A RECORD INTO PIECES
            	String oneRecord[] = allData[j].split("\"");
            	
            	//GOES THROUGH EACH TOKEN IN THE RECORD
            	for(int i=0; i<oneRecord.length; i++)
            	{
            		//IF WE FIND A "d"..
            		if(oneRecord[i].equals("d"))
            		{
            			startCount=i;
            			date=startCount+2;
            			nameview=startCount+12;
            		}
            		//CAPTURES THE DATE TOKEN
            		if(i==date)
            		{
            			gameDate=oneRecord[i];
            		}
            		//CHECKS IF THE TOKEN CONTAINS THE WORD "VIEWER"
            		//IF IT DOES, CAPTURE THE NO OF VIEWER TOKEN
            		//ELSE CAPTURE THE GAME NAME TOKEN
            		if(i==nameview)
            		{
            			if(Character.isDigit(oneRecord[i].charAt(0)) && oneRecord[i].toLowerCase().contains("viewers"))
                        {
            				//CONVERTS THE NUMBER OF VIEWERS INTO AN INTEGER THEN SAVES IT
            				String temp = oneRecord[i].replaceAll("viewers|,", "");
                            gameView = Integer.parseInt(temp.substring(0, (temp.length())-2));
            				flag=1;
                        }
            			else
            			{
            				gameName=oneRecord[i];
            				flag=0;
            			}
            		}
            	}
            	//CREATES AN OBJECT DEPENDING ON WHETHER OR NOT THE RECORD CONTAINED THE NAME OF THE GAME, OR THE NUMBER OF VIEWERS
            	if(flag==0)
        		{
        			gameTitle = new GameData(gameDate, gameName);
        			tableObj.addGTitle(gameTitle);
        		}
        		else if(flag==1)
        		{
        			numViewer = new GameViewer(gameDate, gameView);
        			tableObj.addGViewer(numViewer);
        		}
            }
            
			file.close();
   			return true;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
			return false;	
		}
		catch(IOException ioe)
		{
			System.out.println("Exception while reading the file.");
			return false;
		}
	}	
	

	/**
	 * Exports the table into a .csv file
	 */
	private static void outputData()
	{
		try
		{
			FileWriter writer = new FileWriter("out.csv");
			Set<String> keys = tableObj.getKeys();
			List<String> gameNames;
			List<Integer> gameViews;
		
			for(String key : keys)
			{
				gameNames = tableObj.displayGame(key);
				gameViews = tableObj.displayViewer(key);
				
				for(int i=0; i<gameNames.size(); i++)
				{
					writer.append(key);
					writer.append(',');
					writer.append(gameNames.get(i));
					writer.append(',');
					writer.append(gameViews.get(i).toString());
					writer.append('\n');
				}
			}
			writer.flush();
			writer.close();
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
	}


}
