import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/** 
 * Goutham Rajeev
 * 5/10/13
 * SysTray.java
 * This class is used to access the system tray.
 */

public class SysTray implements ActionListener
{
	private boolean sysTraySupported;
	private MenuItem scheduleItem, openItem, prefItem, checkItem, quit;
	private Image iconImage;
	private MainCoordinator creator;
	
	public SysTray(MainCoordinator creator)
	{
		this.creator = creator;
		
		sysTraySupported = false;
		
		scheduleItem = new MenuItem("Schedule Me!");
		openItem = new MenuItem("Open in Browser");
		prefItem = new MenuItem("Preferences");
		checkItem = new MenuItem("Check for Notifications");
		quit = new MenuItem("Exit");
		
		scheduleItem.addActionListener(this);
		openItem.addActionListener(this);
		prefItem.addActionListener(this);
		checkItem.addActionListener(this);
		quit.addActionListener(this);
	}
	
	public boolean isSupported()
	{
		return sysTraySupported;
	}
	
	public void setup()
	{
		//the following if statement should work in theory - I have not tested this on an unsupported machine
		if(!SystemTray.isSupported())
		{
			System.out.println("SystemTray is not supported");
			sysTraySupported = false;
			return;
		}
		
		//if here, system tray is supported
		sysTraySupported = true;
		
		SystemTray tray = SystemTray.getSystemTray();
		
		iconImage = creator.getLogoImage();
		//scales image to trayIconWidth and height with correct aspect ratio
		//uses manual scaling to use better scaling algorithm than TrayIcon.setImageAutoSize()
		int trayIconWidth = new TrayIcon(iconImage).getSize().width;
		TrayIcon trayIcon = new TrayIcon(iconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH));
		
		PopupMenu popup = new PopupMenu();
		popup.add(scheduleItem);
		popup.add(openItem);
		popup.add(prefItem);
		popup.add(checkItem);
		popup.add(quit);
		trayIcon.setPopupMenu(popup); //opens on right click
		
		try
		{
			tray.add(trayIcon);
        }
        catch(AWTException e)
        {
			System.out.println("TrayIcon could not be added.");
        }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		if(src == scheduleItem)
			schedule();
		else if(src == openItem)
			openBrowser();
		else if(src == prefItem)
			openPrefs();
		else if(src == checkItem)
			checkForNotifs();
		else if(src == quit)
			creator.closeApp();
	}
	
	public void schedule()
	{
		ScheduleWindow popup = new ScheduleWindow(creator);
		popup.showWindow();
	}
	
	public void openBrowser()
	{
		System.out.println("SysTray: Open in Browser clicked");
		if(Desktop.isDesktopSupported())
		{
			try 
			{
				Desktop.getDesktop().browse(new URI("http://www.learntobe.org/dashboard"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			catch (URISyntaxException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void openPrefs()
	{
		PreferencesWindow popup = new PreferencesWindow(creator, false);
		popup.showWindow();
	}
	
	public void checkForNotifs()
	{
		System.out.println("SysTray: Check for notifications clicked");
		PingedData notif = creator.getLTBApi().getTutorNotification();
		
		if(notif != null)
			System.out.println("New notification!");
		else
			System.out.println("No notifications.");
	}
}
