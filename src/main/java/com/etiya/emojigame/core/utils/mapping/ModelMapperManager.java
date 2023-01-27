package com.etiya.emojigame.core.utils.mapping;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService {
    private ModelMapper modelMapper;

    @Autowired
    public ModelMapperManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper getMapper() {
        //setAmbiguityIgnored(true) => composition durumunda iki id varsa, hata verme
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).
                setMatchingStrategy(MatchingStrategies.STANDARD);

        return this.modelMapper;
    }

    @Override
    public ModelMapper getMapperForRequest() {

        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).
                setMatchingStrategy(MatchingStrategies.STANDARD);

        return this.modelMapper;
    }

    @Override
    public ModelMapper getMapperForResponse() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).
                setMatchingStrategy(MatchingStrategies.LOOSE);

        return this.modelMapper;
    }

    @Override
    public ModelMapper getMapperforStrict() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper;
    }
}
