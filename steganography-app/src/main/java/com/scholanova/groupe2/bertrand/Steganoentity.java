package com.scholanova.groupe2.bertrand;





public class Steganoentity {

	
	// copie le contenu d'une chaine dans un tableau de caractères
	public static void copyToTab(String t1, char t2[]) {

		for (int i = 0; i < t1.length(); i++) {
			t2[i] = t1.charAt(i);
		}
	}

	// copie les pixels 3 par 3 dans un tableau
	public static void copy3Pixelsby3PixelsToTab(char t1[], char t2[], int position) {

		for (int i = 0; i < 3; i++) {
			if (i + position == t2.length) {
				if (i == 0) {
					t1[i] = 'X';
					t1[i + 1] = 'X';
					t1[i + 2] = 'X';
				}
				if (i == 1) {
					t1[i] = 'X';
					t1[i + 1] = 'X';
				}
				if (i == 2) {
					t1[i] = 'X';
				}
				break;
			} else {
				t1[i] = t2[i + position];
			}
		}
	}

	

}