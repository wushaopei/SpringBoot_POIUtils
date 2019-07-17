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
}
