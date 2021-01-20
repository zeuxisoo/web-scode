package im.ggd.scode.service;

import im.ggd.scode.model.User;
import im.ggd.scode.model.vo.UserVO;

public interface UserService {

    public boolean isUsernameExists(String username);

    public User findByUsername(String username);

    public User store(UserVO userVO);

}
