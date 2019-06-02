package Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ReadFile {
	public class FileManager {


		public ArrayList getLines(File Archivo) {
			ArrayList lineas = new ArrayList();
			try {
				BufferedReader b = new BufferedReader(new FileReader(Archivo));
				String readLine = "";
				while ((readLine = b.readLine()) != null) {
					lineas.add(readLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return lineas;

		}

	}

}
