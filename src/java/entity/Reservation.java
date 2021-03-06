/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author AlexanderNielsen
 */
@Entity
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airline;
    private int numberOfSeats;
    private String origin;
    private String destination;
    private Double price;
    private String res_date;
    private String traveltime;
    
    @ManyToOne( fetch=FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "username")
    private User user;
    
    
    
    @OneToMany( fetch=FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "reservation_id")
    private List<Passenger> passengers = new ArrayList();
    
    
    public Reservation() {
    }
    

    public Reservation(String airline, String origin, int numberOfSeats, String destination, Double price, String res_date, String traveltime, List<Passenger> passengers, User user) {
        this.airline = airline;
        this.origin = origin;
        this.numberOfSeats = numberOfSeats;
        this.destination = destination;
        this.price = price;
        this.res_date = res_date;
        this.traveltime = traveltime;
        this.passengers = passengers;
        this.user = user;
    }

    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }

    public void setNumberOfSeats( int numberOfSeats )
    {
        this.numberOfSeats = numberOfSeats;
    }

    public String getAirline()
    {
        return airline;
    }

    public void setAirline( String airline )
    {
        this.airline = airline;
    }
    
    public User getUsername()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRes_date() {
        return res_date;
    }

    public void setRes_date(String res_date) {
        this.res_date = res_date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestinaiton() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getTraveltime()
    {
        return traveltime;
    }

    public void setTraveltime( String traveltime )
    {
        this.traveltime = traveltime;
    }

    
    
    
}
