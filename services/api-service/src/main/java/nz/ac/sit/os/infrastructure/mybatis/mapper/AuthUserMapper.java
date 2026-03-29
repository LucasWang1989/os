package nz.ac.sit.os.infrastructure.mybatis.mapper;

import nz.ac.sit.os.domain.auth.UserRepository;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: os
 * @description: Mapper for auth user table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface AuthUserMapper extends UserRepository {

}
