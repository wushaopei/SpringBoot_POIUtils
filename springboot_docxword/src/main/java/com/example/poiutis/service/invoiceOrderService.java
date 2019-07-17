package com.example.poiutis.service;


import com.example.poiutis.model.InvoiceOrder;

import java.util.List;

/**
 * 发票
 * @author wsp
 * @date 17:00 2019/7/16
 */
public interface invoiceOrderService {

    /**
     * 获取开票数据集合
     * @param 　 invoiceOrders 开票单号集合
     * @return 获取结果
     */
    List<InvoiceOrder> getInvoiceLists(List<String> invoiceOrders);

    /**
     * 查询开票记录
     * @param invoiceOrders 用户名id
     * @param currentPage 页码
     * @return pageSize 每页数据量
     */
    List<InvoiceOrder> queryInvoiceLists(List<String> invoiceOrders, int currentPage, int pageSize);

}
