package temka.md5algo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Md5 {
        
        private Integer     from;
        private Integer     to;
        ArrayList<String>   words;
        private  Map<String, String> HVMap   = new HashMap<String, String>();
        
        public Md5(Integer from_val, Integer to_val, ArrayList<String> words) {
            this.from   = from_val;
            this.to     = to_val;
            this.words  = words;
        }

    public void fillNumHashes() {
        
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
        
            while (this.from <= this.to) {
                md5.update(StandardCharsets.UTF_8.encode(this.from.toString()));
                HVMap.put(String.format("%32x", new BigInteger(1, md5.digest())), this.from.toString());
                this.from++;
            }
        } catch (Exception exception) {
            System.out.println("Some error unexpectedly occured!!");
        }
    }

    public void fillAlphaHashes() {
        ArrayList<String> allStrings = new ArrayList<String>();

        allStrings = formAlphaCombs();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            for (String comb : allStrings) {
                md5.update(StandardCharsets.UTF_8.encode(comb));
                HVMap.put(String.format("%32x", new BigInteger(1, md5.digest())), comb);
            }
        } catch (Exception exception) {
            System.out.println("Some error unexpectedly occured!!");
        }
    }

    public ArrayList<String> formAlphaCombs() {
        ArrayList<String> combList = new ArrayList<String>();

        for (Integer cnt = 0; cnt < this.words.size(); cnt++) {
            String word = this.words.get(cnt);
            int combs = 1 << word.length();
            char[][] ch_arr = { word.toLowerCase().toCharArray(), word.toUpperCase().toCharArray()};
            char[] result = new char[word.length()]; 
            for (int i = 0; i < combs; i++) {
                for (int j = 0; j < word.length(); j++) {
                    result[j] = ch_arr[(i >> j) & 1][j];
                }
                combList.add(new String(result));
            }
        }
//        for(int i=0;i<combList.size();i++){
//            System.out.println(combList.get(i));
//        }
        return (combList);
    }

    public void printHashes() {
        for (Map.Entry<String, String> entry : HVMap.entrySet())
        {
            System.out.println("<Hash>: " + entry.getKey() + "<------->" + entry.getValue() + " | ");
        }
        System.out.println("Map size: " + HVMap.size());
    }

    public Map<String, String> getHVMap() {
        return (this.HVMap);
    }
}