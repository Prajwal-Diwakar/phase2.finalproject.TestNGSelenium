package phase2.finalProject.ExcelReader;


	
import org.apache.poi.ss.usermodel.*;

import phase2.finalProject.MediaDetail.MediaDetail;

import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;
	public class ExcelReader {
	    private static final String FILE_PATH = "test-data/Book1.xlsx";

	    public static List<MediaDetail> readSocialMediaDetails() throws IOException {
	        List<MediaDetail> socialMediaDetails = new ArrayList<>();
	        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
	        Workbook workbook = WorkbookFactory.create(fileInputStream);
	        Sheet sheet = workbook.getSheetAt(0);

	        Iterator<Row> rowIterator = sheet.rowIterator();
	        // Skip the header row
	        rowIterator.next();

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            String platform = row.getCell(0).getStringCellValue();
	            String url = row.getCell(1).getStringCellValue();
	            socialMediaDetails.add(new MediaDetail(platform, url));
	        }

	        workbook.close();
	        fileInputStream.close();

	        return socialMediaDetails;
	    }
	


}
