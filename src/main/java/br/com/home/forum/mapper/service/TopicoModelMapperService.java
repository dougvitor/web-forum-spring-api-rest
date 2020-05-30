package br.com.home.forum.mapper.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.forum.dto.DetalhesDoTopicoDto;
import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.form.TopicoForm;
import br.com.home.forum.modelo.Topico;

@Service
public class TopicoModelMapperService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TopicoDto convertToDto(Topico topico) {
		return modelMapper.map(topico, TopicoDto.class);
	}
	
	public Topico convertToEntity(TopicoForm form) {
		return modelMapper.map(form, Topico.class);
	}
	
	public DetalhesDoTopicoDto convertToDetalhesDto(Topico topico) {
		return modelMapper.map(topico, DetalhesDoTopicoDto.class);
	}

}
