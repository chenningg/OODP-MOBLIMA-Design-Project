package managers;

import movie_entities.SystemSettings;
import utils.ProjectRootPathFinder;
import utils.SerializerHelper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SystemSettingsManager {
	// Attributes
	private SystemSettings systemSettings;
	
	private Scanner sc = new Scanner(System.in);
	
	private static SystemSettingsManager single_instance = null;
	
	public static SystemSettingsManager getInstance() {
		if (single_instance == null)
			single_instance = new SystemSettingsManager();
		return single_instance;
	}
	
	
	// Constructor
	private SystemSettingsManager() {
		SystemSettings serializedObject = this.load();
		if (serializedObject != null) {
			this.systemSettings = serializedObject;
		} else {
			this.systemSettings = new SystemSettings();
			this.save();
		}
	}


	// Public exposed methods to app
	public double getPrice(String key) {
		return this.systemSettings.getPrice(key);
	}
	
	public double getPrice(LocalDate date) {
		if (this.systemSettings.isHoliday(date)) {
			return this.systemSettings.getPrice("HOLIDAY");
		} else {
			return 0;
		}
	}
	
	public void displayMenu() {
		int choice;
		
		do {
	        System.out.println("=================== SystemSettings Menu ===================\n"+
	                " 1. View Setting                                         \n"+
	                " 2. Add Setting                                           \n"+
	                " 3. Change Setting                                        \n"+
	                " 4. Delete Setting                                        \n"+
	                " 0. Back to StaffApp                                      ");
			System.out.println("Enter choice: ");			
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					this.viewSystemSetting(sc);
					break;
				case 2:
					this.addSystemSetting(sc);
					break;
				case 3: 
					this.changeSystemSetting(sc);
					break;
				case 4:
					this.deleteSystemSetting(sc);
					break;
				case 0:
					System.out.println("Back to StaffApp......");
					break;
				default:
					System.out.println("Invalid choice. Please choose between 0-4.");
					break;
			}

		} while (choice!=0);
		
		this.save();
	}
	
	
	// Private CRUD methods
	private void viewSystemSetting(Scanner sc) {
		int choice;
		
		do {
	        System.out.println(	"=================== View SystemSettings ===================\n"+
	                		   	" 1. All Price References                                  \n"+
	                		   	" 2. Holiday References                                    \n"+
				                " 3. Default Prices                                        \n"+
				                " 4. Day-of-the-Week Prices                                \n"+
				                " 5. Holiday Prices                                        \n"+
				                " 6. Movie Format Prices                                   \n"+
				                " 7. Ticket Type Prices                                    \n"+
				                " 8. Cinema Type Prices                                    \n"+
				                " 0. Back to movie_entities.SystemSettings Menu                           ");
			System.out.println("Enter choice:");
			choice = sc.nextInt();
				
			switch (choice) {
				case 1:
					this.systemSettings.viewSetting("priceReference");
					break;
				case 2:
					this.systemSettings.viewSetting("holidayReference");
					break;
				case 3: 
					this.systemSettings.viewSetting("default$");
					break;
				case 4:
					this.systemSettings.viewSetting("dayOfWeek$");
					break;
				case 5:
					this.systemSettings.viewSetting("holiday$");
					break;
				case 6:
					this.systemSettings.viewSetting("movieFormat$");
					break;
				case 7:
					this.systemSettings.viewSetting("ticketType$");
					break;
				case 8:
					this.systemSettings.viewSetting("cinemaType$");
					break;
				case 0:
					System.out.println("Back to movie_entities.SystemSettings Menu......");
					break;
				default:
					System.out.println("Invalid choice. Please choose between 0-8.");
					break;
			}
		} while (choice!=0);
	}
	
	private void addSystemSetting(Scanner sc) {
		int choice;
		
		do {
	        System.out.println(	"==================== Add SystemSettings ===================\n"+
			        		   	" 1. New Holiday Reference                                 \n"+
			        		   	" 2. New Movie Format                                      \n"+
				                " 3. New Ticket Type                                       \n"+
				                " 4. New Cinema Type                                       \n"+
				                " 0. Back to movie_entities.SystemSettings Menu                           ");
			System.out.println("Enter choice: ");			
			choice = sc.nextInt();
				
			switch (choice) {
				case 1:
					LocalDate newHolidayDate;
					String newHolidayName;
					
					System.out.println("Enter date of holiday in format YYYY-MM-DD: ");
					newHolidayDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					
					System.out.println("Enter name of holiday: ");
					newHolidayName = sc.next().toUpperCase();
					
					this.systemSettings.addSetting("holidayReference", newHolidayDate, newHolidayName);
					this.systemSettings.viewSetting("holidayReference");
					break;
				case 2:
					String newMovieFormatName;
					double newMovieFormatModifier;
					
					System.out.println("Enter name of new movie format: ");
					newMovieFormatName = sc.next().toUpperCase();
					
					System.out.println("Enter price modifier of new movie format: ");
					newMovieFormatModifier = sc.nextDouble();
					
					this.systemSettings.addSetting("movieFormat$", newMovieFormatName, newMovieFormatModifier);
					this.systemSettings.viewSetting("movieFormat$");
					break;
				case 3: 
					String newTicketTypeName;
					double newTicketTypeModifier;
					
					System.out.println("Enter name of new ticket type: ");
					newTicketTypeName = sc.next().toUpperCase();
					
					System.out.println("Enter price modifier of new ticket type: ");
					newTicketTypeModifier = sc.nextDouble();
					
					this.systemSettings.addSetting("ticketType$", newTicketTypeName, newTicketTypeModifier);
					this.systemSettings.viewSetting("ticketType$");
					break;
				case 4:
					String newCinemaTypeName;
					double newCinemaTypeModifier;
					
					System.out.println("Enter name of new cinema type: ");
					newCinemaTypeName = sc.next().toUpperCase();
					
					System.out.println("Enter price modifier of new cinema type: ");
					newCinemaTypeModifier = sc.nextDouble();
					
					this.systemSettings.addSetting("cinemaType$", newCinemaTypeName, newCinemaTypeModifier);
					this.systemSettings.viewSetting("cinemaType$");
					break;
				case 0:
					System.out.println("Back to movie_entities.SystemSettings Menu......");
					break;
				default:
					System.out.println("Invalid choice. Please choose between 0-4.");
					break;
			}
		} while (choice!=0);
	}
	
	private void changeSystemSetting(Scanner sc) {
		int choice;
		
		do {
	        System.out.println(	"=================== Change SystemSettings ==================\n"+
			        		   	" 1. Holiday Reference                                     \n"+
			        		   	" 2. Movie Format Prices                                   \n"+
				                " 3. Ticket Type Prices                                    \n"+
				                " 4. Cinema Type Prices                                    \n"+
				                " 5. Day-of-the-Week Prices                                \n"+
				                " 6. Holiday Prices                                        \n"+
				                " 7. Default Prices                                        \n"+
				                " 0. Back to SystemSettings Menu                           ");
			System.out.println("Enter choice: ");			
			choice = sc.nextInt();
				
			switch (choice) {
				case 1:
					this.systemSettings.viewSetting("holidayReference");
					LocalDate newHolidayDate;
					String newHolidayName;
					
					System.out.println("Enter date of holiday you want to change in format YYYY-MM-DD: ");
					newHolidayDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					
					System.out.println("Enter new name of holiday: ");
					newHolidayName = sc.next().toUpperCase();
					
					this.systemSettings.updateSetting("holidayReference", newHolidayDate, newHolidayName);
					this.systemSettings.viewSetting("holidayReference");
					break;
				case 2:
					this.systemSettings.viewSetting("movieFormat$");
					String newMovieFormatName;
					double newMovieFormatModifier;
					
					System.out.println("Enter name of movie format you want to change: ");
					newMovieFormatName = sc.next().toUpperCase();
					
					System.out.println("Enter new price modifier: ");
					newMovieFormatModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("movieFormat$", newMovieFormatName, newMovieFormatModifier);
					this.systemSettings.viewSetting("movieFormat$");
					break;
				case 3: 
					this.systemSettings.viewSetting("ticketType$");
					String newTicketTypeName;
					double newTicketTypeModifier;
					
					System.out.println("Enter name of ticket type you want to change: ");
					newTicketTypeName = sc.next().toUpperCase();
					
					System.out.println("Enter new price modifier: ");
					newTicketTypeModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("ticketType$", newTicketTypeName, newTicketTypeModifier);
					this.systemSettings.viewSetting("ticketType$");
					break;
				case 4:
					this.systemSettings.viewSetting("cinemaType$");
					String newCinemaTypeName;
					double newCinemaTypeModifier;
					
					System.out.println("Enter name of cinema type you want to change: ");
					newCinemaTypeName = sc.next().toUpperCase();
					
					System.out.println("Enter new price modifier: ");
					newCinemaTypeModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("cinemaType$", newCinemaTypeName, newCinemaTypeModifier);	
					this.systemSettings.viewSetting("cinemaType$");
					break;
				case 5:
					this.systemSettings.viewSetting("dayOfWeek$");
					String newDayOfWeekName;
					double newDayOfWeekModifier;
					
					System.out.println("Enter name of day of the week you want to change: ");
					newDayOfWeekName = sc.next().toUpperCase();
					
					System.out.println("Enter new price modifier: ");
					newDayOfWeekModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("dayOfWeek$", newDayOfWeekName, newDayOfWeekModifier);	
					this.systemSettings.viewSetting("dayOfWeek$");
					break;			
				case 6:
					this.systemSettings.viewSetting("holiday$");
					double newHolidayModifier;
					
					System.out.println("Enter new price modifier for holidays: ");
					newHolidayModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("holiday$", "HOLIDAY", newHolidayModifier);	
					this.systemSettings.viewSetting("holiday$");
					break;		
				case 7:
					this.systemSettings.viewSetting("default$");
					String newDefaultName;
					double newDefaultModifier;
					
					System.out.println("Enter the default setting you want to change: ");
					newDefaultName = sc.next().toUpperCase();
					
					System.out.println("Enter new default price: ");
					newDefaultModifier = sc.nextDouble();
					
					this.systemSettings.updateSetting("default$", newDefaultName, newDefaultModifier);	
					this.systemSettings.viewSetting("default$");
					break;			
				case 0: 
					break;
				default:
					System.out.println("Invalid choice. Please choose between 0-7.");
					break;
			}
		} while (choice!=0);
		
		System.out.println("Back to SystemSettings Menu......");
	}
		
	private void deleteSystemSetting(Scanner sc) {
		int choice;
		
		do {
	        System.out.println(	"=================== Delete SystemSettings ==================\n"+
			        		   	" 1. Holiday Reference                                     \n"+
			        		   	" 2. Movie Format 											\n"+
				                " 3. Ticket Type                                    		\n"+
				                " 4. Cinema Type 		                                    \n"+
				                " 0. Back to SystemSettings Menu                           ");
	        System.out.println("Enter choice:");
			choice = sc.nextInt();
				
			switch (choice) {
				case 1:
					this.systemSettings.viewSetting("holidayReference");
					
					if (this.systemSettings.getSystemInfoHash().get("holidayReference").size()==0) {
						System.out.println("No data!");
						break;
					} else {				
						LocalDate newHolidayDate;
						
						System.out.println("Enter date of holiday you want to delete in format YYYY-MM-DD: ");
						newHolidayDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						
						this.systemSettings.deleteSetting("holidayReference", newHolidayDate);
						this.systemSettings.viewSetting("holidayReference");
						break;
					}
				case 2:
					this.systemSettings.viewSetting("movieFormat$");
					if (this.systemSettings.getSystemInfoHash().get("movieFormat$").size()==0) {
						System.out.println("No data!");
						break;
					} else {	
						String newMovieFormatName;
						
						System.out.println("Enter name of movie format you want to delete: ");
						newMovieFormatName = sc.next().toUpperCase();
						
						this.systemSettings.deleteSetting("movieFormat$", newMovieFormatName);
						this.systemSettings.viewSetting("movieFormat$");
						break;
					}
				case 3: 
					this.systemSettings.viewSetting("ticketType$");
					
					if (this.systemSettings.getSystemInfoHash().get("ticketType$").size()==0) {
						System.out.println("No data!");
						break;
					} else {	
						String newTicketTypeName;
						
						System.out.println("Enter name of ticket type you want to delete: ");
						newTicketTypeName = sc.next().toUpperCase();
						
						this.systemSettings.deleteSetting("ticketType$", newTicketTypeName);
						this.systemSettings.viewSetting("ticketType$");
						break;
					}
				case 4:
					this.systemSettings.viewSetting("cinemaType$");
					
					if (this.systemSettings.getSystemInfoHash().get("cinemaType$").size()==0) {
						System.out.println("No data!");
						break;
					} else {	
						String newCinemaTypeName;
						
						System.out.println("Enter name of cinema type you want to delete: ");
						newCinemaTypeName = sc.next().toUpperCase();
						
						this.systemSettings.deleteSetting("cinemaType$", newCinemaTypeName);	
						this.systemSettings.viewSetting("cinemaType$");
						break;
					}
				case 0: 
					break;
				default:
					System.out.println("Invalid choice. Please choose between 0-4.");
					break;
			}
		} while (choice!=0);
		
		System.out.println("Back to SystemSettings Menu......");
	}

	
	// Private Serialization and Deserialization
	private void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/system_settings/system_settings.dat";
		SerializerHelper.serializeObject(this.systemSettings, filePath);
	}
	
	private SystemSettings load() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/system_settings/system_settings.dat";
		return (SystemSettings) SerializerHelper.deSerializeObject(filePath);
	}
}