package com.udiansoft.util;

/**
 * <p>Title: Java Application Develope Framework</p>
 * <p>Description: Java��������� ���</p>
 * <p>Copyright: Copyright (c) ��E����������� 2002-2008</p>
 * <p>Company: ��E�� 21CNEC.COM.CN</p>
 * @author not attributable
 * @version 1.0
 */

public class Base64 {

  private Base64() {}

  static boolean compare(byte abyte0[], byte abyte1[]) {
    if (abyte0 == null || abyte1 == null)
      return false;
    if (abyte0.length != abyte1.length)
      return false;
    for (int i = 0; i < abyte0.length; i++)
      if (abyte0[i] != abyte1[i])
        return false;
    return true;
  }

  /** Encodes a string in base 64.
   * @param <code>s</code>: the string to be encoded
   * @return null if s is null otherwise the encoded string
   */
  public static final String encode(String s) {
    if (s == null || s.length() == 0) {
      return null;
    }
    else {
      byte abyte0[] = s.getBytes();
      return new String(encode(abyte0));
    }
  }

  /** Encodes a byte array in base 64.
   * @param <code>b</code>: the byte array to be encoded
   * @return null if b is null otherwise the encoded byte array
   */

  public static final byte[] encode(byte b[]) {
    return encode(b, true);
  }

  static final byte[] encode(byte abyte0[], boolean flag) {
    if (abyte0 == null)
      return null;
    int i = 0;
    int j = 0;
    int k = abyte0.length;
    int l = 0;
    int i1 = 0;
    int j1 = ( (k + 2) / 3) * 4;

    byte abyte1[] = new byte[j1];

    while (j < k) {
      switch (i1) {
        case 0: //'\0'
          abyte1[l++] = (byte) enc[abyte0[j] >> 2 & 0x3f];
          break;

        case 1: //'\001'
          if (j + 1 >= k) {
            abyte1[l++] = (byte) enc[abyte0[j] << 4 & 0x30];
            abyte1[l++] = 61;
            abyte1[l++] = 61;
          }
          else {
            abyte1[l++] = (byte) enc[abyte0[j] << 4 & 0x30 |
                abyte0[j + 1] >> 4 & 0xf];
          }
          j++;
          break;

        case 2: //'\002'
          if (j + 1 >= k) {
            abyte1[l++] = (byte) enc[abyte0[j] << 2 & 0x3c];
            abyte1[l++] = 61;
          }
          else {
            abyte1[l++] = (byte) enc[abyte0[j] << 2 & 0x3c |
                abyte0[j + 1] >> 6 & 0x3];
          }
          j++;
          break;

        case 3: //'\003'
          abyte1[l++] = (byte) enc[abyte0[j] & 0x3f];
          j++;
          break;
      }
      if (++i1 == 4)
        i1 = 0;
      if (++i == 76 && flag) {
        i = 0;
        abyte1[l++] = 13;
        abyte1[l++] = 10;
      }
    }
    /*while*/
    return abyte1;
  }

  /** Decodes a string in base 64.
   * @param <code>s</code>: the string to be decoded
   * @return null if s is null otherwise the decoded string
   */

  public static final String decode(String s) {

    if (s == null || s.length() == 0) {
      return null;
    }
    else {
      byte abyte0[] = s.getBytes();
      return new String(decode(abyte0));
    }
  }

  /** Decodes a byte array in base 64.
   * @param <code>abyte0</code>: the byte array to be decoded
   * @return null if b is null otherwise the decoded byte array
   */

  public static final byte[] decode(byte abyte0[]) {
    if (abyte0 == null || abyte0.length == 0)
      return null;
    int i = (int) ( (double) abyte0.length * 0.75);
    int j = 0;
    byte abyte1[] = new byte[i];
    int k = abyte0.length;
    int l = 0;
    int i1 = 0;

    while (l < k) {
      int j1;
      while ( (j1 = dec[abyte0[l++]]) == -1 && l < k);
      if (l >= k)
        break;
      int k1;
      while ( (k1 = dec[abyte0[l]]) == -1)
        if (++l >= k) {
          k1 = 99;
          break;
        }
      if (j1 == 99 || k1 == 99)
        break;
      switch (i1) {
        case 0: //'\0'
          abyte1[j++] = (byte) (j1 << 2 | k1 >> 4);
          break;

        case 1: //'\001'
          abyte1[j++] = (byte) (j1 << 4 | k1 >> 2);
          break;

        case 2: //'\002'
          abyte1[j++] = (byte) (j1 << 6 | k1);
          l++;
          break;
      }

      if (++i1 == 3)
        i1 = 0;
    }

    if (j < i) {
      byte abyte2[] = new byte[j];
      System.arraycopy(abyte1, 0, abyte2, 0, j);
      return abyte2;
    }
    else {
      return abyte1;
    }
  }

  static final int enc[] = {
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84,
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100,
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55,
      56, 57, 43, 47
  };

  static final int x = -1;

  static final int dec[] = {
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, 62, -1, -1, -1, 63, 52, 53,
      54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
      -1, 99, -1, -1, -1, 0, 1, 2, 3, 4,
      5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
      15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
      25, -1, -1, -1, -1, -1, -1, 26, 27, 28,
      29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
      39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
      49, 50, 51, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1
  };

  /** A main method to test the class out. Precondtion: args must have atleast one argument - the 	         * string to be encoded.
   * @param <code>args</code>: the command line arguments to be encoded / decoded
   */

  public static void main(String args[]) {
    try {
      if (args.length == 1)
        System.out.println(args[1] + " decoded in base 64 is " +
                           Base64.decode(args[0]));
      else if (args.length == 2) {
        System.out.println("\"" + args[0] + "\" encoded in base 64 is \"" +
                           Base64.encode(args[0]) + "\"");
        System.out.println("\"" + args[1] + "\" decoded in base 64 is \"" +
                           Base64.decode(Base64.encode(args[0])) + "\"");
      }
    }
    catch (ArrayIndexOutOfBoundsException a) {
      System.out.println("Usage: Base64 stringToBeENCODED [stringToBeDECODED]");
    }

  }

}
