//SHA-1 알고리즘

package join.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA_1 {
public static String makeSHA_1(String input) throws NoSuchAlgorithmException{
	MessageDigest md = MessageDigest.getInstance("SHA-1");
	
	md.update(input.getBytes());
	byte[] digest = md.digest();
	
	StringBuffer sb = new StringBuffer();
	for(byte b : digest){
		sb.append(Integer.toHexString(b & 0xff));
	}
	return sb.toString();
}
}
