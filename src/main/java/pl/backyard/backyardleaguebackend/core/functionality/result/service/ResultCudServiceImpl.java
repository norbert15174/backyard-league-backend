package pl.backyard.backyardleaguebackend.core.functionality.result.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.common.service.CudServiceImpl;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;

@Transactional
@Service
class ResultCudServiceImpl extends CudServiceImpl<ResultRepository, Result, Long> implements ResultCudService {

    public ResultCudServiceImpl(ResultRepository repository) {
        super(repository);
    }

}
