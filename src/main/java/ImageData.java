public class ImageData {
    private String title;
    private String explanation;
    private String date;
    private String url;

    public ImageData(String title, String explanation, String date, String url) {
        this.title = title;
        this.explanation = explanation;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
