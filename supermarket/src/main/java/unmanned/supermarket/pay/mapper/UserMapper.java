package unmanned.supermarket.pay.mapper;


import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int findUserInfoById(String gid);
}