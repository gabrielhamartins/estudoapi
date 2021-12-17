package com.gft.estudoapi.feign;

import com.gft.estudoapi.dto.fipe.RespostaFipeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "fipe", url = "https://parallelum.com.br/fipe/api/v1/carros")
public interface FipeClient {

    @GetMapping(value = "/marcas")
    List<RespostaFipeDTO> getBrands();

    @GetMapping("/marcas/{brandId}/modelos")
    RespostaFipeDTO getModels(@PathVariable String brandId);

    @GetMapping("/marcas/{brandId}/modelos/{modelId}/anos/{year}")
    RespostaFipeDTO getValue(@PathVariable String brandId, @PathVariable String modelId, @PathVariable String year);
}
