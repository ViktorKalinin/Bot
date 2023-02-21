package com.example.bot.repositories;

import com.example.bot.model.ZodiacSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZodiacSignRepository extends JpaRepository<ZodiacSign, Integer> {
    @Query("select zc.forecast from ZodiacSign zc where zc.sign = :sign")
    String getForecast(String sign);
    @Query("select count(*) from ZodiacSign where id >= 1")
    int checkTableCount();
    @Query("select zc.sign from ZodiacSign zc where zc.sign = :sign")
    String findSign(String sign);
}
