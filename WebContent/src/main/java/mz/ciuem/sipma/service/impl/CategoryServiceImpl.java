package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.Categoria;
import mz.ciuem.sipma.service.CategoryService;

import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends GenericServiceImpl<Categoria> implements CategoryService{

}
