import java.io.*;
import java.util.*;
public class HuffmanCompression {
    public static class Tree{
        String letter;
        Tree left;
        Tree right;
        // public static Tree root;

        
        public Tree(String letter)
        {
            this.letter = letter;
            this.left = null;
            this.right = null;
        }
        public Tree(String key1, String key2, String newkey)
        {
            this.letter = newkey;
            this.left = new Tree(key1);
            this.right = new Tree(key2);
        }
        public Tree(Tree node, String key, String newkey)
        {
            this.letter = newkey;
            this.left = new Tree(key);
            this.right = node;
        }
        public Tree(Tree node1, Tree node2, String newkey)
        {
            this.letter = newkey;
            this.left = node1;
            this.right = node2;
        }
        public String nodeName()
        {
            return this.letter;
        }
    }

    public static Set<Tree> huffmanTreeSet = new HashSet<>();
    public static HashMap<String,Integer> frequencyMap= new HashMap<>();  
    public static HashMap<String,String> letterCodeMap= new HashMap<>();  

    public static String getMinKeyInMap()  throws MyException 
    {
        if (frequencyMap.size()==0){
            throw new MyException("frequencyMap is empty");
        }
        Iterator iterator = frequencyMap.entrySet().iterator();
        Integer min = -1;
        String key = "";
        while(iterator.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)iterator.next();
            // System.out.println(entry.getValue());
            if (min==-1) {
                min = (Integer)entry.getValue();
                key = (String)entry.getKey();
                continue;
            }
            if ((Integer)entry.getValue()<min)
            {
                key = (String)entry.getKey();
                min = (Integer)entry.getValue();
            }
        }
        // System.out.println("getMinKeyInMap: "+ key);
        return key;
    }

    public static void traverseTree(Tree node,String num)
    {
        if (node.left == null && node.right == null)
        {
            letterCodeMap.put(node.nodeName(), num);
        }
        else 
        {
            traverseTree(node.left, num+"0");
            traverseTree(node.right, num+"1");
        }
    }

    public static String getCompressedCode(String inputText, String[] huffmanCodes) {
        String compressedCode = "";
        // Your code obtains the compressed code for inputText based on huffmanCodes
        // For example, if inputText="SUSIE SAYS IT IS EASY\n", and '\n' denotes linefeed.
        // , and huffmanCodes for inputText are: 
        // Space="00", A="010", T="0110", Linefeed="01110", U="01111", S="11",I="110",Y="1110",E="1111".
        // Then, your program will return the String shown in the following line:
        // "11011111111011110011010111011001100110001101100111101011111001110"
        //  S U    S I  E   SpS A  Y   S   I  T     I  S   E   A  S Y   Lf
        for (int i=0; i<inputText.length(); ++i)
        {
            int index = (int) inputText.charAt(i);
            compressedCode += huffmanCodes[index];
        }
       
        return compressedCode;
    }

    public static String[] getHuffmanCode(String inputText) throws MyException
    {
        String[] huffmanCodes = new String[128];
        for (String item : huffmanCodes) item = null;
        // Your code would obtain huffmanCodes for inputText
        // huffmanCodes[i]: huffman code of character with ASCII code i
        // if a character does not appear in inputText, then its huffmanCodes = null
        // For example, if inputText="SUSIE SAYS IT IS EASY\n", and '\n' denotes linefeed.
        // A possible huffmanCodes would be:
        // Space="00",A="010",T="0110",Linefeed="01110",U="01111",S="11",I="110",Y="1110",E="1111".
       
        //{ =4,     A=2,    S=6,  T=1,    E=2,   U=1,    Y=2,   I=3,   \n=1}
        //Answer's output' '="00",A="010",S=11, T=0110, E=1111,U=01111,Y=1110,I=110, Linefeed="01110". length=65
        //my output:     ' '=111, A=0111, S=10, T=0100, E=000, U=0101, Y=001, I=110, \n=0110           length=65
        //create a frequency map
       for (int j = 0; j < inputText.length(); j++){
            String letter = String.valueOf(inputText.charAt(j));
            if (!frequencyMap.containsKey(letter)){
                frequencyMap.put(letter, 1);
            }else{
                frequencyMap.put(letter, frequencyMap.get(letter)+1);
            } 
        }
        // System.out.println(frequencyMap);
        //create a tree
        while (frequencyMap.size()>1)
        {
            String key1 = getMinKeyInMap();
            Integer value1 = frequencyMap.remove(key1);
            // System.out.println("1: "+key1);
            // System.out.println(value1);

            String key2 = getMinKeyInMap();
            Integer value2 = frequencyMap.remove(key2);
            // System.out.println("2: "+key2);
            // System.out.println(value2);
            // System.out.println("######################");

            String newkey = key1 + "+" + key2;
         
            frequencyMap.put(newkey, value1+value2);
            
            boolean find = false;
            boolean find2 = false;
//////////////////////////////////////
            // System.out.println("//////////////////////////////////////////////");

            // for (Tree item : huffmanTreeSet)
            // {
            //     System.out.println(item.nodeName());
            //     System.out.println("----------");
            // }
            // System.out.println("//////////////////////////////////////////////");

            ////////////////////
            for (Tree tree : huffmanTreeSet)
            {
                if (key1 == tree.nodeName())
                {
                    find = true;
                    for (Tree tree2 : huffmanTreeSet)
                    {
                        if (key2 == tree2.nodeName())
                        {
                            find2 = true;
                            huffmanTreeSet.add(new Tree(tree,tree2,newkey));
                            huffmanTreeSet.remove(tree2);
                            break;
                        }
                    }
                    if (find2=true)
                    {
                        huffmanTreeSet.remove(tree);
                        break;
                    }
                    else
                    {
                        huffmanTreeSet.add(new Tree(tree,key2,newkey));
                        huffmanTreeSet.remove(tree);
                        break;
                    }
                }
            }
            if (find == false)
            {
                for (Tree tree : huffmanTreeSet)
                {

                    if (key2 == tree.nodeName())
                    {
                        find2 = true;
                        huffmanTreeSet.add(new Tree(tree,key1,newkey));
                        huffmanTreeSet.remove(tree);
                        break;
                    }
                }
            }
            if (find==false && find2==false)
            {
                huffmanTreeSet.add(new Tree(key1,key2,newkey));
            }
        }

        //traverse the tree and assign numbers
        Tree huffmanTree = huffmanTreeSet.iterator().next();
        traverseTree(huffmanTree,"");
        // System.out.println(letterCodeMap);
   
        Iterator iterator = letterCodeMap.entrySet().iterator();
        while(iterator.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)iterator.next();
            String letter = (String)entry.getKey();
            int index = (int)letter.charAt(0);
            String code = (String) entry.getValue();
            huffmanCodes[index]=code;
        }
        return huffmanCodes;
    }

    public static void main(String[] args) throws Exception {
        try{
             // obtain input text from a text file encoded with ASCII code
        // String inputText = new String("input.txt");
        // String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[0])), "US-ASCII");
        String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("input.txt")), "US-ASCII");
        // System.out.println(inputText);
        // get Huffman codes for each character and write them to a dictionary file
        
        String[] huffmanCodes = HuffmanCompression.getHuffmanCode(inputText);
        
        FileWriter fwriter1 = new FileWriter("dictionaryTry.txt", false);
        // FileWriter fwriter1 = new FileWriter(args[1], false);
        
        BufferedWriter bwriter1 = new BufferedWriter(fwriter1);
        for (int i = 0; i < huffmanCodes.length; i++) 
            if (huffmanCodes[i] != null) {
                bwriter1.write(Integer.toString(i) + ":" + huffmanCodes[i]);
                bwriter1.newLine();
            }
        bwriter1.flush();
        bwriter1.close();
        
        // get compressed code for input text based on huffman codes of each character
        
        String compressedCode = HuffmanCompression.getCompressedCode(inputText, huffmanCodes);
        FileWriter fwriter2 = new FileWriter("compressedTry.txt", false);
        // FileWriter fwriter2 = new FileWriter(args[2], false);
        // System.out.println(args[2]);
        BufferedWriter bwriter2 = new BufferedWriter(fwriter2);
        if (compressedCode != null) 
            bwriter2.write(compressedCode);
        bwriter2.flush();
        bwriter2.close();
        }catch (MyException e){
            System.out.println("frequencyMap is empty");
        }
       
    }
}

class MyException extends Exception
{
    public MyException(){
        super();
    }
    public MyException(String msg){
        super(msg);
    }
}
