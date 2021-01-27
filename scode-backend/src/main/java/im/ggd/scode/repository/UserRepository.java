package im.ggd.scode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import im.ggd.scode.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    public UserModel findByUsername(String username);

    public UserModel findByEmail(String email);

}
