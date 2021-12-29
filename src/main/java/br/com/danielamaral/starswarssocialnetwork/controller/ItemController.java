package br.com.danielamaral.starswarssocialnetwork.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielamaral.starswarssocialnetwork.dto.DefaultResponse;
import br.com.danielamaral.starswarssocialnetwork.dto.ItemDto;
import br.com.danielamaral.starswarssocialnetwork.repository.ItemRepository;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value = "/items", produces = "application/json")
    public DefaultResponse list() {
        DefaultResponse defaultResponse = new DefaultResponse();

        try {
            List<ItemDto> itemsDto = new ArrayList<>();
            itemRepository
                    .findAll()
                    .forEach(item -> itemsDto.add(ItemDto.parseItemDto(item)));

            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData(itemsDto);

        } catch (Exception ex) {
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }
}
