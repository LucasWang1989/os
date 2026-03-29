package nz.ac.sit.os.domain.auth;

import lombok.Data;

/**
 * @program: os
 * @description: TODO describe this class
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 22/03/2026 23:22
 **/
@Data
public class Role {

    private Long id;
    private String roleName;     // ADMIN / USER
    private String description;
}