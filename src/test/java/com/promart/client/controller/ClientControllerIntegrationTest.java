package com.promart.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promart.client.Application;
import com.promart.client.model.Client;
import com.promart.client.repository.ClientRepository;
import com.promart.client.service.ClientService;
import com.promart.client.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(secure=false)
public class ClientControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void createClient() throws Exception {

        Client client = new Client();
        client.setId("1");
        client.setBirthDate(Long.valueOf(307929600));
        client.setFirstName("Client1");
        client.setLastName("Client1");
        Mockito.when(clientRepository.push(any(Client.class))).thenReturn(client);

        mvc.perform(post("/clientes/creacliente").contentType(MediaType.APPLICATION_JSON).content((new ObjectMapper()).writeValueAsString(client))).andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", is("Client1")));
    }

    @Test
    public void listAll() throws Exception {

        Client client = new Client();
        client.setId("2");
        client.setBirthDate(Long.valueOf(307929600));
        client.setFirstName("Client2");
        client.setLastName("Client2");
        List<Client> clients = Arrays.asList(client);
        Mockito.when(clientRepository.findAll(any(Client.class))).thenReturn(clients);


        mvc.perform(get("/clientes/listclientes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void kpiClients() throws Exception {

        Client client1 = new Client();
        client1.setId("1");
        client1.setBirthDate(Long.valueOf(307929600));
        client1.setFirstName("Client1");
        client1.setLastName("Client1");

        Client client2 = new Client();
        client2.setId("2");
        client2.setBirthDate(Long.valueOf(307929600));
        client2.setFirstName("Client2");
        client2.setLastName("Client2");
        List<Client> clients = Arrays.asList(client1,client2);
        Mockito.when(clientRepository.findAll(any(Client.class))).thenReturn(clients);

        mvc.perform(get("/clientes/kpiclientes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.standardDeviation", is(0.0)));
    }


}
