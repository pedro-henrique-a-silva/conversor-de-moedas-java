package com.conversor.fetchapi;

import com.conversor.dto.ConvertionRatesDto;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Desserealization {

  public ConvertionRatesDto fromJson(String body) {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    return gson.fromJson(body, ConvertionRatesDto.class);
  }
}
