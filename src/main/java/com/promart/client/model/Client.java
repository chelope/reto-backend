package com.promart.client.model;

import com.github.fabiomaffioletti.firebase.document.FirebaseDocument;
import com.github.fabiomaffioletti.firebase.document.FirebaseId;

import java.time.*;

/**
 * Client Model Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@FirebaseDocument("/clients")
public class Client {

    private static final long serialVersionUID = 1L;

    @FirebaseId
    private String id;
    private String firstName;
    private String lastName;
    private Long birthDate;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return birthDate
     */
    public Long getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    /**
     * @return Age
     */
    public Integer computeAge()
    {
        LocalDate localBirthDate = Instant.ofEpochSecond(this.birthDate).atZone(ZoneOffset.UTC).toLocalDate();
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        Period period = Period.between(localBirthDate, currentDate);
        return period.getYears();
    }

    /**
     * @param standarDeviation
     * @return
     */
    public Long computeProbableDeathDate(Double standarDeviation) {
        Long variation = Math.round(this.computeAge() * (standarDeviation / 100));
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        LocalDate probableDeathDate = currentDate.plus(Period.ofYears(variation.intValue()));

        return probableDeathDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
    }

}