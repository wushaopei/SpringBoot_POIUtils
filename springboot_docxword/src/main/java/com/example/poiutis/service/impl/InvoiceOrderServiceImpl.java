package com.example.poiutis.service.impl;

import com.example.poiutis.dao.InvoiceOrderDao;
import com.example.poiutis.model.InvoiceOrder;
import com.example.poiutis.service.invoiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 开具发票管理
 * @author issuser
 *
 */
@Component
public class InvoiceOrderServiceImpl implements invoiceOrderService {

    @Autowired
    InvoiceOrderDao invoiceOrderDao;

    /**
     * 获取开票数据集合
     * @param 　 invoiceOrders 开票单号集合
     * @return 获取结果
     */
    public List<InvoiceOrder> getInvoiceLists(List<String> invoiceOrders){

        //创建List保存查询到的每一个发票bean
        List<InvoiceOrder> openingInvoiceOrders = new ArrayList<>();

        //根据开票订单号查询开票信息
        for (String invoiceOrder : invoiceOrders) {
            InvoiceOrder openingInvoiceOrder = invoiceOrderDao.selectOpeningInvoice(invoiceOrder);
            openingInvoiceOrders.add(openingInvoiceOrder);
        }

        return openingInvoiceOrders;
    }

    @Override
    public List<InvoiceOrder> queryInvoiceLists(List<String> invoiceOrders, int currentPage, int pageSize) {

        //创建List保存查询到的每一个发票bean
        List<InvoiceOrder> openingInvoiceOrders = new ArrayList<>();

        //根据开票订单号查询开票信息
        for (String invoiceOrder : invoiceOrders) {
            InvoiceOrder openingInvoiceOrder = invoiceOrderDao.queryInvoiceLists(invoiceOrder,currentPage,pageSize);
            openingInvoiceOrders.add(openingInvoiceOrder);
        }

        return openingInvoiceOrders;
    }
}
