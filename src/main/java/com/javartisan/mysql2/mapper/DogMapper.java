package com.javartisan.mysql2.mapper;


import com.javartisan.bean.Dog;
import org.apache.ibatis.annotations.Param;

public interface DogMapper {

    Dog findById(@Param("id") Long id);

    Long saveDog(@Param("dog") Dog dog);


}
