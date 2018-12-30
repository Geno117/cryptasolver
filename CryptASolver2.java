
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
import java.text.DecimalFormat;

public class CryptASolver2{
	public static void main(String args[]){
		int validflag=0;
		//Find operands
		if (args.length==0){
			System.out.println("No arguments listed");
			System.exit(0);
		}

		//Find words
		int numwords = ((args.length-1)/2);														//numwords changed
		String wordls[] = new String[numwords];
		int cnt=0;
		for (int i = 0;i<args.length-1;i++){
			if (i%2!=1){
				wordls[cnt]=args[i];
				cnt+=1;
			}
		}
		System.out.println("number of words: " + numwords);
		System.out.println("words: " + Arrays.toString(wordls));
		//Find resultant word
		String resultword = args[args.length-1]; //Don't use equalindex
		System.out.println("Resulting word: "+resultword);
		//Find all letters involved (make not of letters used only on op side)
		ArrayList<String> letterls = new ArrayList<String>();
		for (int y = 0; y< wordls.length;y++){
			for (int x = 0; x< wordls[y].length();x++){
				if(letterls.contains(String.valueOf(wordls[y].charAt(x)))==false){
					letterls.add(String.valueOf(wordls[y].charAt(x)));
				}
			}
		}
		System.out.println("Letters used by operands: "+Arrays.toString(letterls.toArray()));
		for (int x=0; x<resultword.length();x++){
			if(letterls.contains(String.valueOf(resultword.charAt(x)))==false){
				letterls.add(String.valueOf(resultword.charAt(x)));
			}
		}
		System.out.println("All letters used: "+Arrays.toString(letterls.toArray()));
		/*// for-each loop
        for (String s : collection) {
        System.out.println("value= " + s);
        }*/
        double limit = Math.pow(10,letterls.size());
		int noletters = letterls.size();
		//replace operand letters with values
		System.out.println("Number of combinations: "+String.valueOf(limit));
		
		System.out.println("Number of letters: "+String.valueOf(noletters));
		String fmt="%0"+String.valueOf(noletters+1)+".0f";
		String limstring = String.format(fmt,limit);
		//compare with resultant to see ifit works
		//iterate
		//display solution
		int[] zero = {0,0,0};
		int[] result =check(numwords,wordls,resultword,letterls,noletters,1.0E0,limit);
		for (int i =0; i<result.length;i++){
			System.out.println(String.valueOf(result[i]+" "));
		}
		System.out.println("THIS IS IT. GOING IN DEEP.\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		int[] resultls = check(numwords,wordls,resultword,letterls, noletters, 1, limit);
		if (Arrays.equals(zero,resultls)){
			System.out.println("invalid");
		} else {
			for(int i: resultls){
				System.out.println(i+", ");
			}
		}
	}
	public static int[] check(int numwords,String[] wordls,String resultword,ArrayList letterls,int noletters,double id,double limit) {
		System.out.println("id: "+String.valueOf(id) + "  limit: "+String.valueOf(limit)+ "   id compared to limit: "+Double.compare(limit,id));
		if (Double.compare(limit,id)==0){
			int[] zero = {0,0,0};
			return zero;
		}
		int[] mp = new int[noletters];
		String fmt="%0"+String.valueOf(noletters)+".0f";
		//System.out.println(fmt);
		String idstring=String.format(fmt,id);
		System.out.println(idstring);
		System.out.println(noletters);

		for(int i=0;i<noletters;i++){		//i is first letter
			mp[i]=Character.getNumericValue(idstring.charAt(noletters-i-1));
			
		}
		for(int i:mp){
			System.out.println(i+", ");

		}
		int[] numls = new int[numwords+1];
		for (int x=0; x<numwords;x++){
			String convnum="";
			for (int y=0; y<wordls[x].length();y++){
				convnum = convnum + Integer.toString(mp[letterls.indexOf(Character.toString(wordls[x].charAt(y)))]);
				//System.out.println(letterls.indexOf(Character.toString(wordls[x].charAt(y))));

			}
			numls[x]=Integer.valueOf(convnum);
		}
		for (int i=0; i<numwords;i++){
			System.out.print("|"+wordls[i]+"=="+String.valueOf(numls[i])+"|");
		}

		String resultnum="";
		for(int i=0; i< resultword.length();i++){
			resultnum = resultnum + Integer.toString(mp[letterls.indexOf(Character.toString(resultword.charAt(i)))]);
		}
		System.out.println(resultword + ": "+ resultnum);
		numls[numwords]= Integer.valueOf(resultnum);
		Integer sum=0;
		for(int p=0;p<(numwords+1);p++){
			sum+=numls[p];
		}
		System.out.println("result: " + String.valueOf(sum));
		if(sum.compareTo(numls[numwords])==0){
			return mp;
		} 
		return check(numwords,wordls,resultword,letterls,noletters,id+1,limit); 
		//int[] one = {1,1,1};
		//return one;
	} //eof function
}
//[2018-01-08] Challenge #346 [Easy] Cryptarithmetic Solver
//iterating through a string
//using arraylist
/*ArrayList
.size()
.isEmpty()
.indexOf()
Arrays
.length

Integer.toString(int)
	*/