package im.ggd.scode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import im.ggd.scode.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

    public UserEntity findByEmail(String email);

}
