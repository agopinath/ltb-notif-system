import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;

/**
 * Stores user preferences, and saves and loads them from a file. 
 * @author Goutham Rajeev
 */

public class PreferenceData
{
	private String tutorEmail, tutorPassword;
	private boolean openOnStartup, availableOnStartup;	
	private double timeOnStartup;	
	
	// since the preferences file name will not change, we declare it final static
	private final static String DEFAULT_PREFS_FILENAME = "preferences.ini"; 
	
	public PreferenceData() { }
	
	public void setPreferences(String tutorEmail, String tutorPassword, 
			boolean openOnStartup, boolean availableOnStartup, double timeOnStartup)
	{
		this.tutorEmail = tutorEmail;
		this.tutorPassword = tutorPassword;
		this.openOnStartup = openOnStartup;
		this.availableOnStartup = availableOnStartup;
		this.timeOnStartup = timeOnStartup;
	}
		
	public boolean loadFromFile(File inputFile)
	{
		String json = "";
		
		BufferedReader br = null;
        try
        {
	        br = new BufferedReader(new FileReader(inputFile));
	        String currentLine;
			while((currentLine = br.readLine()) != null)
				json += (currentLine + "\n");
			
			PreferenceData data = new Gson().fromJson(json, PreferenceData.class);
			this.setPreferences(data.tutorEmail, data.tutorPassword, data.openOnStartup, data.availableOnStartup, data.timeOnStartup);
			
			return true;
        }
        catch (IOException e)
        {
	        e.printStackTrace();
        }
        finally
        {
        	if(br != null)
        	{
        		try 
        		{
					br.close();
				} 
        		catch (IOException e) 
				{
					e.printStackTrace();
				}
        	}
        }
        
        return false;
	}
	
	public boolean saveToFile(File outputFile)
	{
		String data = new Gson().toJson(this);
		
		PrintWriter writer = null;
        try
        {
	        writer = new PrintWriter(outputFile);
	        writer.println(data);
        }
        catch (FileNotFoundException e)
        {
        	e.printStackTrace();
        	return false;
        } 
        finally
        {
        	writer.close();
        }
        
        return true;
	}
	
	public void loadFromFile() 
	{
		loadFromFile(new File(DEFAULT_PREFS_FILENAME));
	}
	
	public void saveToFile() 
	{
		saveToFile(new File(DEFAULT_PREFS_FILENAME));
	}
	
	@Override
    public String toString()
    {
	    return "PreferenceData [tutorEmail=" + tutorEmail + ", tutorPassword="
	            + tutorPassword + ", openOnStartup=" + openOnStartup
	            + ", availableOnStartup=" + availableOnStartup
	            + ", timeOnStartup=" + timeOnStartup + "]";
    }
	
	public static String getDefaultPrefsFilename() { return DEFAULT_PREFS_FILENAME; }

	public String getTutorEmail() { return tutorEmail; }

	public String getTutorPassword() { return tutorPassword; }

	public boolean shouldOpenOnStartup() { return openOnStartup; }

	public boolean isAvailableOnStartup() { return availableOnStartup; }

	public double getTimeOnStartup() { return timeOnStartup; }
}
