public class Main{
    public static void main(String[]args){
	String Input = "encode WHITEHAT PLAYFIREXMBCDGHKNOQSTUVWZ";
	//encode or decode?
	String EorD =Input.substring(0,6);
	//original message
	String message=Input.substring(7,Input.substring(7).indexOf(" ")+7);
	//add x to message
	String ciphertext=addX(message);
	//playfair key
	String key = Input.substring(EorD.length()+2+message.length());
	//playfair key in Two-Dimensional Array
	String [][] dataStructure = makeStructure(key);
	if (EorD.equals("encode")){
		String answer = encode(ciphertext,datastructure);
	}
	else {
		String answer = decode(ciphertext,dataStructure);
	}
	System.out.println(EorD+"d Text"+answer);
    }
	public String encode(String ciphertext, String [][] datastructure){
		for (int i=0;i<ciphertext.length();i+=2){
			String indices = returnIndices(ciphertext.substring(i,i+2);
		}
	}
	public static String addX(String message){
		for (int i=0;i<message.length()-1;i+=2){
			String pair = message.substring(i,i+2);
			if (pair.substring(0,1).equals(pair.substring(1))){
				message = message.substring(0,i+1) + "x" + message.substring(i+1);
			}
		}
		if (message.length()%2!=0){
			message+="x";
		}
		return message;
	}
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
	public String analyis(String indices){
		String r1,c1,r2,c2;
		r1=indices.substring(0,1);
		c1=indices.substring(1,2);
		r2=indices.substring(2,3);
		c2=indices.substring(3,4);
		if (r1==r2) return "horizontal";
		else if (c1==c2) return "vertical";
		else return "regular";
	}
	public String returnIndices(String letterPair, String [][] key){
	//r1 c1 = row column indices for first letterPair
	int r1=0,c1=0,r2=0,c2=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				if (key[i][j].equals(letterPair.substring(0,1))){
					r1= i;
					c1=j;
				}
				if (key[i][j].equals(letterPair.substring(1,2))){
					r2= i;
					c2=j;
				}
			}
		}
	return "" +r1+c1+r2+c2;
	}
	public String verticalEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case
		if (indices.substring(1,2).equals("4")){
			indices=indices.substring(0,1)+"0"+indices.substring(2);
		}
		else{//making column index shift one
			indices=indices.substring(0,1)+(Integer.parseInt(indices.substring(1,2))+1+"")+indices.substring(2);
		}
		if (indices.substring(4).equals("4")){
			indices=indices.substring(0,4)+"0";
		}
		else{
			indices=indices.substring(0,4)+(Integer.parseInt(indices.substring(4))+1+"");
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
	public String horizontalEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		//checking for edge case
		if (indices.substring(1,2).equals("4")){
			indices=indices.substring(0,1)+"0"+indices.substring(2);
		}
		else{//making column index shift one
			indices=indices.substring(0,1)+(Integer.parseInt(indices.substring(1,2))+1)+indices.substring(2);
		}
		if (indices.substring(3).equals("4")){
			indices=indices.substring(0,3)+"0";
		}
		else{
			indices=indices.substring(0,3)+(Integer.parseInt(indices.substring(3))+1);
		}
		newLetterPair+=key[Integer.parseInt(indices.substring(0,1))][Integer.parseInt(indices.substring(1,2))];
		newLetterPair+=key[Integer.parseInt(indices.substring(2,3))][Integer.parseInt(indices.substring(3,4))];
		return newLetterPair;
	}
	public String regularEncode(String letterPair,String [][]key){
		String indices= returnIndices(letterPair,key);
		String newLetterPair = "";
		int r1=Integer.parseInt(indices.substring(0,1));
		int c1=Integer.parseInt(indices.substring(1,2));
		int r2=Integer.parseInt(indices.substring(2,3));
		int c2=Integer.parseInt(indices.substring(3));
		int temp=c1;
		c1=c2;
		c2=temp;
		newLetterPair+=key[r1][c1];
		newLetterPair+=key[r2][c2];
		return newLetterPair;
	}
}
	