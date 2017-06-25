import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResolver {
	
	/**
	 * 根据图片灰度值转换成ascii字符
	 * param imagePath
	 * param outPutPath
	 */
	public void transform(BufferedImage image, String outPutPath) throws IOException{
		
		int pixel;
		int height = image.getHeight();
		int width = image.getWidth();
		FileWriter writer = new FileWriter(new File(outPutPath), true);
		char[] temp = new char[width];;
		
		for (int i = 0; i < height; i++) {  
            for (int j = 0; j < width; j++) {  
                try {
					pixel = image.getRGB(j, i);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
                temp[j] =  getAscii((pixel & 0xff0000) >> 16, (pixel & 0xff00) >> 8, (pixel & 0xff));
            }  
            outPut(temp, writer);
        } 
		writer.close();
	}
	
	/**
	 * 灰度转ascii字符公式
	 * param image
	 */
	public char getAscii(int red, int green, int blue){
//		char rs = (char)((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)%94+33);
//		System.out.println((((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)*94/256)%94+33));
//		System.out.println((char)(((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)*94/256)%94+33));
		//char rs = (char)(((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)*94/256)%94+33);
		return (char)(((int)(0.2126 * red + 0.7152 * green + 0.0722 * blue)*94/256)%94+33);
	}
	
	/**
	 * 输出到txt文件
	 * param char[] 
	 * param writer 
	 */
	public void outPut(char[] data, FileWriter writer){
		try {
			StringBuffer buffer = new StringBuffer();
			for( int i = 0 ; i < data.length ; i++){
				if( i == data.length-1 ){
					buffer.append("\n");
					break;
				}
				buffer.append(data[i]).append(" ");
			}
			writer.write(buffer.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ImageResolver rs = new ImageResolver();
		try {
			BufferedImage image = ImageIO.read( new File("/Users/zhongbingyi/Desktop/aaa.jpg"));
			//BufferedImage tag = new BufferedImage(image.getWidth()*2, image.getHeight(),BufferedImage.TYPE_INT_RGB);
		    //tag.getGraphics().drawImage(image, 0, 0, image.getWidth()*2, image.getHeight(), null);
			rs.transform(image, "/Users/zhongbingyi/Downloads/out.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

			
	}

}
