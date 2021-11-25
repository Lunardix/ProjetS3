import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 57lun
 */
public class Hash {
    
  
  
    
  public String randomString(int taille) {

    String alphabetMajuscule = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetMinuscule = "abcdefghijklmnopqrstuvwxyz";
    String nombres = "0123456789";

    
    String alphaNumerique = alphabetMajuscule + alphabetMinuscule + nombres;

    StringBuilder sb = new StringBuilder();

    Random random = new Random();

    for(int i = 0; i < taille; i++) {

      int index = random.nextInt(alphaNumerique.length());

      char randomChar = alphaNumerique.charAt(index);

      sb.append(randomChar);
    }

    return(sb.toString());
  }
  
  public static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
}
  
  public String hash(String mdp, String randomString){
    MessageDigest digest = null;
    try {
    digest = MessageDigest.getInstance("SHA-256");
    }catch(NoSuchAlgorithmException e){
        System.out.println("Hash Error");
    }
    byte[] encodedHash = digest.digest(mdp.getBytes(StandardCharsets.UTF_8));
    return(bytesToHex(encodedHash));
  }
}
