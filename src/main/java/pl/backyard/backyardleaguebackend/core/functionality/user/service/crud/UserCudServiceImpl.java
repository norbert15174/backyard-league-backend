package pl.backyard.backyardleaguebackend.core.functionality.user.service.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.common.service.CudServiceImpl;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

@Transactional
@Service
class UserCudServiceImpl extends CudServiceImpl<UserRepository, User, Long> implements UserCudService {
    public UserCudServiceImpl(UserRepository repository) {
        super(repository);
    }

}
