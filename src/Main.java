import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ArticleToXml articleToXml = new ArticleToXml.Builder()
                //data.txt in scr
                .setFilename("/home/vadim/math/data.txt")
                .setFileOut("/home/vadim/math/data.xml")
                .build();

        articleToXml.convert();

    }
}