package com.promart.client.service;

import com.promart.client.model.Client;
import com.promart.client.model.ClientWrapper;
import com.promart.client.repository.ClientRepository;
import com.promart.client.service.impl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class ClientServiceImplIntegrationTest {
    @TestConfiguration
    static class ClientServiceImplTestContextConfiguration {

        @Bean
        public ClientService clientService() {
            return new ClientServiceImpl();
        }
    }
    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Before
    public void setUp() {
        Client client1 = new Client();
        client1.setId("1");
        client1.setBirthDate(Long.valueOf(307929600));
        client1.setFirstName("Client1");
        client1.setLastName("Client1");
        List<Client> clients = new ArrayList<Client>();
        clients.add(client1);

        Mockito.when(clientRepository.findAll())
                .thenReturn(clients);

    }

    @Test
    public void listAll() {
        List<Client> clients = clientService.listAll();

        Assert.assertEquals(1, clients.size());
    }

    @Test
    public void listAllWrapper() {
        List<ClientWrapper> clients = clientService.listAllWrapper();

        Assert.assertEquals(1, clients.size());
    }

    @Test
    public void save() {
        Client client2 = new Client();
        client2.setId("2");
        client2.setBirthDate(Long.valueOf(307929600));
        client2.setFirstName("Client2");
        client2.setLastName("Client2");

        Mockito.when(clientRepository.push(any(Client.class))).thenReturn(client2);

        Client client = clientService.save(client2);

        Assert.assertEquals(client, client2);
    }


}
