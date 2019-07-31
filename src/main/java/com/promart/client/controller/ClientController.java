package com.promart.client.controller;

import com.promart.client.model.Client;
import com.promart.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * ClientController Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientes")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    /**
     * @param client
     * @return Client
     */
    @RequestMapping(value = "/creacliente", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity createClient(@RequestBody Client client) {
        try {
            Client newClient = clientService.save(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Unexpected Exception", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return List Clients
     */
    @RequestMapping(value = "/listclientes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listClients() {
        try {
            return new ResponseEntity<>(clientService.listAllWrapper(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Unexpected Exception", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Map that contains KPIs clients
     */
    @RequestMapping(value = "/kpiclientes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> kpiClients() {
        try {
            return new ResponseEntity<>(clientService.getKpiClients(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Unexpected Exception", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
