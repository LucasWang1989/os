package nz.ac.sit.os.domain.auth;

import lombok.Data;
import java.util.List;

/**
 * @program: os
 * @description: TODO describe this class
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 22/03/2026 23:21
 **/
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private List<Role> roles;
}