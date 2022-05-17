package com.works.restcontrollers;

import com.works.entities.Productx;
import com.works.repostories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ProductRestController {

    final ProductRepository pRepo;
    public ProductRestController(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    @PostMapping("/productSave")
    public Map<String, Object> saveProduct(@RequestBody Productx pro) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Productx pr = pRepo.save(pro); // insert
        hm.put("result", pr);
        return hm;
    }

    @GetMapping("/productList")
    public Map<String, Object> saveList() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("result", pRepo.findAll() );
        return hm;
    }


    @DeleteMapping("/productDelete")
    public Map<String, Object> productDelete( @RequestParam int pid ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
           pRepo.deleteById(pid);
            hm.put("result", true);
        }catch (Exception ex) {
            hm.put("result", false);
        }
        return hm;
    }


}
