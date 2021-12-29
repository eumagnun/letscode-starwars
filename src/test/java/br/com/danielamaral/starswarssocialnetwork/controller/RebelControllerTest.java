package br.com.danielamaral.starswarssocialnetwork.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class RebelControllerTest {

    @Autowired
    private MockMvc mockMvc;
	
	@Test
	void shouldCreateOneRebels() throws Exception {
		
		String payload= "{\"name\":\"Daniel\",\"age\":30,\"gender\":\"MALE\",\"locationDto\":{\"id\":1},\"inventoryDto\":{\"inventoryItemsDto\":[{\"itemDto\":{\"id\":4},\"quantity\":10},{\"itemDto\":{\"id\":3},\"quantity\":10}]}}";
		 mockMvc.perform( MockMvcRequestBuilders
                 .post("/api/v1/rebels/")
                 .content(payload)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
		 
         .andExpect(status().is2xxSuccessful());
		 
		 

	              
	}
	
	@Test
	void shouldList3RebelsTest() throws Exception {
		
		
		String payload1= "{\"name\":\"João\",\"age\":30,\"gender\":\"MALE\",\"locationDto\":{\"id\":1},\"inventoryDto\":{\"inventoryItemsDto\":[{\"itemDto\":{\"id\":2},\"quantity\":20},{\"itemDto\":{\"id\":1},\"quantity\":20}]}}";
		 mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/rebels/")
		 		.content(payload1)
		 		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
		 
		String payload2= "{\"name\":\"Maria\",\"age\":30,\"gender\":\"MALE\",\"locationDto\":{\"id\":1},\"inventoryDto\":{\"inventoryItemsDto\":[{\"itemDto\":{\"id\":2},\"quantity\":20},{\"itemDto\":{\"id\":1},\"quantity\":20}]}}";
		 mockMvc.perform( MockMvcRequestBuilders
                 .post("/api/v1/rebels/")
	 			 .content(payload2)
	 			 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON));
		 
		 
		String payload3= "{\"name\":\"Daniel\",\"age\":30,\"gender\":\"MALE\",\"locationDto\":{\"id\":1},\"inventoryDto\":{\"inventoryItemsDto\":[{\"itemDto\":{\"id\":2},\"quantity\":20},{\"itemDto\":{\"id\":1},\"quantity\":20}]}}";
		 mockMvc.perform( MockMvcRequestBuilders
                 .post("/api/v1/rebels/")
	 			 .content(payload3)
	 			 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON));
		
		 mockMvc.perform( MockMvcRequestBuilders
                 .get("/api/v1/rebels/")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
         .andExpect(status().is2xxSuccessful())
         .andExpect(MockMvcResultMatchers.jsonPath("data.size()").value(3));
	}

	@Test
	void sholdUpdateRebelLocationTest() throws Exception {
		
		String payload1= "{\"id\":2}";
		 mockMvc.perform( MockMvcRequestBuilders
                 .patch("/api/v1/rebels/1/location")
                 .content(payload1)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
         .andExpect(status().is2xxSuccessful());
	}


	@Test
	void sholdDoDenunciation() throws Exception {
		String payload1= "{\"accuserID\": 1, \"suspectID\": 5}";
		 mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/rebels/denunciation")
                .content(payload1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
	}
	

	@Test
	void sholdDoExchange() throws Exception {
		String payload = "{\"negotiationPart1\":{\"idPart\":1,\"itensParticipant\":[{\"id\":0,\"item\":{\"id\":4,\"name\":\"Comida\",\"points\":1},\"quantity\":4}]},\"negotiationPart2\":{\"idPart\":5,\"itensParticipant\":[{\"id\":0,\"item\":{\"id\":3,\"name\":\"\\u00c1gua\",\"points\":2},\"quantity\":2}]}}";
		 mockMvc.perform( MockMvcRequestBuilders
	                .post("/api/v1/rebels/exchange")
	                .content(payload)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().is2xxSuccessful());
	}

}
