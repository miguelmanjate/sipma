package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.Counter;
import mz.ciuem.sipma.service.CounterService;

import org.springframework.stereotype.Service;


@Service("counterService")
public class CounterServiceImpl extends GenericServiceImpl<Counter>implements CounterService {

}
