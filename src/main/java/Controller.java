import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controller {
    private List<ImageData> imageDataList;

    public Controller() throws IOException {
        imageDataList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Date date = calendar.getTime();
            String year = Integer.toString(date.getYear() + 1900);
            String month = Integer.toString(date.getMonth() + 1);
            String day = Integer.toString(date.getDate());
            String dateStr = year + "-" + month + "-" + day;
            ImageData imageData = ImageDataDAO.getImageData(dateStr);
            imageDataList.add(imageData);
        }
    }

    public List<ImageData> getImageDataList() {
        return imageDataList;
    }
}
