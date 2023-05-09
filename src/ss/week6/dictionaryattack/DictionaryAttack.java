package ss.week6.dictionaryattack;

import org.apache.commons.codec.binary.Hex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class DictionaryAttack {
    private final Map<String, String> passwordMap;
    private final Map<String, String> hashDictionary;

    public DictionaryAttack() {
        passwordMap = new HashMap<>();
        hashDictionary = new HashMap<>();
    }

    /**
     * Reads a password file. Each line of the password file has the form:
     * username: encodedpassword
     * <p>
     * After calling this method, the passwordMap class variable should be
     * filled with the content of the file. The key for the map should be
     * the username, and the password hash should be the content.
     *
     * @param filename file name
     */
    public void readPasswords(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] args = line.split(": ");
                passwordMap.put(args[0], args[1]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Given a password, return the MD5 hash of a password. The resulting
     * hash (or sometimes called digest) should be hex-encoded in a String.
     *
     * @param password password value
     * @return password hash
     */
    public String getPasswordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return String.valueOf(Hex.encodeHex(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks the password for the user the password list. If the user
     * does not exist, returns false.
     *
     * @param user     username
     * @param password user password
     * @return whether the password for that user was correct.
     */
    public boolean checkPassword(String user, String password) {
        if (passwordMap.containsKey(user)) {
            return passwordMap.get(user).equals(getPasswordHash(password));
        }
        return false;
    }

    /**
     * Reads a dictionary from file (one line per word) and use it to add
     * entries to a dictionary that maps password hashes (hex-encoded) to
     * the original password.
     *
     * @param filename filename of the dictionary.
     */
    public void addToHashDictionary(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            hashDictionary.put(line, getPasswordHash(line));
        }
        br.close();
    }

    /**
     * Do the dictionary attack.
     */
    public void doDictionaryAttack() {
        passwordMap.forEach((username, h1) -> hashDictionary.forEach((password, h2) -> {
            if (h1.equals(h2)) {
                System.out.printf("%s: %s%n", username, password);
            }
        }));
    }

    private String shiftStringValue(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 'z') {
                chars[i] = (char) (chars[i] + 1);
                break;
            }
            chars[i] = 'a';
        }
        return String.valueOf(chars);
    }

    public void bruteForcePasswordHash(String hash, int limit) {
        outerloop:
        for (int i = 0; i < limit; i++) {
            String candidate = "a".repeat(i + 1);
            while (candidate.charAt(i) != 'z') {
                if (getPasswordHash(candidate).equals(hash)) {
                    System.out.printf("%s: %s%n", candidate, hash);
                    break outerloop;
                }
                candidate = shiftStringValue(candidate);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryAttack da = new DictionaryAttack();

        String hash = "c0af77cf8294ff93a5cdb2963ca9f038";
        long beforeExec = System.currentTimeMillis();
        da.bruteForcePasswordHash(hash, 6);
        long afterExec = System.currentTimeMillis();
        System.out.printf("%d milliseconds", afterExec - beforeExec);

//        da.readPasswords("src/ss/week6/test/LeakedPasswords.txt");
//        da.addToHashDictionary("src/ss/week6/dictionaryattack/CommonPasswords.txt");
//        da.doDictionaryAttack();
    }
}
