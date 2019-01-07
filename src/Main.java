import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static String template="<html>\n" +
            "<head>\n" +
            "<title>PNG Codes</title>\n" +
            "<style>\n" +
            ".button {\n" +
            "    margin: 10px;\n" +
            "    font-family: \"Segoe UI\", Gadget, sans-serif;\n" +
            "    font-size: 20px;\n" +
            "    padding: 15px;\n" +
            "    text-align: center;\n" +
            "    transition: 0.5s;\n" +
            "    background-size: 200% auto;\n" +
            "    color: #FFF;\n" +
            "    box-shadow: 0 0 20px #eee;\n" +
            "    border-radius: 10px;\n" +
            "    width: 200px;\n" +
            "    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);\n" +
            "    transition: all 0.3s cubic-bezier(.25,.8,.25,1);\n" +
            "    cursor: pointer;\n" +
            "    display: inline-block;\n" +
            "    text-decoration: none;\n" +
            "    border-radius: 25px;\n" +
            "    background-image: linear-gradient(to right, #38ace3 0%, #5cd6d6 51%, #00ffaa 100%)   \n" +
            "}\n" +
            ".button:hover { background-position: right center; \n" +
            "    box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);\n" +
            "    margin: 8px 10px 12px;}\n" +
            ".info{\n" +
            "    text-align: center;\n" +
            "    font-family: \"Segoe UI Light\", Gadget, sans-serif;\n" +
            "    color: #AAA;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "###button###" +
            "\n" +
            "\n" +
            "        \n" +
            "        <a class=\"info\" href=\"https://PNG.Services\">Powered by PNG Services</a>\n" +
            "\n" +
            "</body></html>";
    public static final String TEMPLATE_BUTTON="<a class=\"button\" href=\"tel:*100*###code###%23\">Code -###offset###</a><br/>\n";

    public static void main(String[] args) throws IOException {

        ArrayList<String> codes=new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader(new File("codes.txt").getAbsolutePath()));

        try{
            StringBuilder text = new StringBuilder();
            String readStringLine = reader.readLine();

            while(readStringLine != null){

                //Trying to save seperate lines of text in an array.
                codes.add(readStringLine.replace("-", "").replace("Ihr Vodafone Code uber 15,00 EUR : ",""));
                readStringLine = reader.readLine();

            }
        }
        finally{
            reader.close();
        }


        String buttons="";
        for (String code:codes) {
            if (code!=null){
            if (code.length()<4) throw new IllegalArgumentException("Jede Zeile muss mindestens 4 Zeichen enthalten!");
            String cmd=TEMPLATE_BUTTON.replace("###code###", code).replace("###offset###", code.substring(code.length()-4));
            buttons+=cmd;
        }}
        template=template.replace("###button###", buttons);
        try (PrintStream out = new PrintStream(new FileOutputStream("output.html"))) {
            out.print(template);
        }

    }
}
