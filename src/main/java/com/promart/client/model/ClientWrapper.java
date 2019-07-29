package com.promart.client.model;

/**
 * Client Wrapper Model Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
public class ClientWrapper extends Client{

    private Integer age;
    private Long probableDeathDate;


    /**
     * @param client
     * @param probableDeathDate
     */
    public ClientWrapper(Client client, Integer age, Long probableDeathDate) {
        super.setId(client.getId());
        super.setFirstName(client.getFirstName());
        super.setLastName(client.getLastName());
        super.setBirthDate(client.getBirthDate());
        this.probableDeathDate = probableDeathDate;
        this.age = age;
    }

    /**
     * @return Long
     */
    public Long getProbableDeathDate() {
        return probableDeathDate;
    }

    /**
     * @param probableDeathDate
     */
    public void setProbableDeathDate(Long probableDeathDate) {
        this.probableDeathDate = probableDeathDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}