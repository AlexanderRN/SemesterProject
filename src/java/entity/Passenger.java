/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author AlexanderNielsen
 */
@Entity
@Table(name="passenger")
public class Passenger implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="reservation_ID")
    private Reservation reservation;
    
    private String firstName;
    private String lastName;
    private boolean reserver;

    public Passenger()
    {
    }

    public Passenger( String firstName, String lastName, boolean reserver )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reserver = reserver;
    }
    
    public Reservation getReservation()
    {
        return reservation;
    }

    public void setReservation( Reservation reservation )
    {
        this.reservation = reservation;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public boolean isReserver()
    {
        return reserver;
    }

    public void setReserver( boolean reserver )
    {
        this.reserver = reserver;
    }
    
    

    @Override
    public String toString()
    {
        return "Passenger{" + "reservation=" + reservation + ", firstName=" + firstName + ", lastName=" + lastName + ", reserver=" + reserver + '}';
    }
   
    
}


