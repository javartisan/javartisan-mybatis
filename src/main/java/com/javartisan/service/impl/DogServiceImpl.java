package com.javartisan.service.impl;

import com.javartisan.bean.Dog;
import com.javartisan.mysql2.mapper.DogMapper;
import com.javartisan.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogMapper dogMapper;

    @Override
    public Dog findById(Long id) {
        return dogMapper.findById(id);
    }

    @Override
    public Long saveDog(Dog dog) {
        return dogMapper.saveDog(dog);
    }
}
