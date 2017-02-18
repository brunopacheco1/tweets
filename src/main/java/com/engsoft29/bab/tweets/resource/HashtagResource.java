package com.engsoft29.bab.tweets.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.engsoft29.bab.tweets.dto.HashtagDTO;
import com.engsoft29.bab.tweets.dto.ResponseDTO;
import com.engsoft29.bab.tweets.service.HashtagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Path("/hashtag")
@Api("servicos")
public class HashtagResource {

	@Inject
	private HashtagService service;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Serviço de processamento de hashtag.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "A hashtag foi recebida.", response = ResponseDTO.class),
	    @ApiResponse(code = 409, message = "A hastag não passou na validação de campos.", response = ResponseDTO.class),
	    @ApiResponse(code = 500, message = "Erro não esperado.", response = ResponseDTO.class)
	})
	public ResponseDTO send(@ApiParam(required=true, value="Hashtag que será processada.") HashtagDTO dto) throws Exception {
		service.processar(dto);
		
		return new ResponseDTO(true);
	}
}