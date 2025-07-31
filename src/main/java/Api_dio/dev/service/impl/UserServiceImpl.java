package Api_dio.dev.service.impl;

import Api_dio.dev.domain.model.User;
import Api_dio.dev.domain.repository.UserRepository;
import Api_dio.dev.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

//usando a classe de implementação, não expomos o serviço que o usuario é fornecido num padrão de encapsulamento

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userCreate) {
        if (userCreate.getId() != null && userRepository.existsById(userCreate.getId())) {
            throw new IllegalArgumentException("This User ID already exists");
        }
        if (userRepository.existsByAccountNumber(userCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists");
        }
        return userRepository.save(userCreate);
    }
}
