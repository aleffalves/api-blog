package com.github.aleffalves.apiblog.utils;

import com.github.aleffalves.apiblog.model.Usuario;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class MethodsUtils {

    public static Usuario getUsuarioLogado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
