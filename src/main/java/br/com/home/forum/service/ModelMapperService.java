package br.com.home.forum.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.modelo.Topico;

@Service
public class ModelMapperService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TopicoDto convertToDto(Topico topico) {
		return modelMapper.map(topico, TopicoDto.class);
	}
}
