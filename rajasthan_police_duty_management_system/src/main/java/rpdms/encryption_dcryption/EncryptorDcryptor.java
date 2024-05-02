package rpdms.encryption_dcryption;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptorDcryptor {
	public static  String encodePassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verifyPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
}
