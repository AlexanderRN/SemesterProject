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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author AlexanderNielsen
 */
@Entity
public class Flight implements Serializable {
    @Id
    private String flightId;
    private int numberOfSeats;
//    @ManyToOne
//    private Airline airline;
//    
//    @OneToMany(mappedBy = "flight")
//    private ArrayList<Flightinstance> flightinstances;

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
  
    
}
