import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProjectRootPathFinder {
	public static String findProjectRootPath() {
		try {
			// Access root folder
			String rootFolderName = "OODP-MOBLIMA-Design-Project";			
			File file = new File(".");
			
			if (file.getCanonicalFile().getName().equals(rootFolderName) == true) {
				return file.getCanonicalPath();
			} else {
				while (file.getName().equals(rootFolderName) != true) {
					System.out.println("hi");
					file = file.getCanonicalFile().getParentFile();
					// System.out.println(file.getCanonicalPath());
				}
				
				String projectRootPath = file.getCanonicalPath();
				return projectRootPath;
			}		
		} catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
			return null;
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
			return null;
		}          
	}
	
}
