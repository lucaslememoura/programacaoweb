package br.com.lucaslememoura.controller;

import br.com.lucaslememoura.service.BaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseController<T,S extends BaseService<T>> {
	
	protected S service;

}
