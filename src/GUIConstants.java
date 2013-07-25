// class holds constants (mainly string values)
// used in the GUIs
public final class GUIConstants 
{
	private GUIConstants() { }
	
	// constants for GUI windows
	private static final String APP_TITLE = "LTB Desktop App";
	public static final String PREFS_WINDOW_TITLE = APP_TITLE + " - Preferences";
	public static final String SCHEDULE_WINDOW_TITLE = APP_TITLE + " - Schedule Me!";
	public static final String ABOUT_WINDOW_TITLE = APP_TITLE + " - About the App";
	
	// constants for SysTray
	public static final String SYSTRAY_SCHEDULE_ITEM_STRING = "Schedule me!";
	public static final String SYSTRAY_OPEN_BROWSER_ITEM_STRING = "Open in Browser";
	public static final String SYSTRAY_CHECK_NOTIFS_ITEM_STRING = "Check for Notifications";
	public static final String SYSTRAY_PREFS_ITEM_STRING = "Preferences";
	public static final String SYSTRAY_ABOUT_ITEM_STRING = "About";
	public static final String SYSTRAY_EXIT_ITEM_STRING = "Quit";
	
	// constants for popup messages
	public static final String POPUP_MESSAGE_TITLE = APP_TITLE;
	public static final String POPUP_CONNECTION_ERROR = "LearnToBe server appears to be down. Check your network connectivity and try again later.";
	public static final String POPUP_AUTHENTICATION_ERROR = "Could not authenticate with server. Check the supplied email and password.";
	public static final String POPUP_CREDENTIALS_EMPTY = "Email and password fields cannot be empty.";
	public static final String POPUP_CREDENTIALS_INVALID = "Incorrect email and/or password.";
	public static final String POPUP_PREFS_SETUP_NEEDED = "Preferences file does not exist and must be set up first. Try saving your preferences.";
	public static final String POPUP_SUPPORTED_OSES_ERROR = "Supported OSes for running the app on startup are currently Windows 7 and Windows XP only.";
	public static final String POPUP_NO_NEW_NOTIFS = "No students have requested a session for you to take.";
}