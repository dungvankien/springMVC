package com.cg.service.method;

import com.cg.model.Method;
import com.cg.model.dto.MethodDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IMethodService extends IGeneralService<Method> {
    List<MethodDTO> getAllMethodDTO();
}
