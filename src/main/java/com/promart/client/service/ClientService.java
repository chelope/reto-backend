package com.promart.client.service;

import com.promart.client.model.Client;
import com.promart.client.model.ClientWrapper;

import java.util.List;
import java.util.Map;


/**
 * Client Service Interface.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
public interface ClientService  extends GenericService<Client, String> {
    Map<String,Double> getKpiClients();
    List<ClientWrapper> listAllWrapper();
}
