package br.com.lucaslememoura.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.lucaslememoura.model.dto.EditoraDTO;
import br.com.lucaslememoura.model.entity.Editora;
@Mapper(componentModel = "spring")
public interface EditoraMapper {
    List<EditoraDTO> parseListDTO(List<Editora> editoras);
    EditoraDTO parseDTO(Editora editora);
    Editora parseEntity(EditoraDTO editora);
}
