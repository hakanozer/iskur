package com.works.restcontrollers;

import com.works.entities.Prodcut;
import com.works.repositories.ProdcutRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ProductRestController {

    final ProdcutRepository pRepo;
    final CacheManager cacheManager;
    public ProductRestController(ProdcutRepository pRepo, @Lazy CacheManager cacheManager) {
        this.pRepo = pRepo;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Prodcut pr) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("save", pRepo.save(pr));
        cacheManager.getCache("listCache").clear();
        return hm;
    }


    @Cacheable("listCache")
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("list", pRepo.findAll());
        return hm;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
