package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    //construtor
    public Reservation(Integer roomNumber, Date checkin, Date checkout) {
        if (!checkout.after(checkin))
        {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    //getters e setters
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    //métodos
    public long duration(){
        long diff = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkin, Date checkout){
        Date now = new Date();
        if (checkin.before(getCheckin()) || checkout.before(getCheckin()))
        {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkout.after(checkin))
        {
            throw new DomainException("Check-out dates must be after check-in date");
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString(){
        return "Room: " + roomNumber + ", check-in: " + sdf.format(checkin) + ", check-out: " + sdf.format(checkout) + ", " + duration() + " nights";
    }
}
