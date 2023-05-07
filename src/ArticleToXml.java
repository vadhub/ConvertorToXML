import java.io.*;
import java.util.Arrays;

public class ArticleToXml {

    private final BufferedReader in;
    private final BufferedWriter out;
    private final String[] tags;

    private ArticleToXml(BufferedReader in, BufferedWriter out, String[] tags) {
        this.in = in;
        this.out = out;
        this.tags = tags;
    }

    public static class Builder {
        private String filename;
        private String fileOut;
        private String[] tags;

        Builder() {
        }

        public Builder setFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder setFileOut(String fileOut) {
            this.fileOut = fileOut;
            return this;
        }

        public Builder setTags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public ArticleToXml build() throws IOException {
            if (fileOut == null) {
                fileOut = filename;
            }

            if (tags == null) {
                tags = new String[]{"<title>", "<authors>", "<text>"};
            }
            return new ArticleToXml(new BufferedReader(new FileReader(filename)),  new BufferedWriter(new FileWriter(fileOut)), tags);
        }

    }

    public void convert() {
        try {
            String title = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            int countTag = -1;

            Object[] closeTags = Arrays.stream(tags).map(str -> (str.charAt(0) + "/" +str.substring(1))).toArray();
            StringBuilder result = new StringBuilder();
            result.append(title).append("\n");
            String str;
            while ((str = in.readLine()) != null) {
                if (str.contains("(.")) {
                    countTag++;
                }
                str = str.replace("(.", tags[countTag]);
                result.append(str.replace("*)", (CharSequence) closeTags[countTag])).append("\n");
            }
            out.write(result.toString());
            out.close();
            in.close();
            System.out.println("File save!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
