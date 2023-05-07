import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ArticleToXml articleToXml = new ArticleToXml.Builder()
                .setFilename("/home/vadim/math/data.txt")
                .setFileOut("/home/vadim/math/data1.txt")
                .build();

        articleToXml.convert();

    }
}