import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by matias on 13/9/18.
 */
public class StringGrouper {
    public static void main(String[] args) throws IOException {
        InputStream text = SolveProblem.class.getResourceAsStream("text.txt");


        BufferedReader reader = new BufferedReader(new InputStreamReader(text));



    }
}
