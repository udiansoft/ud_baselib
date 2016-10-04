package com.udiansoft.util;

import java.awt.Color; 

public class ColorUtil {
	//根据传入的16进制颜色值返回yanse值（BufferedImage使用yanse值画像素点）
	public static int getYanseByHexColor(String hexColor, int def_r, int def_g, int def_b) {
		int r = def_r, g = def_g, b = def_b;     //默认颜色
		if(hexColor != null && !"".equals(hexColor.trim()) && hexColor.trim().length() == 7) {
			String hexs = hexColor.trim();
			r = Integer.valueOf(hexs.substring(1,3), 16);
			g = Integer.valueOf(hexs.substring(3,5), 16);
			b = Integer.valueOf(hexs.substring(5,7), 16);
		}
		int yanse = ((r * 256) + g) * 256 + b;
		if(yanse > 8388608) {yanse = yanse - 16777216;}
		return yanse;
	}
	
	// Color转换为16进制显示
	public static String toHexEncoding(Color color) {
		String R, G, B;
		StringBuffer sb = new StringBuffer();
		R = Integer.toHexString(color.getRed());
		G = Integer.toHexString(color.getGreen());
		B = Integer.toHexString(color.getBlue());
		R = R.length() == 1 ? "0" + R : R;
		G = G.length() == 1 ? "0" + G : G;
		B = B.length() == 1 ? "0" + B : B;
		sb.append("0x");
		sb.append(R);
		sb.append(G);
		sb.append(B);
		return sb.toString();
	}

}
