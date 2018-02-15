package mz.ciuem.sipma.service.impl;

import mz.ciuem.sipma.entity.Invoice;
import mz.ciuem.sipma.service.InvoiceService;

import org.springframework.stereotype.Service;

@Service("invoiceService")
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice> implements InvoiceService{

}
