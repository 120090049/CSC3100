import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class HuffmanDecompression {

    public static void main(String[] args) throws Exception {
        // args[0] is the file of compressed text
        // args[1] is the dictionary file
        // args[2] is the file of decompressed text to output
        //
        // For example: if your compressed text is:
        // 11011111111011110011010111011001100110001101100111101011111001110
        //
        // , and your dictionary file is:
        // 10:01110
        // 32:00
        // 65:010
        // 69:1111
        // 73:110
        // 83:11
        // 84:0110
        // 85:01111
        // 89:1110
        //
        // Then your expected output would be:
        // SUSIE SAYS IT IS EASY\n
        // where '\n' denotes linefeed character.
        // Note: 10 is the ASCII code of '\n', 32 is ' ', 65 is 'A', ...

        
        //1 get input
        // String inputText = new String("input.txt");
        // String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[0])), "US-ASCII");
        String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("compressedTry.txt")), "US-ASCII");
        //2 create a map
        List<String> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
		BufferedReader br = Files.newBufferedReader(Paths.get("dictionaryTry.txt"));
 		
        list = br.lines().collect(Collectors.toList());
        for (String i : list)
        {
            String[] is=i.split(":");
            int code = Integer.parseInt(is[0]);
            // System.out.println(code);
            String letter =String.valueOf((char) code);
            map.put(is[1], letter);
        }
        // System.out.println(map);

       
        // System.out.println("getMinKeyInMap: "+ key);
    

        //3 decompress
        String decompressedCode = "";
        int index = 0;

        while (index < inputText.length())
        {
            Iterator iterator = map.entrySet().iterator();
     
            while(iterator.hasNext()){
                HashMap.Entry entry = (HashMap.Entry)iterator.next();
                String code = (String)entry.getKey();
                if (inputText.indexOf(code,index)==index)
                {
                    index += code.length();
                    decompressedCode += (String)entry.getValue();
                }
            }
        }
        System.out.println(decompressedCode);
        FileWriter fwriter = new FileWriter("outputTry.txt", false);
        // FileWriter fwriter2 = new FileWriter(args[2], false);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        bwriter.write(decompressedCode);
        bwriter.flush();
        bwriter.close();
        return;
    }
}
