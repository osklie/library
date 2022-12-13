package com.example.library.controller;

import com.example.library.domain.CopyDto;
import com.example.library.exception.ObjectNotFoundException;
import com.example.library.mapper.CopyMapper;
import com.example.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/copy")
public class CopyController {
    @Autowired
    private CopyService dbServiceCopy;
    @Autowired
    private CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopyDto> getCopies() {
        return copyMapper.mapToCopyDtoList(dbServiceCopy.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopy")
    public CopyDto getCopy(@RequestParam Long copyId) throws ObjectNotFoundException {
        return copyMapper.mapToCopyDto(dbServiceCopy.getCopy(copyId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopy")
    public void deleteCopy(@RequestParam Long copyId) {
        dbServiceCopy.deleteCopy(copyId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCopy")
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) {
        return copyMapper.mapToCopyDto(dbServiceCopy.saveCopy(copyMapper.mapToCopy(copyDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCopy", consumes = APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) {
        dbServiceCopy.saveCopy2(copyMapper.mapToCopy(copyDto));
    }
}