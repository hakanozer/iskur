package com.works.restcontrollers;

import com.works.entities.Productx;
import com.works.repostories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductRepository pRepo;
    public ProductRestController(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    @PostMapping("/save")
    public Map<String, Object> saveProduct(@RequestBody Productx pro) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Productx pr = pRepo.save(pro); // insert
        pRepo.findByTitleEqualsAndDetailIsLike(null, "");
        hm.put("result", pr);
        return hm;
    }

    @GetMapping("/list")
    public Map<String, Object> saveList() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("result", pRepo.findAll() );
        return hm;
    }


    @DeleteMapping("/delete")
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

    @PutMapping("/update")
    public Map<String, Object> productUpdate( @RequestBody Productx pro ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            Productx p = pRepo.getById(pro.getPid());
            if ( p.getPid() > 0 ) {
                pRepo.saveAndFlush(pro);
                hm.put("status", true);
                hm.put("result", pro);
            }else {
                hm.put("status", false);
                hm.put("message", "Kayıt hatası");
            }
        }catch (Exception ex) {
            hm.put("status", false);
            hm.put("message", ex.getMessage());
        }
        return hm;
    }


    @GetMapping("/search")
    public Map<String, Object> search( @RequestParam String q ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<Productx> ls = pRepo.findByTitleContains(q);
        hm.put("result", ls );
        return hm;
    }


}
