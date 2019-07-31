package com.promart.client.service;

import com.promart.client.model.Client;
import com.promart.client.model.ClientWrapper;
import com.promart.client.model.Kpi;

import java.util.List;


/**
 * Client Service Interface.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
public interface ClientService  extends GenericService<Client, String> {
    List<Kpi>  getKpiClients();
    List<ClientWrapper> listAllWrapper();
}
