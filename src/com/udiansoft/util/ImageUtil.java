package com.udiansoft.util;

import java.awt.*;
import java.awt.image.*;
import java.awt.color.ColorSpace;
import javax.imageio.ImageIO;

import java.io.*;
import java.util.Random;

public class ImageUtil {
    public String sRand = "";

    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public BufferedImage creatImage() {
        // 在内存中创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        // 画边框
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        // String rand = request.getParameter("rand");
        // rand = rand.substring(0,rand.indexOf("."));
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }
        // 图象生效
        g.dispose();
        return image;
    }

	/** *//**
     * 缩放图像
     * @param srcImageFile 源图像文件地址
     * @param result       缩放后的图像地址
     * @param scale        缩放比例
     * @param flag         缩放选择:true 放大; false 缩小;
     */
    public static BufferedImage scale(String srcImageFile, String result, int scale, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (flag) {             // 放大
                width = width * scale;
                height = height * scale;
            } else {                // 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            if(result != null && !"".equals(result)) {
                ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
            }
            return tag;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
  	}
    
    /** *//**
     * 图像切割
     * @param srcImageFile 源图像地址
     * @param descDir      切片目标文件夹
     * @param destWidth    目标切片宽度
     * @param destHeight   目标切片高度
     */
    public static void cut(String srcImageFile, String descDir, int destWidth, int destHeight)
    {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > destWidth && srcHeight > destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                destWidth = 200; // 切片宽度
                destHeight = 150; // 切片高度
                int cols = 0; // 切片横向数量
                int rows = 0; // 切片纵向数量
                // 计算切片的横向和纵向数量
                if(srcWidth % destWidth == 0) {
                    cols = srcWidth / destWidth;
                } else {
                    cols = (int) Math.floor(srcWidth / destWidth) + 1;
                }
                if(srcHeight % destHeight == 0) {
                    rows = srcHeight / destHeight;
                } else {
                    rows = (int) Math.floor(srcHeight / destHeight) + 1;
                }
                // 循环建立切片
                // 改进的想法:是否可用多线程加快切割速度
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        // 四个参数分别为图像起点坐标和宽高
                        // 即: CropImageFilter(int x,int y,int width,int height)
                        cropFilter = new CropImageFilter(j * 200, i * 150, destWidth, destHeight);
                        img = Toolkit.getDefaultToolkit().createImage(
                                        new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = tag.getGraphics();
                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                        g.dispose();
                        // 输出为文件
                        ImageIO.write(tag, "JPEG", new File(descDir + "pre_map_" + i + "_" + j + ".jpg"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** *//**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
     */
    public static BufferedImage convert(String source, String result)
    {
        try {
            File f = new File(source);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            if(result != null && !"".equals(result)) {
                ImageIO.write(src, "JPG", new File(result));
            }
            return src;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /** *//**
     * 彩色转为黑白
     * @param source
     * @param result
     */
    public static BufferedImage gray(String source, String result)
    {
        try {
            BufferedImage src = ImageIO.read(new File(source));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            if(result != null && !"".equals(result)) {
                ImageIO.write(src, "JPEG", new File(result));
            }
            return src;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //图像加文字
    public static BufferedImage imageDrawString(String srcImageFile, String result, String words, int x, int y, String font, int fontsize, int r, int g, int b) {
        try {
            BufferedImage image = ImageIO.read(new File(srcImageFile)); // 读入文件
            // 获取图形上下文
            Graphics gh = image.getGraphics();
            // 设定字体
            if(font == null || "".equals(font.trim())) {
            	font = "宋体";
            }
            gh.setFont(new Font(font, Font.PLAIN, fontsize));
            // 设定颜色
            gh.setColor(new Color(r, g, b));
            // 图像加文字
            gh.drawString(words, x, y);
            // 图象生效
            gh.dispose();
            if(result != null && !"".equals(result)) {
                ImageIO.write(image, "JPG", new File(result));// 输出到文件流
            }
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    //图像加文字
    public static BufferedImage imageDrawString(BufferedImage image, String result, String words, int x, int y, String font, int fontsize, int r, int g, int b) {
        try {
            // 获取图形上下文
            Graphics gh = image.getGraphics();
            // 设定字体
            if(font == null || "".equals(font.trim())) {
            	font = "宋体";
            }
            gh.setFont(new Font(font, Font.PLAIN, fontsize));
            // 设定颜色
            gh.setColor(new Color(r, g, b));
            // 图像加文字
            gh.drawString(words, x, y);
            // 图象生效
            gh.dispose();
            if(result != null && !"".equals(result)) {
                ImageIO.write(image, "JPG", new File(result));// 输出到文件流
            }
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /** *//**
     * @param args
     */
    public static void main(String[] args) {
        //scale("d:\\0.jpg","d:\\3.jpg",5,false);    //缩小5倍
    	imageDrawString("d:\\0.jpg","d:\\6.jpg","远处的山", 60, 160, "宋体", 100, 255, 150, 60);
    }    
    
    
}
