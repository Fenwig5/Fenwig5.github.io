
package blockcipherdecrypt;

//@author timothy c stockton ii

public class BlockCipherDecrypt {
    //***********************************************************************
    //Student/Author: Timothy C Stockton II
    //Wei Lu CS455 Cryptography and Network Security 2022	
    //Midterm Project Programming Question 4 - BlockCipherDecrypt.java
    //Decodes a string of hex characters to ascii format using a block cipher.
    //Program displays the final decrypted ascii message.
    //***********************************************************************

    public static void main(String[] args) {
        String message = ""; //original message
        String key = "10001001"; //initial vector
        String nextKey = "";	//key used for next block
        String messageBin = ""; //original message in binary
        String encryptedBin = ""; //encrypted binary message
        String encryptedHex = "c195c191be8fa190"; //encrypted hex message

        System.out.println("***************************************************************************************\n"
                + "Student/Author: Timothy C Stockton II\n"
                + "Wei Lu CS455 Cryptography and Network Security 2022\n"
                + "Midterm Project Programming Question 4 - BlockCipherDecrypt.java\n"
                + "Decodes a string of hex characters to ascii format using a block cipher.\n"
                + "Program displays the final decrypted ascii message.\n"
                + "***************************************************************************************\n");

        System.out.println("Decrypting \"" + encryptedHex + "\" using initial vector \"" + key + "\".\n");

        //convert encrypted hex string to binary
        //---------------------------------------------------------------------------
        //use custom hex to binary function
        encryptedBin = hexToBin(encryptedHex);
        System.out.println("Encrypted binary:\n" + encryptedBin + "\n");
        //--------------------------------------------------------------------------------
        //XOR in 8 bit blocks beginning with initial vector
        //current index encrypted 8 bit block becomes key for next block
        //-------------------------------------------------------
        //create a string array to make handling encrypted blocks easier
        String[] encryptedBinBlocks = new String[encryptedHex.length() / 2];
        System.out.print("Encrypted binary blocks: \n");
        //for each character (every 8 bits/block)
        for(int i = 0; i < encryptedHex.length() / 2; i++) {
            //take that characters binary digits and assign it to the respective block in the array
            encryptedBinBlocks[i] = encryptedBin.substring((8 * i), (8 * (i + 1)));
            System.out.print(encryptedBinBlocks[i] + " ");
        }
        System.out.print("\n\nPlaintext binary blocks: \n");
        //create a string array to make handling plaintext blocks easier
        String[] messageBinBlocks = new String[encryptedHex.length() / 2];
        //assign initial key as nextKey which will be replaced after each block
        nextKey = key;
        String temp = "";
        //for each block of binary (each character)
        for(int i = 0; i < encryptedHex.length() / 2; i++) {
            //for each binary digit in the current block
            for(int j = 0; j < 8; j++) {
                //exclusive or (XOR) encrypted bit with key bit and append to temp
                temp += encryptedBinBlocks[i].charAt(j) ^ nextKey.charAt(j);
            }
            //append temp (current block, plaintext) to messageBin
            messageBin += temp;
            //assign temp to respective index of plaintext binary block array
            messageBinBlocks[i] = temp;
            //if not the last binary block/character, assign current index encrypted block as the next key
            if(i != (encryptedHex.length() / 2) - 1) {nextKey = encryptedBinBlocks[i];}
            //clear temp
            temp = "";
            System.out.print(messageBinBlocks[i] + " ");
        }
        //end xor
        //-----------------------------------------------------------------------
        //convert plaintext binary string to original message characters
        //---------------------------------------------------------------------------
        //for each plaintext block (character, 8 bits)
        for(int i = 0; i < encryptedHex.length() / 2; i++) {
            //convert binary blocks to characters and append to plaintext message string
            message += (char)Integer.parseInt(messageBinBlocks[i], 2);
        }
        //end convert to characters
        System.out.println("\n\nPlaintext message: \n" + message);
        //--------------------------------------------------------------------------------
    }//end main()
    
    private static String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("a", "1010");
        hex = hex.replaceAll("b", "1011");
        hex = hex.replaceAll("c", "1100");
        hex = hex.replaceAll("d", "1101");
        hex = hex.replaceAll("e", "1110");
        hex = hex.replaceAll("f", "1111");
        return hex;
    }
    
}//end BlockCipher.java
