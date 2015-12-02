/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author AlexanderNielsen
 */
@Entity
public class Flightinstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightDate;
    private int numberOfSeats;
    private double totalPrice;
    private int travelTime;
    private Airport origin;
    private Airport destination;
    @ManyToOne
    @JoinColumn(name="Flight_ID")
    private Flight flight;
//    
//    @OneToMany(mappedBy = "flightinstance")
//    ArrayList<Reservation> reservations; 


    public Flightinstance() {
    }

    public Flightinstance(Long id, String flightDate, int numberOfSeats, double totalPrice, int travelTime, Airport origin, Airport destination) {
        this.id = id;
        this.flightDate = flightDate;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.travelTime = travelTime;
        this.origin = origin;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
    
    
    
    
    
}
