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
	private final String FILE_NAME;
	
	public PreferenceData()
	{
		/*tutorEmail = "";
		tutorPassword = "";
		openOnStartup = availableOnStartup = false;
		timeOnStartup = 0;
		*/
		
		FILE_NAME = "preferences.ini";
	}
	
	public void setPreferences(String tutorEmail, String tutorPassword, 
			boolean openOnStartup, boolean availableOnStartup, double timeOnStartup)
	{
		this.tutorEmail = tutorEmail;
		this.tutorPassword = tutorPassword;
		this.openOnStartup = openOnStartup;
		this.availableOnStartup = availableOnStartup;
		this.timeOnStartup = timeOnStartup;
	}
	
	public void loadFromFile()
	{
		String json = "";
		
		BufferedReader br = null;
        try
        {
	        br = new BufferedReader(new FileReader(new File(FILE_NAME)));
	        String currentLine;
			while((currentLine = br.readLine()) != null)
				json += (currentLine + "\n");
			
			PreferenceData data = new Gson().fromJson(json, PreferenceData.class);
			this.setPreferences(data.tutorEmail, data.tutorPassword, data.openOnStartup, data.availableOnStartup, data.timeOnStartup);
        }
        catch (FileNotFoundException e)
        {
	        e.printStackTrace();
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
	}
	public void saveToFile()
	{
		String data = new Gson().toJson(this);
		
		PrintWriter writer = null;
        try
        {
	        writer = new PrintWriter(new File(FILE_NAME));
	        writer.println(data);
			writer.close();
        }
        catch (FileNotFoundException e)
        {
	        e.printStackTrace();
        }
	}
	
	@Override
    public String toString()
    {
	    return "PreferenceData [tutorEmail=" + tutorEmail + ", tutorPassword="
	            + tutorPassword + ", openOnStartup=" + openOnStartup
	            + ", availableOnStartup=" + availableOnStartup
	            + ", timeOnStartup=" + timeOnStartup + ", FILE_NAME="
	            + FILE_NAME + "]";
    }
}
