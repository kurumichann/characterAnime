import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImageResolver {
	
	/**
	 * 根据图片灰度值转换成ascii字符
	 * param imagePath
	 * param outPutPath
	 */
	public void transform(BufferedImage image, String outPutPath){
		int height = image.getHeight();
		int width = image.getWidth();
		char[] temp = new char[1];;
		int pixel;
		for (int i = 0; i < height; i++) {  
            for (int j = 0; j < width; j++) {  
                try {
					pixel = image.getRGB(i, j);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
                temp = new char[width+1];
                temp[j] =  getAscii((pixel & 0xff0000) >> 16, (pixel & 0xff00) >> 8, (pixel & 0xff));
              //System.out.println(temp[j]);
            }  
            outPut(temp, outPutPath);
            //System.out.println(i);
        } 
	}
	
	/**
	 * 灰度转ascii字符公式
	 * param image
	 */
	public char getAscii(int red, int green, int blue){
		System.out.println((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue));
		System.out.println((char)((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)));
		return (char)(int)(0.2126 * red + 0.7152 * green + 0.0722 * blue);
	}
	
	/**
	 * 输出到txt文件
	 * param char[] 
	 * param path
	 */
	public void outPut(char[] data, String outPutPath){
		try {
			FileWriter writer = new FileWriter(new File(outPutPath), true);
			//System.out.println(String.valueOf(data));
			writer.write(String.valueOf(data));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
//		ImageResolver rs = new ImageResolver();
//		try {
//			rs.transform(ImageIO.read( new File("/Users/zhongbingyi/Desktop/23_2.jpg")), "/Users/zhongbingyi/Downloads/out.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		for( int i = 0 ; i < 256 ; i++){
			if((char)i == '!'){
				System.out.println(i);
			}
			System.out.println((char)i);
		}
			
	}

}
