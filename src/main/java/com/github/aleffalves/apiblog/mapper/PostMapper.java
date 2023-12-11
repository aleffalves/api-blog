package com.github.aleffalves.apiblog.mapper;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.model.Post;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(uses = {ComentarioMapper.class})
public interface PostMapper {


    Post toEntity(PostDTO dto);
    PostDTO toDTO(Post entity);

    List<Post> toEntity(List<PostDTO> dto);
    List<PostDTO> toDTO(List<Post> entity);

}
