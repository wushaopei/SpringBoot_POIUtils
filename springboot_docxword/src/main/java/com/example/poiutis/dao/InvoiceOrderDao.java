package com.example.poiutis.dao;


import com.example.poiutis.model.InvoiceOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName InvoiceOrderDao
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/10 15:42
 * @Version 1.0
 */
@Component
@Mapper
public interface InvoiceOrderDao {

    /**
     * 获取开票数据集合
     * @param 　 invoiceOrders 开票单号集合
     * @return 获取结果
     */
    InvoiceOrder selectOpeningInvoice(@Param("invoiceOrder") String invoiceOrder);

    /**
     * 根据invoiceOrders 的集合 获取发票数据
     * @param invoiceOrder 开票单号
     * @param currentPage 页码
     * @return pageSize 每页数据量
     */
    InvoiceOrder queryInvoiceLists(@Param("invoiceOrder") String invoiceOrder,
                                    @Param("currentPage") int currentPage,
                                    @Param("pageSize") int pageSize);
}
