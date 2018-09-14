import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

public class SolveProblem {


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
        values.put("","");
        values.put("\n","\n");
        return values;
    }


    public static void main(String[] args) throws IOException {
        InputStream image = SolveProblem.class.getResourceAsStream("codigo-programador.jpeg");

        BufferedImage im = ImageIO.read(image);
        System.out.println(im.getHeight()+" "+im.getWidth());

        StringBuilder builder = new StringBuilder();
        List<Integer> firsts = new ArrayList<Integer>();
        for(int i = 53; i< im.getHeight()-52; i+=3){
            int last1 = -1;
            for (int j =0; j< im.getWidth();j++){
                if (new Color(im.getRGB(j,i)).equals(Color.BLACK) ){
                    last1 = j;

                    //builder.append(first1);
                }

                Color c = new Color(im.getRGB(j,i));


                if(c.getRed() < 50 && c.getBlue() < 50 && c.getGreen() < 50){
                    builder.append(1);
                }else{
                    builder.append(0);
                }


            }
            firsts.add(last1);

            builder.append("\n");
        }






        String message = builder.toString();


        message = message.replace("1110", "-");

        message = message.replace("0100", "•");

        message = message.replace("00000000"," ");

        message = message.replace("0","");
        //System.out.println(message);
        StringBuilder processedMsg = new StringBuilder();
        String[] wordSplitted = message.split("  ");
        for(String s : wordSplitted){
            String[] charSplit = s.split(" ");
            for(String c : charSplit){

                processedMsg.append(coding.get(c));
            }
            processedMsg.append(" ");
        }

        String[] processedMsgSplitted = processedMsg.toString().split("      ");

        StringBuilder newBuilder = new StringBuilder(processedMsgSplitted[0]);

        for(int i = 1; i< processedMsgSplitted.length; i++){
            String[] splitted = processedMsgSplitted[i].split(" ");
            for(String s : splitted) {
                newBuilder.append(hexTocmd
                        String(s));
            }
            newBuilder.append(" ");
        }

        String mess = newBuilder.toString();

        String[] lastSplit = mess.split("\n\n\n");



        byte[] bytes = lastSplit[1].getBytes("UTF-8");
        String encoded = Base64.getEncoder().encodeToString(bytes);


        String output = new String(encoded);


        for(byte val : lastSplit[0].getBytes()){
            System.out.print( String.format("%8s", Integer.toBinaryString(val & 0xFF)).replace(' ', '0'));
            System.out.print( " ");
        }

    }


    public static String hexToString(String hex){

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
        }/*
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("UTF-8")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("ISO-8859-1")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("cp1252")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("US-ASCII")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("Big5")));
        System.out.println(new String(sb.toString().getBytes(), Charset.forName("x-mswin-936")));
*/
        return new String(sb.toString().getBytes());
    }


}
