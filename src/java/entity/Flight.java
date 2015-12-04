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
@Table(name="flight")
public class Flight implements Serializable {
    @Id
    private String flightId;
    private int numberOfSeats;
    
    @ManyToOne
    @JoinColumn(name="Airline_ID")
    private Airline airline;
    
    @OneToMany(mappedBy = "flight", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Flightinstance> flightinstances = new ArrayList();


    public Flight() {
    }

    public Flight(String flightId, int numberOfSeats) {
        this.flightId = flightId;
        this.numberOfSeats = numberOfSeats;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
  
    
}
