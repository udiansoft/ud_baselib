package com.udiansoft.util;

/**
 * <p>Title: �׵ù������ϵͳ 3.0</p>
 * <p>Description: ����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2003��2008</p>
 * <p>Company: ������Ϣ�Ƽ������ڣ����޹�˾</p>
 * @author ���̻�
 * @version 1.0
 */

import java.text.NumberFormat;
import java.math.BigDecimal;
import java.util.Locale;
public class NumberTool {
  public NumberTool() {
  }
  //�������� _digitΪС��λ��
  public static double round(String _double,int _digit)
  {
    BigDecimal big = new BigDecimal(_double);
    BigDecimal one = new BigDecimal("1");
    return big.divide(one, _digit, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  //�������� _digitΪС��λ��
  public static double round(double _double,int _digit)
  {
    String f = Double.toString(_double);
    return round(f,_digit);
  }
  //�������� С��λ��Ϊ 2
  public static double round(double _double)
  {
    return round(_double,2);
  }
  //�������� С��λ��Ϊ 3
  public static double round3(double _double)
  {
    return round(_double,3);
  }
  //��������Ϊ����
  public static int roundInt(double _double)
  {
    String f = Double.toString(_double);
    BigDecimal big = new BigDecimal(f);
    BigDecimal one = new BigDecimal("1");
    return big.divide(one, 0, BigDecimal.ROUND_HALF_UP).intValue();
  }
  //�õ���ŵ���������ַ� С��λ��Ϊ _digit
  public static String strPot(double _double,int _digit)
  {
    BigDecimal big = getBigDecimal(_double,_digit);
    NumberFormat format = NumberFormat.getInstance();
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� ����С��λ��
  public static String strPot(double _double)
  {
    BigDecimal big = getBigDecimal(_double);
    NumberFormat format = NumberFormat.getInstance();
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� С��λ��Ϊ _digit ����Ԫ��� $
  public static String strPotUS(double _double,int _digit)
  {
    BigDecimal big = getBigDecimal(_double,_digit);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� ����С��λ�� ����Ԫ��� $
  public static String strPotUS(double _double)
  {
    BigDecimal big = getBigDecimal(_double);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� С��λ��Ϊ _digit ����Ԫ��� $
  public static String strPotHK(double _double,int _digit)
  {
    BigDecimal big = getBigDecimal(_double,_digit);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� ����С��λ�� ��HKԪ��� $
  public static String strPotHK(double _double)
  {
    BigDecimal big = getBigDecimal(_double);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� С��λ��Ϊ _digit ������ҷ��
  public static String strPotCN(double _double,int _digit)
  {
    BigDecimal big = getBigDecimal(_double,_digit);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
    return format.format(big.doubleValue());
  }
  //�õ���ŵ���������ַ� ����С��λ�� ������ҷ��
  public static String strPotCN(double _double)
  {
    BigDecimal big = getBigDecimal(_double);
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
    return format.format(big.doubleValue());
  }


  public static BigDecimal getBigDecimal(double _double)
  {
    String f = Double.toString(_double);
    BigDecimal big = new BigDecimal(f);
    return big;
  }
  public static BigDecimal getBigDecimal(double _double,int _digit)
  {
    String f = Double.toString(_double);
    BigDecimal big = new BigDecimal(f);
    BigDecimal one = new BigDecimal("1");
    return big.divide(one, _digit, BigDecimal.ROUND_HALF_UP);
  }
  public static String double2Str(double _double,int _digit)
  {
    String f = Double.toString(_double);
    BigDecimal big = new BigDecimal(f);
    BigDecimal one = new BigDecimal("1");
    return big.divide(one, _digit, BigDecimal.ROUND_HALF_UP).toString();
  }
  public static void main(String[] argv)
  {
    System.out.println(getBigDecimal(123.123458556,5).doubleValue());
  }

}
