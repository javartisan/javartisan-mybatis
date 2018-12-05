package com.javartisan.service;

import com.javartisan.bean.Dog;

public interface DogService {

    Dog findById(Long id);

    Long saveDog(Dog dog);

}
