package com.etiya.emojigame.core.utils.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    public ModelMapper getMapper();

    public ModelMapper getMapperForRequest();

    public ModelMapper getMapperForResponse();

    public ModelMapper getMapperforStrict();

}
