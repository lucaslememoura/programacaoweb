package br.com.lucaslememoura.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.lucaslememoura.model.dto.CategoriaDTO;
import br.com.lucaslememoura.model.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    List<CategoriaDTO> parseListDTO(List<Categoria> categorias);
    CategoriaDTO parseDTO(Categoria categoria);
    Categoria parseEntity(CategoriaDTO categoria);
}
