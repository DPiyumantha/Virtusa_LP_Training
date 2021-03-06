package com.dimalka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		int input = 0;
		Scanner scanner = new Scanner(System.in);
		Hotel hotel = new Hotel();
		// passing true to just to get an instance without creating the composition
		// classes
		Reservation reservation = new Reservation(true);

		while (input != 9) {
			System.out.println("Exceptions Example");
			System.out.println("Enter 1 to create reservation [CHECKED EXCEPTION - Multiple Layers]");
			System.out.println("Enter 2 to confirm reservation [UNCHECKED EXCEPTION]");
			System.out.println("Enter 3 to confirm reservation [HANDLING UNCHECKED EXCEPTION]");
			System.out.println("Enter 9 to exit");
			input = scanner.nextInt();
			if (input == 1) {

				try {
					reservation = hotel.createReservation();

				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			} else if (input == 2) {
				reservation.confirmReservation();
			} else if (input == 3) {
				try {
					reservation.confirmReservation();
				} catch (ArithmeticException arithmaticException) {
					System.err.println("Invalid input. Please try again! : " + arithmaticException.getMessage());
					reservation.confirmReservation();
				}
			}
		}
		scanner.close();

	}

}
