package com.promart.client.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.promart.client.model.Client;
import org.springframework.stereotype.Repository;


/**
 * Client Repository Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@Repository
public class ClientRepository extends DefaultFirebaseRealtimeDatabaseRepository<Client, String> {
}
