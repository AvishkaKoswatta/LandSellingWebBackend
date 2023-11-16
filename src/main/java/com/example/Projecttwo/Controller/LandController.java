package com.example.Projecttwo.Controller;

import com.example.Projecttwo.Exception.LandNotFoundException;
import com.example.Projecttwo.Model.Land;
import com.example.Projecttwo.Repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class LandController {
    @Autowired
    private LandRepository landRepository;

    @PostMapping("/land")
    Land newLand(@RequestBody Land newLand){
        return landRepository.save(newLand);
    }

    @GetMapping("/lands")
    List<Land> getAllLands(){
        return landRepository.findAll();
    }

    @GetMapping("/land/{id}")
    Land getLandById(@PathVariable Long id) {
        return landRepository.findById(id)
                .orElseThrow(() -> new LandNotFoundException(id));
    }

    @PutMapping("/land/{id}")
    Land updateLand(@RequestBody Land newLand, @PathVariable Long id) {
        return landRepository.findById(id)
                .map(land -> {
                    land.setPrice(newLand.getPrice());
                    land.setType(newLand.getType());
                    land.setLocation(newLand.getLocation());
                    return landRepository.save(land);
                }).orElseThrow(() -> new LandNotFoundException(id));
    }


    @DeleteMapping("/land/{id}")
    String deleteLand(@PathVariable Long id){
        if(!landRepository.existsById(id)){
            throw new LandNotFoundException(id);
        }
        landRepository.deleteById(id);
        return  "Land with id "+id+" has been deleted success.";
    }

}
