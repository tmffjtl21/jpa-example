package com.osstem.jpa.example.web.rest;

import com.osstem.jpa.example.service.DirectoryService;
import com.osstem.jpa.example.service.dto.DirectoryDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectoryResource {

    private DirectoryService directoryService;

    public DirectoryResource(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    /**
     * POST  /post : Create Directory
     */
    @PostMapping("/directory")
    @ApiOperation(value = "createDirectory", notes = "1) Create Directory Entity")
    public ResponseEntity<DirectoryDTO> createDirectory(@RequestBody DirectoryDTO directoryDTO) throws Exception {
        directoryService.createDirectory(directoryDTO);
        return ResponseEntity.created(null).build();
    }

    /**
     * PUT  /post : Update Directory
     */
    @PutMapping("/directory")
    @ApiOperation(value = "updateDirectory", notes = "1) Update Directory Entity")
    public ResponseEntity<DirectoryDTO> updateDirectory(@RequestBody DirectoryDTO directoryDTO) throws Exception {
        directoryService.updateDirectory(directoryDTO);
        return ResponseEntity.accepted().build();
    }

    /**
     * READ  /post : Read Directory
     */
    @GetMapping("/directory")
    @ApiOperation(value = "readDirectory", notes = "1) Read Post Entity")
    public ResponseEntity<List<DirectoryDTO>> readDirectory() throws Exception {
        List<DirectoryDTO> list = directoryService.readDirectory();
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    /**
     * DELETE  /post : Delete Directory
     */
    @DeleteMapping("/directory")
    @ApiOperation(value = "removeDirectory", notes = "1) Delete Directory Entity")
    public ResponseEntity<DirectoryDTO> removDirectory(@RequestParam String id) throws Exception {
        directoryService.removDirectory(id);
        return ResponseEntity.accepted().build();
    }
}
