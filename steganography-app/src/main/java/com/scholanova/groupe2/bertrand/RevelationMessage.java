package com.scholanova.groupe2.bertrand;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class RevelationMessage {

	public void revelation(int size) {
		try {

			BufferedImage image = ImageIO.read(new FileInputStream("perroquet2.bmp"));
			
			int pixel;
			char newPixel[] = new char[32];
			char message[] = new char[size];
			int position = 0;
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {

					if (position == size)
						break;
					
					pixel = image.getRGB(i, j);
					
					Steganoentity.copyToTab(Integer.toBinaryString(pixel), newPixel);
					
					if (position == size) {
						break;						
					}
					message[position] = newPixel[15];
					position++;
					if (position == size) {
						break;
					}
					message[position] = newPixel[23];
					position++;

					if (position == size) {
						break;
					}
					message[position] = newPixel[31];
					position++;
				}
			}
			
			System.out.print("\nLe message retrouvé sous forme binaire est le suivant : ");
			for (int i = 0; i < message.length; i++) {
				System.out.print(message[i]);
			}			

		} catch (Exception e){
			e.printStackTrace();
		}
	}

}