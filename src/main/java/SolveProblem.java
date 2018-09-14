import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

public class SolveProblem {


    private static final String DASH = "-";
    private static final String DOT ="•";
    private static final String SPACE = " ";
    private static final String NONE = "";
    private static Map<String,String> coding = buildMap();

    // • -

    private static Map<String, String> buildMap() {
        Map<String, String> values = new HashMap<String, String>();


        values.put("•-","a");
        values.put("-•••","b");
        values.put("-•-•","c");
        values.put("-••","d");
        values.put("•","e");
        values.put("••-•","f");
        values.put("--•","g");
        values.put("••••","h");
        values.put("••","i");
        values.put("•---","j");
        values.put("-•-","k");
        values.put("•-••","l");
        values.put("--","m");
        values.put("-•","n");
        values.put("---","o");
        values.put("•--•","p");
        values.put("--•-","q");
        values.put("•-•","r");
        values.put("•••","s");
        values.put("-","t");
        values.put("••-","u");
        values.put("•••-","v");
        values.put("•--","w");
        values.put("-••-","x");
        values.put("-•--","y");
        values.put("--••","z");

        values.put("•----","1");
        values.put("••---","2");
        values.put("•••--","3");
        values.put("••••-","4");
        values.put("•••••","5");
        values.put("-••••","6");
        values.put("--•••","7");
        values.put("---••","8");
        values.put("----•","9");
        values.put("-----","0");

        values.put("••--••","?");
        values.put("--••--",",");
        values.put("•-•-•-",".");

        values.put(" "," ");
        values.put(""," ");
        values.put("\n","\n");
        return values;
    }


    public static void main(String[] args) throws IOException {
        InputStream image = SolveProblem.class.getResourceAsStream("codigo-programador.jpeg");

        BufferedImage im = ImageIO.read(image);
        System.out.println(im.getHeight()+" "+im.getWidth());

        StringBuilder builder = new StringBuilder();
        List<Integer> firsts = new ArrayList<Integer>();
        int top = 10000, min = 10000, bot = -1, max = -1;

        for(int i = 0; i< im.getHeight(); i++){
            for (int j = 0; j< im.getWidth(); j ++){
                Color c1 = new Color(im.getRGB(j,i));
                if(isBlack(c1)){
                    if(i < top){
                        top = i;
                    }
                    if (i > bot){
                        bot = i;
                    }
                    if (j < min){
                        min = j;
                    }
                    if( j > max){
                        max = j;
                    }
                }

            }
        }



        for(int i = top; i< bot; i+=3){

            for (int j =min; j<= max+1;j++){

                Color c1 = new Color(im.getRGB(j,i));

                if(isBlack(c1)){
                    builder.append(1);
                }else{
                    builder.append(0);
                }


            }


            builder.append("\n");
        }

        //System.out.println(builder.toString());


        String message = builder.toString();

        message = message.replace("0100",DOT);

        message = message.replace("1110",DASH);
        message = message.replace("00000000",SPACE);

        message = message.replace("0000",NONE);

        message = message.replace("\n",SPACE);

        //System.out.println(message);
        String val = message.replace(DOT,".");

        val = val.replace(SPACE,"|");

        //System.out.println(val);

        String[] vals = message.split(" ");

        StringBuilder firstProcess = new StringBuilder();

        for(String s : vals){
            //System.out.println("\""+s + "\" "+coding.get(s));
            firstProcess.append(coding.get(s));
        }



        String[] secondProcess = firstProcess.toString().split("           ");

        //System.out.println(secondProcess[0]);

        String[] secondSplitted = secondProcess[1].split(" ");

        StringBuilder secondVal = new StringBuilder();

        for(String s : secondSplitted){
            secondVal.append(hexToString(s));
        }

        String[] lastSplit = secondVal.toString().split("\n\n\n");

        //System.out.println(lastSplit[0]);

        System.out.println(lastSplit[1]);

    }




    public static String hexToString(String hex) throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }
        /*System.out.println(new String(sb.toString().getBytes(), Charset.forName("UTF-8")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("ISO-8859-1")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("cp1252")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("US-ASCII")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("Big5")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("x-mswin-936")));
*/
        return new String(sb.toString().getBytes("UTF-8"));
    }


    private static String grouper(String value){
        StringBuilder s = new StringBuilder();

            for(int i = 0; i< value.length(); i+=2){
                s.append(value.charAt(i));
            }
        return s.toString();
    }

    private static boolean isBlack(Color c){
        return c.getRed() < 100 && c.getBlue() < 100 && c.getGreen() < 100;
    }


}
