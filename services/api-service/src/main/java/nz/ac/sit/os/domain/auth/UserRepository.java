package nz.ac.sit.os.domain.auth;

import java.util.Optional;

/**
 * @program: os
 * @description: User related operations
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 20/03/2026 23:24
 **/
public interface UserRepository {

    Optional<User> findByUsername(String username);
}