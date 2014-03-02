package com.blstream.myhoard.biz.service;

import java.util.logging.Logger;

public abstract class ResourceService<T> implements IResourceService<T> {

	protected final static Logger logger = Logger.getLogger(ResourceService.class
			.getName());

}
