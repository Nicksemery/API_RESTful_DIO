package Api_dio.dev.service;

import Api_dio.dev.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userCreate);

}
