package utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

//用于产生登录验证码
public class CodeImage {
		public int w = 70;
		public int h = 30;
		public String textCode;
		public String codes ="0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
		public Random r = new Random();
		public Color randomColor()
		{
			int red = r.nextInt(150);
			int green = r.nextInt(150);
			int blue = r.nextInt(150);
			return new Color(red, green, blue);
		}
		public char randomChar()
		{
			int index = r.nextInt(codes.length());
			return codes.charAt(index);
		}
		public void drawline(BufferedImage image)
		{
			int num = 3;
			Graphics2D g2 = (Graphics2D) image.getGraphics();
			for (int i = 0; i < num; i++) {
				int x1 = r.nextInt(w);
				int x2 = r.nextInt(w);
				int y1 = r.nextInt(h);
				int y2 = r.nextInt(h);
				g2.setStroke(new BasicStroke(1.5F));
				g2.setColor(randomColor());
				g2.drawLine(x1, y1, x2, y2);
			}
		}
		public BufferedImage createImage() {
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = (Graphics2D) image.getGraphics();
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, w ,h);
			return image;
		}
		public BufferedImage getImage() {
			BufferedImage image = createImage();
			Graphics2D g2 = (Graphics2D) image.getGraphics();
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				float x = (float) (i*1.0*w/4);
				char c = randomChar();
				String string = c+"";
				sBuilder.append(c);
				g2.setColor(randomColor());
				g2.setFont(new Font("΢���ź�",1, 20));
				g2.drawString(string, x, h-5);
			}
			textCode = sBuilder.toString();
			drawline(image);
			return image;
		}
		public String getTextCode() {
			return textCode;
		}
		public static void out(BufferedImage image,OutputStream outputStream) throws IOException {
			ImageIO.write(image, "JPEG", outputStream);
		}
}
