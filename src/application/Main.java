package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int roomNumber = sc.nextInt();
            System.out.print("Check-in date: ");
            Date checkin = sdf.parse(sc.next());
            System.out.print("Check-out date: ");
            Date checkout = sdf.parse(sc.next());

            Reservation reservation = new Reservation(roomNumber, checkin, checkout);
            System.out.print("Reservation: " + reservation);

            System.out.print("\n\nEnter date to update the reservation:\n");
            System.out.print("Check-in date: ");
            Date updatedCheckin = sdf.parse(sc.next());
            System.out.print("Check-out date: ");
            Date updatedCheckout = sdf.parse(sc.next());

            reservation.updateDates(updatedCheckin, updatedCheckout);
            System.out.print("Reservation: " + reservation);

        } catch (ParseException e) {
            System.out.print("Invalid date format");
        } catch (DomainException e) {
            System.out.print("Error in reservation: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.print("Unexpected error");
        }

        sc.close();

    }
}