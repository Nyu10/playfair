import java.util.Scanner;  // Import the Scanner class

public class Main{
	public static void main(String[]args){
		Scanner in =new Scanner(System.in);
		//encode or decode?
		String EorD =args[0];
		//original message
		String message=args[1];
		//add x to message
		String ciphertext=addX(message).toUpperCase();
		//playfair key
		String key = args[2];
		//playfair key in Two-Dimensional Array
		String [][] dataStructure = makeStructure(key);
		String answer="";
		if (EorD.equals("encode")){
			answer = encode(ciphertext,dataStructure);
		}
		else {
			answer = decode(ciphertext,dataStructure);
		}
		System.out.println((EorD+"d Text "+answer).toUpperCase());
    }
	public static String encode(String ciphertext, String [][] dataStructure){
		String answer="";
		for (int i=0;i<ciphertext.length();i+=2){
			String indices = returnIndices(ciphertext.substring(i,i+2),dataStructure);
			//Vertical, Horitzontal, Regular
			String VHorR = analyze(indices);
			if (VHorR.equals("vertical")){
				answer+=verticalEncode(ciphertext.substring(i,i+2),dataStructure);
			}
			else if (VHorR.equals("horizontal")){
				answer+=horizontalEncode(ciphertext.substring(i,i+2),dataStructure);
			}
			else if (VHorR.equals("regular")){
				answer+=regularEncode(ciphertext.substring(i,i+2),dataStructure);
			}
		}
		return answer;
	}
	public static String decode(String ciphertext, String [][]dataStructure){
		String answer="";
		for (int i=0;i<ciphertext.length();i+=2){
			String indices = returnIndices(ciphertext.substring(i,i+2),dataStructure);
			//Vertical, Horitzontal, Regular
			String VHorR = analyze(indices);
			if (VHorR.equals("vertical")){
				answer+=verticalDecode(ciphertext.substring(i,i+2),dataStructure);
			}
			else if (VHorR.equals("horizontal")){
				answer+=horizontalDecode(ciphertext.substring(i,i+2),dataStructure);
			}
			else if (VHorR.equals("regular")){
				answer+=regularEncode(ciphertext.substring(i,i+2),dataStructure);
			}
		}
		return answer;
	}
	//Changes J to I adds X's, add Z
	public static String addX(String message){
		for (int i=0;i<message.length()-1;i+=1){
			if (message.substring(i,i+1).equals("J")){
				message= message.substring(0,i) + "I"+message.substring(i+1);
			}
		}
		for (int i=0;i<message.length()-1;i+=2){
			String pair = message.substring(i,i+2);
			if (pair.substring(0,1).equals(pair.substring(1))){
				message = message.substring(0,i+1) + "X" + message.substring(i+1);
			}
		}
		if (message.length()%2!=0){
			message+="Z";
		}
		return message;
	}
	//makes 5x5 two dimensional array
	public static String[][] makeStructure(String str){
		String [][] keyStructure = new String[5][5];
		int count=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				keyStructure[i][j]=str.substring(count,count+1);
				count++;
			}
		}
		return keyStructure;
	}
	//checks to see if you do horizontal, vertical, or regular encoding
	public static String analyze(String indices){
		String r1,c1,r2,c2;
		r1=indices.substring(0,1);
		c1=indices.substring(1,2);
		r2=indices.substring(2,3);
		c2=indices.substring(3,4);
		if (r1.equals(r2)) return "horizontal";
		else if (c1.equals(c2)) return "vertical";
		else return "regular";
	}
	//returns row,column index for each letter of the letter pair
	public static String returnIndices(String letterPair, String [][] key){
	//r1 = row1 c1=column1
	int r1=0,c1=0,r2=0,c2=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				//row column indices of first letter
				if (key[i][j].equals(letterPair.substring(0,1))){
					r1= i;
					c1=j;
				}
				//row column indices of second letter
				if (key[i][j].equals(letterPair.substring(1,2))){
					r2= i;
					c2=j;
				}
			}
		}
	return "" +r1+c1+r2+c2;
	}
	public static String verticalEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case if its in the last column
		if (indices.substring(1,2).equals("4")){
			indices=indices.substring(0,1)+"0"+indices.substring(2);
		}
		else{//making column index shift one to the right
			indices=indices.substring(0,1)+(Integer.parseInt(indices.substring(1,2))+1+"")+indices.substring(2);
		}
		//same thing but for second letter
		if (indices.substring(3).equals("4")){
			indices=indices.substring(0,3)+"0";
		}
		else{
			indices=indices.substring(0,3)+(Integer.parseInt(indices.substring(3))+1+"");
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
	public static String horizontalEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case if it is last row
		if (indices.substring(0,1).equals("4")){
			indices="0"+indices.substring(1);
		}
		else{//making row index shift one down
			indices=(Integer.parseInt(indices.substring(0,1))+1)+indices.substring(1);
		}
		if (indices.substring(2,3).equals("4")){
			indices=indices.substring(0,2)+"0"+indices.substring(3);
		}
		else{
			indices=indices.substring(0,2)+(Integer.parseInt(indices.substring(2,3))+1)+indices.substring(3);
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
	public static String regularEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		int r1=Integer.parseInt(indices.substring(0,1));
		int c1=Integer.parseInt(indices.substring(1,2));
		int r2=Integer.parseInt(indices.substring(2,3));
		int c2=Integer.parseInt(indices.substring(3));
		newLetterPair+=key[r1][c2];
		newLetterPair+=key[r2][c1];
		return newLetterPair;
	}
	public static String verticalDecode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case if its in the first column
		if (indices.substring(1,2).equals("0")){
			indices=indices.substring(0,1)+"4"+indices.substring(2);
		}
		else{//making column index shift one to the left
			indices=indices.substring(0,1)+(Integer.parseInt(indices.substring(1,2))-1+"")+indices.substring(2);
		}
		//same thing but for second letter
		if (indices.substring(3).equals("0")){
			indices=indices.substring(0,3)+"4";
		}
		else{
			indices=indices.substring(0,3)+(Integer.parseInt(indices.substring(3))-1+"");
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
	public static String horizontalDecode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case if it is first row
		if (indices.substring(0,1).equals("0")){
			indices="4"+indices.substring(1);
		}
		else{//making row index shift one up
			indices=(Integer.parseInt(indices.substring(0,1))-1)+indices.substring(1);
		}
		if (indices.substring(2,3).equals("0")){
			indices=indices.substring(0,2)+"4"+indices.substring(3);
		}
		else{
			indices=indices.substring(0,2)+(Integer.parseInt(indices.substring(2,3))-1)+indices.substring(3);
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
}
	