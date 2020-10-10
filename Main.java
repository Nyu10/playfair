public class Main{
    public static void main(String[]args){
	String Encode = "WHITEHAT PLAYFIREXMBCDGHKNOQSTUVWZ";
	String message =Encode.substring(0,Encode.indexOf(' '));
	System.out.println(addX("abooksoop"));
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
}
