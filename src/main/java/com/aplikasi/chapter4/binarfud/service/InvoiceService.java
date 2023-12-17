package com.aplikasi.chapter4.binarfud.service;

import com.aplikasi.chapter4.binarfud.entity.Merchant;
import com.aplikasi.chapter4.binarfud.entity.User;

import java.util.Map;

public interface InvoiceService {
    Map generateInvoice(User user);
    Map generateReport(Merchant merchant);
}
