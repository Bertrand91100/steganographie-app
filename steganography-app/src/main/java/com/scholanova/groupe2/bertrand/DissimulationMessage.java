package com.scholanova.groupe2.bertrand;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class DissimulationMessage {

	int size;
	Scanner sc;
	public int dissimulation() {
		try {

			BufferedImage image = ImageIO.read(new FileInputStream("perroquet.bmp"));
			BufferedImage imageFinal = ImageIO.read(new FileInputStream("perroquet.bmp"));

			sc = new Scanner(System.in);
			System.out.print("Entrer le message à cacher : ");
			String message = sc.nextLine(); 
			
			char[] ascii; 
			char[] codeAscii = new char[8 * message.length()];
			String code = "";

			ascii = message.toCharArray();
			for (int i = 0; i < ascii.length; i++) {
				code = code + Integer.toBinaryString(ascii[i]);
			}
			//Transformation du codeASCII sous forme de tableau de caractères
			codeAscii = code.toCharArray();

			System.out.print("Le message sous forme binaire est : ");
			for (int i = 0; i < codeAscii.length; i++) {
				System.out.print(codeAscii[i]);
			}
			
			System.out.println();

			int pixel;
			char newPixel[] = new char[32];
			char tabCode[] = new char[3];
			int position = 0;

			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {

					if (position > codeAscii.length) {
						break;
					}
					//On prend trois bits du message
					Steganoentity.copy3Pixelsby3PixelsToTab(tabCode, codeAscii, position);

					System.out.print("\nNous allons dissimuler : ");
					System.out.print(tabCode[0] + "" + tabCode[1] + "" + tabCode[2]);
					position = position + 3;

					System.out.println();
					
					//Récupération d'un pixel à la position (i,j)
					pixel = image.getRGB(i, j);
					
					//Transformation du pixel en chaîne binaire
					Steganoentity.copyToTab(Integer.toBinaryString(pixel), newPixel);

					System.out.print("pixel Initial : ");
					
					for (int k = 0; k < newPixel.length; k++) {
						System.out.print(newPixel[k]);
					}
					System.out.println();

					// on remplace les bits de poids faible de chaque pixel par notre bit à cacher
					if (tabCode[0] == 'X') {
						break;
					}
					if (tabCode[1] == 'X') {
						newPixel[15] = tabCode[0];
						System.out.print("pixel Final : ");
						for (int l = 0; l < newPixel.length; l++) {
							System.out.print(newPixel[l]);
						}
						System.out.println();
						
						String pixString = "";
						for (int p = 0; p < newPixel.length; p++) {
							pixString = pixString + newPixel[p];
						}
						int pixInt = (int) Long.parseLong(pixString, 2);
						imageFinal.setRGB(i, j, pixInt);
						
						break;
					}
					if (tabCode[2] == 'X') {
						newPixel[23] = tabCode[1];
						newPixel[15] = tabCode[0];
						System.out.print("pixel Final : ");
						for (int m = 0; m < newPixel.length; m++) {
							System.out.print(newPixel[m]);
						}
						String pixString = "";
						for (int p = 0; p < newPixel.length; p++) {
							pixString = pixString + newPixel[p];
						}
						int pixInt = (int) Long.parseLong(pixString, 2);
						imageFinal.setRGB(i, j, pixInt);
						break;
					}
					//Mise à jour des nouveaux pixels
					newPixel[31] = tabCode[2];
					newPixel[23] = tabCode[1];
					newPixel[15] = tabCode[0];
					
					System.out.print("pixel Final : ");
					for (int n = 0; n < newPixel.length; n++) {
						System.out.print(newPixel[n]);
					}
					System.out.println();
					
					//On transforme le tableau de caractères en chaine 
					String pixString = "";
					for (int p = 0; p < newPixel.length; p++) {
						pixString = pixString + newPixel[p];
					}
					//On récupère dans un pixel(int) la chaine à rajouter
					int pixInt = (int) Long.parseLong(pixString, 2);					

					//On met à jour le nouveau pixel dans la nouvelle image				
					imageFinal.setRGB(i, j, pixInt);
					
				}
			}
			
			//On écrit notre image dans un fichier
			 
			File output = new File("perroquet2.bmp");			 
			ImageIO.write(imageFinal, "bmp", output);

			System.out.print("\n\nLe message caché sous forme binaire est le suivant: ");
			for (int i = 0; i < codeAscii.length; i++) {
				System.out.print(codeAscii[i]);
			}
			//on retourne la taille du message caché
			size = codeAscii.length;
			
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return size;
	}
	
	public int getSize(){
		return size;
	}

}
