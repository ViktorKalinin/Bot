package com.example.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "zodiac_sign")
@Getter
@Setter
@NoArgsConstructor
public class ZodiacSign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "sign")
    private String sign;

    @Column(name = "forecast")
    private String forecast;

    public ZodiacSign(String sign, String forecast) {
        this.sign = sign;
        this.forecast = forecast;
    }
}
