package com.example.grpcdemo.rest;

import com.example.grpcdemo.entity.DataEntity;
import com.example.grpcdemo.jpa.DataEntityRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/getData")
public class HelloRestController {
    private final DataEntityRepository dataEntityRepository;

    public HelloRestController(DataEntityRepository dataEntityRepository) {
        this.dataEntityRepository = dataEntityRepository;
    }

    @GetMapping("/{fetch}")
    public List<DataEntity> getByFetch(@PathVariable int fetch) {
        Pageable pageable = Pageable.ofSize(100);
        List<DataEntity> dataEntities = new ArrayList<>();
        for (int i = 0; i < (fetch / 100); i++) {
            dataEntities.addAll(dataEntityRepository.findAll(pageable.withPage(i)).stream().toList());
        }

        return dataEntities;
    }
}
