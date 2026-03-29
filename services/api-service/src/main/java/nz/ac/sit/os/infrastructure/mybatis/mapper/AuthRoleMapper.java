package nz.ac.sit.os.infrastructure.mybatis.mapper;

import nz.ac.sit.os.domain.auth.RoleRepository;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: os
 * @description: Mapper for auth role table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface AuthRoleMapper extends RoleRepository {

}
