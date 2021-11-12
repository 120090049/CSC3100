import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class tryy 
{
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
    static HashMap<String,Integer> frequencyMap= new HashMap<>();  

    public static Set<Tree> huffmanTreeSet = new HashSet<>();
    
    public static String getMinKeyInMap() throws MyException
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
            if ((Integer)entry.getValue()<min) key = (String)entry.getKey();
        }
        System.out.println("getMinKeyInMap: "+ key);
        return key;
    }
    public static void main(String[] args) throws Exception 
    {
        // huffmanTreeSet.add(new Tree("A", "B", "A+B"));
        // huffmanTreeSet.add(new Tree("C", "D", "C+D"));
        // huffmanTreeSet.add(new Tree("E", "F", "E+F"));
        // huffmanTreeSet.add(new Tree("G", "H", "G+H"));
        // for (Tree clp : huffmanTreeSet){
        //     if (clp.nodeName()=="E+F")
        //     {
        //         for (Tree clp1 : huffmanTreeSet)
        //         {
        //             if (clp1.nodeName()=="A+B")
        //             {
        //                 System.out.println(clp1.nodeName());
        //                 huffmanTreeSet.add(new Tree(clp, clp1, clp.nodeName()+"+"+clp1.nodeName()));

        //                 huffmanTreeSet.remove(clp1);
        //             }
        //             break;
        //         }
        //         System.out.println(clp.nodeName());
        //         huffmanTreeSet.remove(clp);
        //         break;
        //     }
        // }
        // for (Tree clp : huffmanTreeSet){
        //     System.out.println("haha"+clp.nodeName());
        // }

        // String inputText = "ccLpppp";
        // // frequency.put("clp", 1);
        // // frequency.put("clp", frequency.get("clp")+1);
        // for (int j = 0; j < inputText.length(); j++){
        //     String letter = String.valueOf(inputText.charAt(j));
        //     if (!frequencyMap.containsKey(letter)){
        //         frequencyMap.put(letter, 1);
        //     }else{
        //         frequencyMap.put(letter, frequencyMap.get(letter)+1);
        //     } 
        // }
        // // Collection values = frequency.values();
        // Iterator iterator = frequencyMap.entrySet().iterator();
        // Integer min = -1;
        // String key = "";
        // while(iterator.hasNext()){
        //     HashMap.Entry entry = (HashMap.Entry)iterator.next();
        //     System.out.println(entry.getValue());
        //     if (min==-1) {
        //         min = (Integer)entry.getValue();
        //         key = (String)entry.getKey();
        //         continue;
        //     }
        //     if ((Integer)entry.getValue()<min) key = (String)entry.getKey();
        // }
        // String clp = "a";
        // System.out.println((int)clp.charAt(0));
        // List<String> list = new ArrayList<>();
        // HashMap<String,String> map = new HashMap<>();

		// BufferedReader br = Files.newBufferedReader(Paths.get("dictionaryTry.txt"));
 		// list = br.lines().collect(Collectors.toList());
        // for (String i : list)
        // {
        //     String[] is=i.split(":");
        //     int code = Integer.parseInt(is[0]);
        //     // System.out.println(code);
        //     String letter =String.valueOf((char) code);
        //     map.put(is[1], letter);
        // }
        // // System.out.println(map);
		// // list.forEach(System.out::println);
            // TODO Auto-generated method stub
            String likeType = "fjas";
            String sourceStr = "adfjaslfj23ldfalsf";
            System.out.println(sourceStr.indexOf(likeType,3)); 
            // System.out.println(sourceStr(1)); 

    }
}
