package services;

import entities.Client;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ClientRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
